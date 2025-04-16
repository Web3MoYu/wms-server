package org.wms.location.model.vo;

import lombok.Data;

@Data
public class StorageCountVo {
    private Long totalCount;
    private Long freeCount;
    private Long occupiedCount;
    private Long disabledCount;
}
