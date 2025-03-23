package org.bistu.web.webstore.controller;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.bistu.web.webstore.domain.ProductBo;
import org.bistu.web.webstore.domain.ResultInfo;
import org.bistu.web.webstore.global.exception.BizException;
import org.bistu.web.webstore.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.bistu.web.webstore.consts.StatusCodeEnum;

import java.util.List;
import java.util.Map;
import java.util.Objects;

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
    public ResultInfo<ProductBo> getProduct(@PathVariable(name="id", required=true) Long id) throws Exception {
        log.info("Get Product Id:{}", id);
        ProductBo productBo = this.productService.getOne(id);

        if (Objects.isNull(productBo)) {
            log.error("Invalid ProdcutId:{}", id);
            throw new BizException(StatusCodeEnum.INVALID_PRODUCT_ID.getCode(), StatusCodeEnum.INVALID_PRODUCT_ID.getMessage());
        }
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

    @PostMapping("/products/form")
    public ResultInfo<ProductBo> createProductByForm(@RequestParam String name,
                                                     @RequestParam(required = false, defaultValue = "") String description,
                                                     @RequestParam Float price)  {
        log.info("Get Producgts By Params, name:{}, description:{}, price:{}", name, description, price);

        ProductBo productBo = this.productService.createOne(name, description, price);
        log.info("new created productBo:{}", productBo);

        return ResultInfo.success(productBo);
    }

    @PostMapping("/products/form2")
    public ResultInfo<ProductBo> createProductByForm2(@RequestParam String name, @RequestParam(required = false, defaultValue = "") String description,
                                                     @RequestParam Float price)  {
        log.info("Get Producgts By Params, name:{}, description:{}, price:{}", name, description, price);

        ProductBo productBo = ProductBo.create(name, description, price);
        log.info("new created productBo:{}", productBo);

        return ResultInfo.success(productBo);
    }

    @PostMapping("/products")
    public ResultInfo<ProductBo> addProduct(@RequestBody  ProductBo productBo) {
        log.info("addProduct:{}", productBo);

        ProductBo newProductBo = ProductBo.create(productBo.getName(), productBo.getDescription(), productBo.getPrice());

        return ResultInfo.success(newProductBo);
    }

    @PutMapping("/products/{id}")
    public ResultInfo<ProductBo> updateProduct(@PathVariable Long id, ProductBo productBo) {
        log.info("updateProduct:{}", productBo);

       boolean resultStatus = this.productService.updateOne(id, productBo);

        return ResultInfo.success(resultStatus);
    }

    @PutMapping("/products/json/{id}")
    public ResultInfo<ProductBo> updateJsonProduct(@PathVariable Long id, @RequestBody ProductBo productBo) {
        log.info("updateProduct:{}", productBo);

        ProductBo updateProductBo = this.productService.getOne(id);
        updateProductBo.setPrice(productBo.getPrice());
        updateProductBo.setDescription(productBo.getDescription());
        updateProductBo.setName(productBo.getName());
        log.info("Updated ProductBo:{}", updateProductBo);

        return ResultInfo.success(updateProductBo);
    }

    @DeleteMapping("/products/{id}")
    public ResultInfo<ProductBo> deleteProduct(@PathVariable Long id) {
        log.info("deleteProduct:{}", id);

       Boolean resultStatus = this.productService.deleteOne(id);

        return ResultInfo.success(resultStatus);
    }
}
