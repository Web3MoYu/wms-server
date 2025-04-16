package org.wms.product.service;

import java.util.List;

import org.wms.common.model.Result;
import org.wms.common.model.vo.CountVo;
import org.wms.product.model.entity.ProductCat;
import org.wms.product.model.vo.ProductCatTree;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author moyu
 * @description 针对表【product_cat(产品分类表)】的数据库操作Service
 * @createDate 2025-03-04 21:34:08
 */
public interface ProductCatService extends IService<ProductCat> {

    /**
     * 获取所有产品类别的树状结构
     *
     * @return 树状结构的产品类别列表
     */
    List<ProductCatTree> getAllCategoryTree();

    /**
     * 删除产品类别
     *
     * @param id
     * @return 删除结果
     */
    Result<String> delete(String id);

    /**
     * 统计产品分类和数量
     *
     * @return 产品分类和数量
     */
    Result<List<CountVo>> countInfo();
}
