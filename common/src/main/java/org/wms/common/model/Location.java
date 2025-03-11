package org.wms.common.model;

import lombok.Data;

import java.util.List;

@Data
public class Location {
    /**
     * 货架id
     */
    private String shelfId;
    /**
     * 占用该货架的的库位id
     */
    private List<String> storageIds;
}
