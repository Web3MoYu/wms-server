package org.wms.msg.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.wms.msg.model.entity.Notice;
import org.wms.msg.mapper.NoticeMapper;
import org.wms.msg.service.NoticeService;

/**
* @author moyu
* @description 针对表【notif_notice(公告表)】的数据库操作Service实现
* @createDate 2025-03-03 12:37:18
*/
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice>
    implements NoticeService {

}




