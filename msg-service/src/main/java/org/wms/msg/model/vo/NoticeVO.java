package org.wms.msg.model.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.wms.msg.model.entity.Notice;

@EqualsAndHashCode(callSuper = true)
@Data
public class NoticeVO extends Notice {
    private String publisherName;

}
