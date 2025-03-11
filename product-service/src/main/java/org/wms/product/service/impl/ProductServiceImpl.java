package org.wms.product.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.wms.product.mapper.ProductCatMapper;
import org.wms.product.mapper.ProductMapper;
import org.wms.common.entity.product.Product;
import org.wms.product.model.entity.ProductCat;
import org.wms.product.model.vo.ProductVo;
import org.wms.product.service.ProductService;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import jakarta.annotation.Resource;

/**
 * @author moyu
 * @description 针对表【product(产品表)】的数据库操作Service实现
 * @createDate 2025-03-04 21:33:40
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product>
        implements ProductService {

    @Resource
    ProductMapper productMapper;

    @Resource
    ProductCatMapper productCatMapper;

    @Override
    public Page<ProductVo> pageProductVo(int page, int pageSize, String productName, String categoryId, String brand) {
        Page<Product> pageParam = new Page<>(page, pageSize);

        // 使用LambdaQueryWrapper构建查询条件
        LambdaQueryWrapper<Product> queryWrapper = new LambdaQueryWrapper<>();

        // 构建查询条件
        if (productName != null && !productName.isEmpty()) {
            queryWrapper.like(Product::getProductName, productName);
        }

        if (categoryId != null && !categoryId.isEmpty()) {
            // 获取该分类及其所有子分类的ID列表
            List<String> categoryIds = getAllChildCategoryIds(categoryId);
            if (!categoryIds.isEmpty()) {
                queryWrapper.in(Product::getCategoryId, categoryIds);
            } else {
                // 如果没有找到子分类，则只查询当前分类
                queryWrapper.eq(Product::getCategoryId, categoryId);
            }
        }

        if (brand != null && !brand.isEmpty()) {
            queryWrapper.like(Product::getBrand, brand);
        }

        // 排序（按创建时间降序）
        queryWrapper.orderByDesc(Product::getUpdateTime);

        // 使用自定义查询方法获取带分类全称的产品列表
        return productMapper.selectProductVoPage(pageParam, queryWrapper);
    }

    /**
     * 获取指定分类及其所有子分类的ID列表
     *
     * @param categoryId 父分类ID
     * @return 包含父分类和所有子分类ID的列表
     */
    private List<String> getAllChildCategoryIds(String categoryId) {
        List<String> categoryIds = new ArrayList<>();
        // 添加当前分类ID
        categoryIds.add(categoryId);

        // 递归查询所有子分类
        recursiveGetChildCategories(categoryId, categoryIds);

        return categoryIds;
    }

    /**
     * 递归获取所有子分类ID
     *
     * @param parentId    父分类ID
     * @param categoryIds 结果列表，递归过程中会向此列表添加子分类ID
     */
    private void recursiveGetChildCategories(String parentId, List<String> categoryIds) {
        // 查询直接子分类
        LambdaQueryWrapper<ProductCat> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ProductCat::getParentId, parentId);
        List<ProductCat> childCategories = productCatMapper.selectList(queryWrapper);

        for (ProductCat child : childCategories) {
            // 添加子分类ID
            categoryIds.add(child.getId());
            // 递归查询子分类的子分类
            recursiveGetChildCategories(child.getId(), categoryIds);
        }
    }
}
