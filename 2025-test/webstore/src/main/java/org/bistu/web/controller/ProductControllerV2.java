package org.bistu.web.controller;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.bistu.web.consts.StatusCodeEnum;
import org.bistu.web.domain.ProductBo;
import org.bistu.web.domain.ResultInfo;
import org.bistu.web.global.exception.BizException;
import org.bistu.web.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@Slf4j
@RequestMapping("/webstorev2")
public class ProductControllerV2 {

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
    public ResultInfo<List<ProductBo>> getProductByParams(@PathVariable Long id, @RequestParam(name = "price", required=false, defaultValue = "0.0") float price)  {
        log.info("Get Producgts By Params, id:{}, price:{}", id, price);
        List<ProductBo> productBoList = this.productService.getOnesByPrice(price);
        log.info("Query Product Bos:{}", productBoList);

        return ResultInfo.success(productBoList);
    }

    /**
     * @RequestParam可以省略示例.
     *
     * @param id
     * @param price
     * @return
     */
    @GetMapping("/products/simple/{id}/params")
    public ResultInfo<List<ProductBo>> getProductSimpleByParams(@PathVariable Long id, Float price)  {
        log.info("Get Producgts By Params, id:{}, price:{}", id, price);
        List<ProductBo> productBoList = this.productService.getOnesByPrice(price);
        log.info("Query Product Bos:{}", productBoList);

        return ResultInfo.success(productBoList);
    }

    @PostMapping("/products/form")
    public ResultInfo<ProductBo> createProductByForm(@RequestParam String name, @RequestParam(required = false, defaultValue = "") String description,
                                                     @RequestParam Float price)  {
        log.info("Get Producgts By Params, name:{}, description:{}, price:{}", name, description, price);

        ProductBo productBo = this.productService.createOne(name, description, price);
        log.info("new created productBo:{}", productBo);

        return ResultInfo.success(productBo);
    }

    @PostMapping("/products")
    public ResultInfo<ProductBo> addProduct(@RequestBody  ProductBo productBo) {
        log.info("addProduct:{}", productBo);

        ProductBo newProductBo = this.productService.createOne(productBo);
        log.info("Created ProductBo:{}", newProductBo);

        return ResultInfo.success(newProductBo);
    }

    @PostMapping("/products/simple")
    public ResultInfo<ProductBo> addSimpleProduct(ProductBo productBo) {
        log.info("addProduct:{}", productBo);

        ProductBo newProductBo = this.productService.createOne(productBo);
        log.info("Created ProductBo:{}", newProductBo);

        return ResultInfo.success(newProductBo);
    }


    @PutMapping("/products/{id}")
    public ResultInfo<ProductBo> updateProduct(@PathVariable Long id, @RequestBody ProductBo productBo) {
        log.info("updateProduct:{}", productBo);

        ProductBo updateProductBo = this.productService.updateOne(id, productBo);
        log.info("re-update ProductBo:{}", updateProductBo);

        return ResultInfo.success(updateProductBo);
    }

    @PutMapping("/products/json/{id}")
    public ResultInfo<ProductBo> updateJsonProduct(@PathVariable Long id, @RequestBody ProductBo productBo) {
        log.info("updateProduct:{}", productBo);

        ProductBo updateProductBo = this.productService.updateOne(id, productBo);
        log.info("Updated ProductBo:{}", updateProductBo);

        log.info("re-update ProductBo:{}", updateProductBo);

        return ResultInfo.success(updateProductBo);
    }

    @DeleteMapping("/products/{id}")
    public ResultInfo<ProductBo> deleteProduct(@PathVariable Long id) {
        log.info("deleteProduct:{}", id);

        Boolean resultStatus = this.productService.deleteOne(id);
        log.info("Deleted ProductBo:{}", resultStatus);

        return ResultInfo.success(resultStatus);
    }
}
