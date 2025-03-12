package org.wms.msg.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.wms.common.entity.msg.Msg;
import org.wms.msg.service.MsgService;
import org.wms.msg.mapper.MsgMapper;
import org.springframework.stereotype.Service;

/**
* @author moyu
* @description 针对表【notif_msg(消息通知表)】的数据库操作Service实现
* @createDate 2025-03-12 12:23:02
*/
@Service
public class MsgServiceImpl extends ServiceImpl<MsgMapper, Msg>
    implements MsgService{

}




