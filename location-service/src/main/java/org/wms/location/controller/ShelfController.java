package org.wms.location.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.wms.common.model.Result;
import org.wms.location.model.entity.Shelf;
import org.wms.common.entity.location.StatusEnums;
import org.wms.location.model.vo.ShelfVo;
import org.wms.location.service.ShelfService;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import jakarta.annotation.Resource;

@RestController
@RequestMapping("/location/shelf")
public class ShelfController {

    @Resource
    private ShelfService shelfService;

    /**
     * 分页查询货架信息，包含区域名称
     *
     * @param page      页码，默认为1
     * @param pageSize  每页条数，默认为10
     * @param shelfName 货架名称（可选，模糊查询）
     * @param areaId    区域ID（可选）
     * @param status    状态（可选，null表示查询所有）
     * @return 货架分页结果，包含区域名称
     */
    @PreAuthorize("hasAuthority('location:shelf:list')")
    @GetMapping("/page")
    public Result<Page<ShelfVo>> pageShelves(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false) String shelfName,
            @RequestParam(required = false) String areaId,
            @RequestParam(required = false) Integer status) {

        Page<ShelfVo> result = shelfService.pageShelfVos(page, pageSize, shelfName, areaId, status);
        return Result.success(result, "查询成功");
    }

    /**
     * 新增货架
     *
     * @param shelf 货架信息
     * @return 添加结果
     */
    @PreAuthorize("hasAuthority('location:shelf:add')")
    @PostMapping
    public Result<String> addShelf(@RequestBody Shelf shelf) {
        // 设置创建时间和更新时间
        shelf.setCreateTime(LocalDate.now());
        shelf.setUpdateTime(LocalDate.now());

        // 如果状态为空，默认设置为启用
        if (shelf.getStatus() == null) {
            shelf.setStatus(StatusEnums.ENABLED);
        }

        boolean saved = shelfService.save(shelf);
        if (saved) {
            return Result.success(null, "添加成功");
        } else {
            return Result.error(500, "添加失败");
        }
    }

    /**
     * 修改货架信息
     *
     * @param shelf 货架信息
     * @param id    货架ID
     * @return 修改结果
     */
    @PreAuthorize("hasAuthority('location:shelf:update')")
    @PutMapping("/{id}")
    public Result<String> updateShelf(@RequestBody Shelf shelf, @PathVariable String id) {
        // 设置ID和更新时间
        shelf.setId(id);
        shelf.setUpdateTime(LocalDate.now());

        // 执行更新操作
        boolean updated = shelfService.updateById(shelf);
        if (updated) {
            return Result.success(null, "修改成功");
        } else {
            return Result.error(500, "修改失败");
        }
    }

    /**
     * 删除货架（逻辑删除，实际是将状态设置为禁用）
     *
     * @param id 货架ID
     * @return 删除结果
     */
    @PreAuthorize("hasAuthority('location:shelf:delete')")
    @DeleteMapping("/{id}")
    public Result<String> deleteShelf(@PathVariable String id) {
        // 将状态设置为禁用，实现逻辑删除
        boolean updated = shelfService.updateStatus(id, StatusEnums.DISABLED);
        if (updated) {
            return Result.success(null, "删除成功");
        } else {
            return Result.error(500, "删除失败");
        }
    }

    /**
     * 检查ShelfCode是否重复
     *
     * @param shelfCode 货架编码
     * @return 检查结果
     */
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/checkShelfCode/{areaId}")
    public Result<Boolean> checkShelfCode(@RequestParam String shelfCode, @PathVariable String areaId) {
        boolean exists = shelfService.checkShelfCode(areaId, shelfCode);
        return Result.success(exists, "检查成功");
    }

    /**
     * 检查ShelfCode是否重复
     *
     * @param shelfName 货架名称
     * @return 检查结果
     */
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/checkShelfName/{areaId}")
    public Result<Boolean> checkShelfName(@RequestParam String shelfName, @PathVariable String areaId) {
        boolean exists = shelfService.checkShelfName(areaId, shelfName);
        return Result.success(exists, "检查成功");
    }

    /**
     * 获取所有货架的信息
     *
     * @return 返回所有货架的集合
     */
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/getShelves/{areaId}")
    public Result<List<Shelf>> getShelves(@PathVariable String areaId) {
        List<Shelf> list = shelfService.lambdaQuery()
                .eq(Shelf::getAreaId, areaId)
                .eq(Shelf::getStatus, StatusEnums.ENABLED.getCode()).list();
        return Result.success(list, "查询成功");
    }

    /**
     * 获取该还存在空闲库位的货架
     *
     * @param areaId 区域id
     */
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/getFreeShelves/{areaId}")
    public Result<List<Shelf>> getFreeShelves(@PathVariable String areaId) {
        List<Shelf> list = shelfService.listFreeShelves(areaId);
        return Result.success(list, "查询成功");
    }

    /**
     * 批量新增货架
     */
    @PreAuthorize("hasAuthority('location:shelf:add')")
    @PostMapping("/batchAdd")
    @Transactional(rollbackFor = Exception.class)
    public Result<String> batchAddShelves(@RequestBody List<Shelf> shelves) {
        shelves.forEach(shelf -> {
            shelf.setCreateTime(LocalDate.now());
            shelf.setUpdateTime(LocalDate.now());
        });
        boolean saved = shelfService.saveBatch(shelves);
        if (saved) {
            return Result.success(null, "添加成功");
        } else {
            return Result.error(500, "添加失败");
        }
    }

}
