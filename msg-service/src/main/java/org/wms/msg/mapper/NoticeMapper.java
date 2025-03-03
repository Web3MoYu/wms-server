package org.wms.msg.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.wms.msg.model.entity.Notice;

/**
* @author moyu
* @description 针对表【notif_notice(公告表)】的数据库操作Mapper
* @createDate 2025-03-03 12:37:18
* @Entity org.wms.msg.entity.NotifNotice
*/
@Mapper
public interface NoticeMapper extends BaseMapper<Notice> {

}




