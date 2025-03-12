package org.bistu.web.webstore.controller;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.bistu.web.webstore.domain.ProductBo;
import org.bistu.web.webstore.domain.ResultInfo;
import org.bistu.web.webstore.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/rest")
public class ProductController {
    @Autowired
    private IProductService productService;

    @GetMapping("/products")
    public ResultInfo<ProductBo> getProducts() {
        List<ProductBo> productBoList = this.productService.getOnes();
        log.info("getProducts:{}",  productBoList);

        return ResultInfo.success(productBoList);
    }


    @GetMapping("/products/{id}")
    public ResultInfo<ProductBo> getProduct(@PathVariable(name="id", required=true) Long id) {
        log.info("Get Product Id:{}", id);
        ProductBo productBo = this.productService.getOne(id);
        log.info("Porduct Query {}: {}", id, productBo);

        return ResultInfo.success(productBo);
    }

    @GetMapping("/products/simple/{id}")
    public ResultInfo<ProductBo> getProductSimple(@PathVariable  Long id) {
        log.info("Get Product Id:{}", id);
        ProductBo productBo = this.productService.getOne(id);
        log.info("Product Query {}: {}", id, productBo);

        return ResultInfo.success(productBo);
    }


    @GetMapping("/products/{id}/params")
    public ResultInfo<List<ProductBo>> getProductByParams(@PathVariable Long id,
                  @RequestParam(name = "price", required=false, defaultValue = "0.0") float price)  {
        log.info("Get Producgts By Params, id:{}, price:{}", id, price);
        List<ProductBo> productBoList = this.productService.getOnes(price);
        log.info("Query Product Bos:{}", productBoList);

        return ResultInfo.success(productBoList);
    }

}
