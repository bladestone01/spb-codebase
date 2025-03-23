package org.bistu.web.webstore.dao.repo;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.micrometer.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.bistu.web.webstore.dao.entity.Product;
import org.bistu.web.webstore.dao.mapper.ProductMapper;
import org.bistu.web.webstore.dao.mapper.ext.ProductXMapper;
import org.bistu.web.webstore.domain.ProductBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 * ServiceImpl实现了基础的增删改查动作，来自于MyBatisPlus。
 *
 */
@Repository
@Slf4j
public class ProductRepository extends ServiceImpl<ProductMapper, Product> {
    @Autowired
    private ProductXMapper productXMapper;

    /**
     * 根据价格查询商品
     * @param price
     * @return
     */
    public List<Product> listByPrice(float price) {
        LambdaQueryWrapper<Product> queryWrapper = Wrappers.lambdaQuery(Product.class);
        queryWrapper.gt(Product::getPrice, price);

        return this.list(queryWrapper);
    }

    public List<Product> listByName(String name) {
        return this.productXMapper.getByName(name);
    }


    /**
     * Custom MyBatis Method
     */

    public int updateByName(Product product) {
        return this.productXMapper.updateByName(product);
    }

    public int deleteByName(String name) {
        return this.productXMapper.deleteByName(name);
    }

    public int logicDeleteByName(String name) {
        return this.productXMapper.logicDeleteByName(name);
    }

    public int createOne(Product product) {
        return this.productXMapper.createOne(product);
    }

    /**
     * 分页查询方法.
     *
     * @param page
     * @param queryProduct
     * @return
     */
    public IPage<Product> queryByPageable(IPage<Product> page, ProductBo queryProduct) {
        LambdaQueryWrapper<Product> queryWrapper = Wrappers.lambdaQuery(Product.class);
        queryWrapper.gt(Product::getPrice, Objects.nonNull(queryProduct.getPrice()) ? queryProduct.getPrice() : BigDecimal.ZERO);
        if (StringUtils.isNotBlank(queryProduct.getName())) {
            queryWrapper.like(Product::getName, queryProduct.getName());
        }

        return this.page(page, queryWrapper);
    }
}
