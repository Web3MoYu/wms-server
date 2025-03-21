package org.wms.product.cotroller;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.seata.spring.annotation.GlobalTransactional;
import org.springframework.security.access.prepost.PreAuthorize;
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
import org.wms.common.entity.product.Product;
import org.wms.common.utils.IdGenerate;
import org.wms.product.model.vo.ProductVo;
import org.wms.product.service.ProductService;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import jakarta.annotation.Resource;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Resource
    private ProductService productService;

    @Resource
    private IdGenerate idGenerate;

    /**
     * 分页查询产品信息，包含分类全称
     *
     * @param page        页码，默认为1
     * @param pageSize    每页条数，默认为10
     * @param productName 产品名称（可选）
     * @param categoryId  分类ID（可选）
     * @param brand       品牌（可选）
     * @return 包含分类全称的产品分页结果
     */
    @PreAuthorize("hasAuthority('product:list')")
    @GetMapping("/page")
    public Result<Page<ProductVo>> pageProducts(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false) String productName,
            @RequestParam(required = false) String categoryId,
            @RequestParam(required = false) String brand) {

        Page<ProductVo> result = productService.pageProductVo(page, pageSize, productName, categoryId, brand);
        return Result.success(result, "查询成功");
    }

    /**
     * 根据产品编码，查询产品是否存在
     *
     * @param productCode 产品编码
     * @return 是否存在
     */
    @PreAuthorize("hasAuthority('product:list')")
    @GetMapping("/checkCode")
    public Result<Boolean> existsByProductCode(@RequestParam String productCode) {
        boolean exists = productService.lambdaQuery().eq(Product::getProductCode, productCode).exists();
        return Result.success(exists, "查询成功");
    }

    /**
     * 根据id删除产品
     *
     * @param id 产品id
     * @return 是否删除成功
     */
    @PreAuthorize("hasAuthority('product:delete')")
    @DeleteMapping("/{id}")
    public Result<Boolean> deleteProductById(@PathVariable String id) {
        boolean result = productService.removeById(id);
        if (result) {
            return Result.success(null, "删除成功");
        }
        return Result.error(500, "删除失败");
    }

    /**
     * 新增产品
     *
     * @param product 产品
     * @return 是否新增成功
     */
    @PreAuthorize("hasAuthority('product:add')")
    @PostMapping
    public Result<Boolean> addProduct(@RequestBody Product product) {
        product.setCreateTime(LocalDateTime.now());
        product.setUpdateTime(LocalDateTime.now());
        boolean result = productService.save(product);
        if (result) {
            return Result.success(null, "新增成功");
        }
        return Result.error(500, "新增失败");
    }

    /**
     * 更新产品
     *
     * @param product 产品
     * @return 是否更新成功
     */
    @PreAuthorize("hasAuthority('product:update')")
    @PutMapping("/{id}")
    @GlobalTransactional
    public Result<Boolean> updateProduct(@RequestBody Product product, @PathVariable String id) {
        return productService.updateProduct(product, id);
    }

    /**
     * 模糊查询产品名称
     *
     * @param productName 产品名称
     * @return 查询结果
     */
    @GetMapping("/list/{productName}")
    @PreAuthorize("isAuthenticated()")
    public Result<List<Product>> listProducts(@PathVariable String productName) {
        List<Product> list = productService.lambdaQuery()
                .like(Product::getProductName, productName).list();
        return Result.success(list, "查询成功");
    }

    @GetMapping("/batchNumber")
    @PreAuthorize("isAuthenticated()")
    public Result<String> getBatchNumber() {
        return Result.success(idGenerate.generateBatchNo(), "生成成功");
    }

}
