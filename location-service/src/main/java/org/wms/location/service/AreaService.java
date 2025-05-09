package org.wms.location.service;

import org.wms.location.model.dto.AreaDto;
import org.wms.common.entity.location.Area;
import org.wms.common.entity.location.StatusEnums;
import org.wms.location.model.vo.AreaVo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author moyu
 * @description 针对表【wms_area(区域表)】的数据库操作Service
 * @createDate 2025-03-08 17:25:49
 */
public interface AreaService extends IService<Area> {

    /**
     * 分页查询区域信息，包含负责人名称
     *
     * @param page 页码
     * @param pageSize 每页条数
     * @param areaName 区域名称（可选，模糊查询）
     * @param areaManager 负责人（可选）
     * @param status 状态（可选）
     * @return 包含负责人名称的区域分页结果
     */
    Page<AreaVo> pageAreaVos(int page, int pageSize, String areaName, String areaManager, Integer status);

    /**
     * 更新区域状态
     *
     * @param id 区域ID
     * @param status 新状态
     * @return 是否更新成功
     */
    boolean updateStatus(String id, StatusEnums status);

    /**
     * 检查AreaCode是否重复
     *
     * @param areaCode 区域编码
     * @return 检查结果
     */
    boolean checkAreaCode(String areaCode);

    /**
     * 新增区域
     *
     * @param areaDto 区域信息
     * @return 是否新增成功
     */
    boolean saveArea(AreaDto areaDto);

    /**
     * 修改区域
     *
     * @param areaDto 区域信息
     * @return 是否修改成功
     */
    boolean updateArea(AreaDto areaDto);
}
