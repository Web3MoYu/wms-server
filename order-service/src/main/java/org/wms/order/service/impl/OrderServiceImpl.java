package org.wms.order.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.wms.api.client.UserClient;
import org.wms.common.entity.sys.User;
import org.wms.common.enums.order.OrderType;
import org.wms.common.model.Result;
import org.wms.order.mapper.OrderMapper;
import org.wms.order.model.dto.ApprovalDto;
import org.wms.order.model.dto.OrderDto;
import org.wms.order.model.dto.OrderQueryDto;
import org.wms.order.model.entity.OrderIn;
import org.wms.order.model.entity.OrderInItem;
import org.wms.order.model.entity.OrderOut;
import org.wms.order.model.entity.OrderOutItem;
import org.wms.order.model.enums.OrderStatusEnums;
import org.wms.order.model.vo.OrderDetailVo;
import org.wms.order.model.vo.OrderVo;
import org.wms.order.service.OrderInService;
import org.wms.order.service.OrderOutService;
import org.wms.order.service.OrderService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import jakarta.annotation.Resource;

@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Resource
    UserClient userClient;

    @Resource
    OrderInService orderInService;

    @Resource
    OrderOutService orderOutService;

    @Resource
    OrderMapper orderMapper;

    @Override
    public Result<String> addOrderIn(OrderDto<OrderIn, OrderInItem> order) {
        return orderInService.addOrder(order);
    }

    @Override
    public Result<String> addOrderOut(OrderDto<OrderOut, OrderOutItem> order) {
        return null;
    }

    @Override
    public Result<List<OrderDetailVo<OrderInItem>>> inDetail(String id) {
        return orderInService.inDetail(id);
    }

    @Override
    public Result<List<OrderDetailVo<OrderOutItem>>> outDetail(String id) {
        return orderOutService.outDetail(id);
    }

    @Override
    public Result<String> updateStatus(Integer type, String id, String remark, OrderStatusEnums statusEnums) {
        if (OrderType.IN_ORDER.getCode().equals(type)) {
            orderInService.updateStatus(id, remark, statusEnums);
        } else {
            orderOutService.updateStatus(id, remark, statusEnums);
        }
        return Result.success(null, "成功");
    }

    @Override
    public Result<String> approvalInBound(String id, List<ApprovalDto> dto, String inspector) {
        return orderInService.approve(id, dto, inspector);
    }

    @Override
    public Result<Page<OrderVo>> pageOrder(OrderQueryDto queryDto) {
        // 参数校验
        if (queryDto == null) {
            queryDto = new OrderQueryDto();
        }

        // 计算偏移量
        int offset = (queryDto.getPage() - 1) * queryDto.getPageSize();
        int limit = queryDto.getPageSize();

        // 执行联合查询
        List<OrderVo> orders = orderMapper.unionQueryOrders(queryDto, offset, limit);

        // 统计总记录数
        Long total = orderMapper.countUnionQueryOrders(queryDto);

        // 收集所有需要查询的用户ID
        Set<String> userIdSet = new HashSet<>();
        for (OrderVo order : orders) {
            // 收集创建人ID
            if (order.getCreator() != null && order.getCreator().getUserId() != null) {
                userIdSet.add(order.getCreator().getUserId());
            }

            // 收集审批人ID
            if (order.getApprover() != null && order.getApprover().getUserId() != null) {
                userIdSet.add(order.getApprover().getUserId());
            }

            // 收集质检人员ID
            if (order.getInspector() != null && order.getInspector().getUserId() != null) {
                userIdSet.add(order.getInspector().getUserId());
            }
        }

        // 批量查询用户信息
        Map<String, User> userMap = new HashMap<>();
        if (!userIdSet.isEmpty()) {
            try {
                // 批量获取用户信息，减少网络调用
                List<User> userList = userClient.getUserByIds(new ArrayList<>(userIdSet));
                if (userList != null) {
                    for (User user : userList) {
                        if (user != null && user.getUserId() != null) {
                            userMap.put(user.getUserId(), user);
                        }
                    }
                }
            } catch (Exception e) {
                log.error("批量获取用户信息失败", e);
            }
        }

        // 遍历订单列表，为每个订单设置完整的用户信息
        for (OrderVo order : orders) {
            // 填充创建人信息
            if (order.getCreator() != null && order.getCreator().getUserId() != null) {
                String userId = order.getCreator().getUserId();
                User user = userMap.get(userId);
                if (user != null) {
                    order.setCreator(user);
                }
            }

            // 填充审批人信息
            if (order.getApprover() != null && order.getApprover().getUserId() != null) {
                String userId = order.getApprover().getUserId();
                User user = userMap.get(userId);
                if (user != null) {
                    order.setApprover(user);
                }
            }

            // 填充质检人员信息
            if (order.getInspector() != null && order.getInspector().getUserId() != null) {
                String userId = order.getInspector().getUserId();
                User user = userMap.get(userId);
                if (user != null) {
                    order.setInspector(user);
                }
            }
        }

        // 构建分页结果
        Page<OrderVo> page = new Page<>(queryDto.getPage(), queryDto.getPageSize(), total);
        page.setRecords(orders);

        return Result.success(page, "查询成功");
    }

}
