package org.wms.product.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.wms.common.model.Result;
import org.wms.product.mapper.ProductCatMapper;
import org.wms.product.model.entity.Product;
import org.wms.product.model.entity.ProductCat;
import org.wms.product.model.vo.ProductCatTree;
import org.wms.product.service.ProductCatService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.wms.product.service.ProductService;

/**
 * @author moyu
 * @description 针对表【product_cat(产品分类表)】的数据库操作Service实现
 * @createDate 2025-03-04 21:34:08
 */
@Service
public class ProductCatServiceImpl extends ServiceImpl<ProductCatMapper, ProductCat>
        implements ProductCatService {

    @Resource
    ProductService productService;

    @Override
    public List<ProductCatTree> getAllCategoryTree() {
        // 查询所有产品类别
        List<ProductCat> categories = this.list();

        // 创建一个Map，用于存储类别ID和对应的树节点
        Map<String, ProductCatTree> categoryTreeMap = new HashMap<>();

        // 创建结果列表，用于存储顶级类别
        List<ProductCatTree> resultList = new ArrayList<>();

        // 将所有类别转换为树节点并存入Map中
        for (ProductCat category : categories) {
            ProductCatTree catTree = new ProductCatTree();
            catTree.setCategory(category);

            // 如果是顶级类别（parentId为空或为0），则加入结果列表
            if (category.getParentId() == null || "0".equals(category.getParentId())) {
                resultList.add(catTree);
            }

            // 所有节点都放入Map中，便于后续查找
            categoryTreeMap.put(category.getId(), catTree);
        }

        // 构建树形结构，为每个非顶级节点找到其父节点并添加为子节点
        for (ProductCat category : categories) {
            // 跳过顶级节点
            if (category.getParentId() != null && !"0".equals(category.getParentId())) {
                ProductCatTree parentTree = categoryTreeMap.get(category.getParentId());
                if (parentTree != null) {
                    if (parentTree.getChildren() == null) {
                        parentTree.setChildren(new ArrayList<>());
                    }
                    parentTree.getChildren().add(categoryTreeMap.get(category.getId()));
                }
            }
        }

        // 对树进行排序
        sortCategoryTree(resultList);

        return resultList;
    }

    /**
     * 递归对类别树进行排序
     *
     * @param catTreeList 要排序的类别树列表
     */
    private void sortCategoryTree(List<ProductCatTree> catTreeList) {
        if (catTreeList == null || catTreeList.isEmpty()) {
            return;
        }

        // 根据sort字段排序
        catTreeList.sort(Comparator.comparing(tree -> tree.getCategory().getSort(), Comparator.nullsLast(Comparator.naturalOrder())));

        // 递归排序子节点
        for (ProductCatTree tree : catTreeList) {
            if (tree.getChildren() != null && !tree.getChildren().isEmpty()) {
                sortCategoryTree(tree.getChildren());
            }
        }
    }

    @Override
    public Result<String> delete(String id) {
        // 判断是否存在子类别 
        boolean hasChildren = this.lambdaQuery().eq(ProductCat::getParentId, id).exists();
        if (hasChildren) {
            return Result.error(500, "存在子类别，无法删除");
        }
        // 是否有产品
        boolean hasProduct = productService.lambdaQuery().eq(Product::getCategoryId, id).exists();
        if (hasProduct) {
            return Result.error(500, "存在产品，无法删除");
        }
        boolean remove = this.removeById(id);
        if (remove) {
            return Result.success(null, "删除成功");
        }
        return Result.error(500, "删除失败");
    }
}
