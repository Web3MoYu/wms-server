package org.wms.msg.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.wms.common.entity.msg.Msg;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @author moyu
 * @description 针对表【notif_msg(消息通知表)】的数据库操作Mapper
 * @createDate 2025-03-12 12:23:02
 * @Entity org.wms.common.entity.msg.Msg
 */
@Mapper
public interface MsgMapper extends BaseMapper<Msg> {

}




