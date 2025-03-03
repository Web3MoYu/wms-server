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

    Result<Page<NoticeVO>> pageList(NoticePageDTO param);
}
