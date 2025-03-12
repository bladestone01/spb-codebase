package org.bistu.web.webstore.service.impl;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.bistu.web.webstore.domain.ProductBo;
import org.bistu.web.webstore.service.IProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class ProductServiceImpl implements IProductService {
    private Map<Long, ProductBo> productsMap = new java.util.concurrent.ConcurrentHashMap<Long, ProductBo>();

    @PostConstruct
    public void init() {
        ProductBo productBo1 = ProductBo.create();
        productsMap.put(productBo1.getId(), productBo1);

        ProductBo productBo2 = ProductBo.create();
        productsMap.put(productBo2.getId(), productBo2);

        ProductBo productBo3 = ProductBo.create();
        productsMap.put(productBo3.getId(), productBo3);

        log.info("product bo list is initialized....");
    }

    @Override
    public ProductBo getOne(Long id) {
        ProductBo productBo = productsMap.get(id);

        return productBo;
    }

    @Override
    public List<ProductBo> getOnes() {
        List<ProductBo> productBoList = productsMap.values().stream().toList();

        return productBoList;
    }

    @Override
    public List<ProductBo> getOnes(Float price) {
        log.info("Get Ones By Price:{}", price);
        List<ProductBo> productBoList = productsMap.values().stream().filter(productBo -> productBo.getPrice() > price).toList();

        return productBoList;
    }

    @Override
    public ProductBo createOne(ProductBo productBo) {
        ProductBo newProductBo = ProductBo.create(productBo.getName(), productBo.getDescription(), productBo.getPrice());
        productsMap.put(productBo.getId(), productBo);

        return productBo;
    }

    @Override
    public ProductBo createOne(String name, String description, float price) {
        ProductBo newProductBo = ProductBo.create(name, description, price);
        productsMap.put(newProductBo.getId(), newProductBo);

        return newProductBo;
    }


    @Override
    public ProductBo udpateOne(Long id, ProductBo productBo) {
        ProductBo updateProductBo = productsMap.get(id);
        updateProductBo.setPrice(productBo.getPrice());
        updateProductBo.setDescription(productBo.getDescription());
        updateProductBo.setName(productBo.getName());

        return updateProductBo;
    }

    @Override
    public ProductBo deleteOne(Long id) {
        ProductBo deleteProductBo = productsMap.remove(id);

        return deleteProductBo;
    }
}
