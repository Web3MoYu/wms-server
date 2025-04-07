package org.wms.product.cotroller;

import java.time.LocalDate;
import java.util.List;

import cn.hutool.core.util.StrUtil;
import io.micrometer.common.util.StringUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wms.common.model.Result;
import org.wms.product.model.entity.ProductCat;
import org.wms.product.model.vo.ProductCatTree;
import org.wms.product.service.ProductCatService;

import jakarta.annotation.Resource;

@RestController
@RequestMapping("/product/cat")
public class ProductCatController {

    @Resource
    private ProductCatService productCatService;

    /**
     * 查询产品类别树状结构
     *
     * @return 树状结构的产品类别列表
     */
    @GetMapping("/tree")
    @PreAuthorize("hasAuthority('product:cat:list') or hasAuthority('order:in-out:add')")
    public Result<List<ProductCatTree>> getCategoryTree() {
        List<ProductCatTree> categoryTree = productCatService.getAllCategoryTree();
        return Result.success(categoryTree, "查询成功");
    }

    /**
     * 检查categoryCode是否存在
     *
     * @param categoryCode
     * @return
     */
    @GetMapping("/check/{parent}/{categoryCode}")
    @PreAuthorize("hasAuthority('product:cat:list')")
    public Result<Boolean> checkCategoryCode(@PathVariable String parent, @PathVariable String categoryCode) {
        boolean exists = productCatService.lambdaQuery()
                .eq(ProductCat::getCategoryCode, categoryCode)
                .eq(!StrUtil.equals(parent, "null"), ProductCat::getParentId, parent)
                .exists();
        return Result.success(exists, "检查成功");
    }

    /**
     * 添加产品类别
     *
     * @param productCat
     * @return 添加结果
     */
    @PostMapping("/add")
    @PreAuthorize("hasAuthority('product:cat:add')")
    public Result<String> add(@RequestBody ProductCat productCat) {
        productCat.setCreateTime(LocalDate.now());
        productCat.setUpdateTime(LocalDate.now());
        if (StringUtils.isEmpty(productCat.getParentId())) {
            productCat.setParentId(null);
        }
        boolean save = productCatService.save(productCat);
        if (save) {
            return Result.success(null, "添加成功");
        }
        return Result.error(500, "添加失败");
    }

    /**
     * 更新产品类别
     *
     * @param productCat
     * @return 更新结果
     */
    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('product:cat:update')")
    public Result<String> update(@RequestBody ProductCat productCat, @PathVariable String id) {
        productCat.setUpdateTime(LocalDate.now());
        boolean update = productCatService.updateById(productCat);
        if (update) {
            return Result.success(null, "更新成功");
        }
        return Result.error(500, "更新失败");
    }

    /**
     * 删除产品类别
     *
     * @param id
     * @return 删除结果
     */
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('product:cat:delete')")
    public Result<String> delete(@PathVariable String id) {
        return productCatService.delete(id);
    }

}
