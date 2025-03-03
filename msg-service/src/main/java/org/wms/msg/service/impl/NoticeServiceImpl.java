package org.wms.msg.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.wms.common.model.Result;
import org.wms.msg.model.dto.NoticePageDTO;
import org.wms.msg.model.entity.Notice;
import org.wms.msg.mapper.NoticeMapper;
import org.wms.msg.model.vo.NoticeVO;
import org.wms.msg.service.NoticeService;

/**
 * @author moyu
 * @description 针对表【notif_notice(公告表)】的数据库操作Service实现
 * @createDate 2025-03-03 12:37:18
 */
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice>
        implements NoticeService {

    @Resource
    NoticeMapper noticeMapper;

    @Override
    public Result<Page<NoticeVO>> pageList(NoticePageDTO param) {
        Page<NoticeVO> list = noticeMapper.pageList(new Page<NoticeVO>
                (param.getPage(), param.getPageSize()), param);
        return Result.success(list, "查询成功");
    }
}




