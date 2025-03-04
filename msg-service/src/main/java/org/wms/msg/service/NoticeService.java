package org.wms.msg.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.wms.common.model.Result;
import org.wms.msg.model.dto.NoticePageDTO;
import org.wms.msg.model.entity.Notice;
import org.wms.msg.model.vo.NoticeVO;

/**
* @author moyu
* @description 针对表【notif_notice(公告表)】的数据库操作Service
* @createDate 2025-03-03 12:37:18
*/
public interface NoticeService extends IService<Notice> {

    /**
     * 分页查询公告列表
     *
     * @param param 查询参数，包含页码、每页大小、查询条件等
     * @return 公告分页结果，包装在Result对象中
     */
    Result<Page<NoticeVO>> pageList(NoticePageDTO param);
}
