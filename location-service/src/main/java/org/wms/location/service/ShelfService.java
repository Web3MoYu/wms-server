package org.wms.location.service;

import org.wms.location.model.entity.Shelf;
import org.wms.location.model.enums.StatusEnums;
import org.wms.location.model.vo.ShelfVo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author moyu
 * @description 针对表【wms_shelf(货架表)】的数据库操作Service
 * @createDate 2025-03-08 17:25:49
 */
public interface ShelfService extends IService<Shelf> {

    /**
     * 分页查询货架信息，包含区域名称
     *
     * @param page      页码
     * @param pageSize  每页条数
     * @param shelfName 货架名称（可选，模糊查询）
     * @param areaId    区域ID（可选）
     * @param status    状态（可选，null表示查询所有）
     * @return 包含区域名称的货架分页结果
     */
    Page<ShelfVo> pageShelfVos(int page, int pageSize, String shelfName, String areaId, Integer status);

    /**
     * 更新货架状态
     *
     * @param id     货架ID
     * @param status 新状态
     * @return 是否更新成功
     */
    boolean updateStatus(String id, StatusEnums status);

    /**
     * 检查货架编码是否存在
     *
     * @param shelfCode 货架编码
     * @return 是否存在
     */
    boolean checkShelfCode(String areaId, String shelfCode);

    /**
     * 检查货架名称是否存在
     * @param areaId 区域id
     * @param shelfName 名称
     * @return 检查结果
     */
    boolean checkShelfName(String areaId, String shelfName);
}
