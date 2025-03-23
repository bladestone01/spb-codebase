package org.bistu.web.webstore.service.impl;

import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.bistu.web.webstore.dao.entity.Product;
import org.bistu.web.webstore.dao.repo.ProductRepository;
import org.bistu.web.webstore.domain.ProductBo;
import org.bistu.web.webstore.service.IProductService;
import org.bistu.web.webstore.utils.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProductServiceImpl implements IProductService {
    @Autowired
    private ProductRepository productRepo;

    @Override
    public List<ProductBo> getOnes() {
        List<Product> productList = productRepo.list();

        List<ProductBo> productBos =  BeanUtil.copyListProperties(productList, ProductBo.class);
        log.info("Get All Products:{}",productBos);

        return productBos;
    }

    @Override
    public List<ProductBo> getOnes(Float price) {
        List<Product> productList = productRepo.listByPrice(price);
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
    public ProductBo createOne(String name, String description, float price) {
        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setPrice(BigDecimal.valueOf(price));
        productRepo.save(product);
        ProductBo newProductBo = BeanUtil.copyProperties(product, ProductBo.class);

        log.info("New Created ProductBo:{}", newProductBo);
        return newProductBo;
    }

    @Override
    public ProductBo createOne(ProductBo productBo) {
        Product product = BeanUtil.copyProperties(productBo, Product.class);
        productRepo.save(product);
        ProductBo newProductBo = BeanUtil.copyProperties(product, ProductBo.class);
        log.info("New Created ProductBo:{}", newProductBo);

        return newProductBo;
    }

    @Override
    public boolean updateOne(Long id, ProductBo productBo) {
        Product sourceProduct = BeanUtil.copyProperties(productBo, Product.class);
        Product targetProduct = this.productRepo.getById(id);

        cn.hutool.core.bean.BeanUtil.copyProperties(sourceProduct, targetProduct, CopyOptions.create().ignoreNullValue());

        return this.productRepo.updateById(targetProduct);
    }

    @Override
    public boolean deleteOne(Long id) {
        return this.productRepo.removeById(id);
    }


    @Override
    public List<ProductBo> getOneByNameLike(String name) {
        List<Product> productList = productRepo.listByName(name);
        List<ProductBo> productBoList = BeanUtil.copyListProperties(productList, ProductBo.class);

        return productBoList;
    }


    /**
     *  Custom MyBatis Method
     */
    @Override
    public int updateCustomByName(ProductBo productBo) {
        log.info("Update Custom by Name,  productBo:{}", productBo);
        Product product = BeanUtil.copyProperties(productBo, Product.class);
        return this.productRepo.updateByName(product);
    }

    @Override
    public boolean deleteCustomByName(String name,  Boolean logicFlag) {
        log.info("delete Action, name:{}, logicFlag:{}", name, logicFlag);
        int count = logicFlag? this.productRepo.logicDeleteByName(name) : this.productRepo.deleteByName(name);
        log.info("delete count:{}", count);
        return count > 0;
    }

    @Override
    public ProductBo createCustomOne(ProductBo productBo) {
        return this.createOne(productBo);
    }

    @Override
    public IPage<ProductBo> getOnesByPageable(Integer pageNo, Integer pageSize, ProductBo queryProductBo) {
        IPage<Product> page = new Page<>(pageNo, pageSize);

        IPage<Product> productIPage = this.productRepo.queryByPageable(page, queryProductBo);

        List<Product> productList = productIPage.getRecords();
        List<ProductBo> productBoList = BeanUtil.copyListProperties(productList, ProductBo.class);

        return new Page<ProductBo>()
                .setRecords(productBoList)
                .setTotal(productIPage.getTotal())
                .setSize(productIPage.getSize())
                .setCurrent(productIPage.getCurrent());
    }
}