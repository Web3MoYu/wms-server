package org.wms.common.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.wms.common.entity.location.Shelf;
import org.wms.common.entity.location.Storage;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocationInfo {
    Shelf shelf; // 货架
    List<Storage> storages; // 该货架所对应的库位信息
}
