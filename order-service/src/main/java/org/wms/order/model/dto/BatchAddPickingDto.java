package org.wms.order.model.dto;


import lombok.Data;

import java.util.List;

@Data
public class BatchAddPickingDto {
    List<String> ids;
    String picker;
    String remark;
}
