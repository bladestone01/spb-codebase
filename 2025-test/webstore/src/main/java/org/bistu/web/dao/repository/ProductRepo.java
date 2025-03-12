package org.bistu.web.dao.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.bistu.web.webstore.dao.entity.Product;
import org.bistu.web.webstore.dao.mapper.ProductMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepo extends ServiceImpl<ProductMapper, Product> {


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
}