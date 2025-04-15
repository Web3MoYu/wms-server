package org.wms.product.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.wms.common.entity.product.Product;
import org.wms.product.model.vo.ProductCatCountVo;
import org.wms.product.model.vo.ProductVo;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
 * @author moyu
 * @description 针对表【product(产品表)】的数据库操作Mapper
 * @createDate 2025-03-04 21:33:40
 * @Entity org.wms.common.entity.product.Product
 */
@Mapper
public interface ProductMapper extends BaseMapper<Product> {

    /**
     * 分页查询产品信息，包含分类全称
     *
     * @param page    分页参数
     * @param wrapper 查询条件
     * @return 包含分类全称的产品信息分页结果
     */
    Page<ProductVo> selectProductVoPage(Page<Product> page, @Param("ew") Wrapper<Product> wrapper);

    /**
     * 根据id获取产品信息
     *
     * @param id 产品id
     * @return 产品信息
     */
    ProductVo selectProductById(String id);

    /**
     * 统计产品分类和数量
     *
     * @return 产品分类和数量
     */
    List<ProductCatCountVo> countCatInfo();

}




