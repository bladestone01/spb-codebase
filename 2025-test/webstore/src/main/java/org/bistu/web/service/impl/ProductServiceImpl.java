package org.bistu.web.service.impl;

import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFactory;
import org.bistu.web.dao.entity.Product;
import org.bistu.web.dao.repository.ProductRepo;
import org.bistu.web.domain.ProductBo;
import org.bistu.web.service.IProductService;
import org.bistu.web.utils.BeanUtil;
import org.springframework.beans.BeanUtils;
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

    @Override
    public List<ProductBo> getOneByNameLike(String name) {
        List<Product> productList = productRepo.listByName(name);
        List<ProductBo> productBoList = BeanUtil.copyListProperties(productList, ProductBo.class);

        return productBoList;
    }

    @Override
    public ProductBo createOne(String name, String description, float price) {
        return null;
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
    public ProductBo updateOne(Long id, ProductBo productBo) {
        Product sourceProduct = BeanUtil.copyProperties(productBo, Product.class);
        Product targetProduct = this.productRepo.getById(id);

        cn.hutool.core.bean.BeanUtil.copyProperties(sourceProduct, targetProduct, CopyOptions.create().ignoreNullValue());

        this.productRepo.updateById(targetProduct);

        return BeanUtil.copyProperties(targetProduct, ProductBo.class);
    }

    @Override
    public Boolean deleteOne(Long id) {
        return this.productRepo.removeById(id);
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
