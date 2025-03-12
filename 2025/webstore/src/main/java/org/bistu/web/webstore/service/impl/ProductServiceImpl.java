package org.bistu.web.webstore.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.bistu.web.webstore.dao.entity.Product;
import org.bistu.web.webstore.dao.repository.ProductRepo;
import org.bistu.web.webstore.domain.ProductBo;
import org.bistu.web.webstore.service.IProductService;
import org.bistu.web.webstore.utils.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProductServiceImpl implements IProductService {
    @Autowired
    private ProductRepo productRepo;

    @Override
    public List<ProductBo> getOnes() {
        List<Product> productList = productRepo.list();

        List<ProductBo> productBos =  BeanUtil.copyListProperties(productList, ProductBo.class);
        log.info("Get All Products:{}",productBos);

        return productBos;
    }

    @Override
    public ProductBo getOne(Long id) {
        Optional<Product> productOpt =  this.productRepo.getOptById(id);
        ProductBo productBo = null;
        if (productOpt.isPresent()) {
            productBo = BeanUtil.copyProperties(productOpt.get(), ProductBo.class);
        }
        log.info("ProductBo :{} by ID:{}", productBo, id);

        return productBo;
    }

    @Override
    public List<ProductBo> getOnesByPrice(float price) {
        List<Product> productList = productRepo.listByPrice(price);
        List<ProductBo> productBoList = BeanUtil.copyListProperties(productList, ProductBo.class);

        log.info("Get Ones: {} By Price {}", productBoList, price);

        return productBoList;
    }
}
