package org.wms.product.cotroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.wms.common.model.Result;
import org.wms.product.model.vo.ProductVo;
import org.wms.product.service.ProductService;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import jakarta.annotation.Resource;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Resource
    private ProductService productService;

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
}
