package org.wms.msg.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.wms.msg.model.dto.NoticePageDTO;
import org.wms.msg.model.entity.Notice;
import org.wms.msg.model.vo.NoticeVO;

/**
 * @author moyu
 * @description 针对表【notif_notice(公告表)】的数据库操作Mapper
 * @createDate 2025-03-03 12:37:18
 * @Entity org.wms.msg.entity.NotifNotice
 */
@Mapper
public interface NoticeMapper extends BaseMapper<Notice> {

    /**
     * 分页查询公告列表
     *
     * @param noticeVOPage 公告分页结果
     * @param param        查询参数
     * @return 公告分页结果
     */
    Page<NoticeVO> pageList(@Param("page") Page<NoticeVO> noticeVOPage, @Param("param") NoticePageDTO param);
}




