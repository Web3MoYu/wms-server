package org.wms.product.service;

import org.wms.common.entity.product.Product;
import org.wms.common.model.Result;
import org.wms.product.model.vo.ProductVo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author moyu
 * @description 针对表【product(产品表)】的数据库操作Service
 * @createDate 2025-03-04 21:33:40
 */
public interface ProductService extends IService<Product> {
    /**
     * 分页查询产品信息，包含分类全称
     *
     * @param page        页码
     * @param pageSize    每页条数
     * @param productName 产品名称（可选）
     * @param categoryId  分类ID（可选）
     * @param brand       品牌（可选）
     * @return 包含分类全称的产品分页结果
     */
    Page<ProductVo> pageProductVo(int page, int pageSize, String productName, String categoryId, String brand);

    Result<Boolean> updateProduct(Product product, String id);
}
