package org.bistu.web.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.bistu.web.consts.StatusCodeEnum;
import org.bistu.web.domain.ProductBo;
import org.bistu.web.domain.ResultInfo;
import org.bistu.web.global.exception.BizException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentMap;

@RestController
@Slf4j
@RequestMapping("/webstore")
public class ProductController {
    private Map<Long, ProductBo> productsMap = new java.util.concurrent.ConcurrentHashMap<Long, ProductBo>();

    @PostConstruct
    public void init() {
        ProductBo productBo1 = ProductBo.create();
        productsMap.put(productBo1.getId(), productBo1);

        ProductBo productBo2 = ProductBo.create();
        productsMap.put(productBo2.getId(), productBo2);

        ProductBo productBo3 = ProductBo.create();
        productsMap.put(productBo3.getId(), productBo3);
    }

    @GetMapping("/products")
    public ResultInfo<ProductBo> getProducts() {
        List<ProductBo> productBoList = productsMap.values().stream().toList();
        log.info("getProducts:{}",  productBoList);

        return ResultInfo.success(productBoList);
    }

    @GetMapping("/products/{id}")
    public ResultInfo<ProductBo> getProduct(@PathVariable(name="id", required=true) Long id) throws Exception {
        log.info("Get Product Id:{}", id);
        ProductBo productBo = productsMap.get(id);

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
        ProductBo productBo = productsMap.get(id);
        log.info("Product Query {}: {}", id, productBo);

        return ResultInfo.success(productBo);
    }

    @GetMapping("/products/{id}/params")
    public ResultInfo<List<ProductBo>> getProductByParams(@PathVariable Long id, @RequestParam(name = "price", required=false, defaultValue = "0.0") float price)  {
        log.info("Get Producgts By Params, id:{}, price:{}", id, price);
        List<ProductBo> productBoList = productsMap.values().stream().filter(productBo -> productBo.getPrice() > price).toList();
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
        List<ProductBo> productBoList = productsMap.values().stream().filter(productBo -> productBo.getPrice() > price).toList();
        log.info("Query Product Bos:{}", productBoList);

        return ResultInfo.success(productBoList);
    }

    @PostMapping("/products/form")
    public ResultInfo<ProductBo> createProductByForm(@RequestParam String name, @RequestParam(required = false, defaultValue = "") String description,
                                                     @RequestParam Float price)  {
        log.info("Get Producgts By Params, name:{}, description:{}, price:{}", name, description, price);

        ProductBo productBo = ProductBo.create(name, description, price);
        productsMap.put(productBo.getId(), productBo);
        log.info("new created productBo:{}", productBo);

        return ResultInfo.success(productBo);
    }

    @PostMapping("/products")
    public ResultInfo<ProductBo> addProduct(@RequestBody  ProductBo productBo) {
        log.info("addProduct:{}", productBo);

        ProductBo newProductBo = ProductBo.create(productBo.getName(), productBo.getDescription(), productBo.getPrice());
        productsMap.put(newProductBo.getId(), newProductBo);
        log.info("Created ProductBo:{}", newProductBo);

        return ResultInfo.success(newProductBo);
    }

    @PostMapping("/products/simple")
    public ResultInfo<ProductBo> addSimpleProduct(ProductBo productBo) {
        log.info("addProduct:{}", productBo);

        ProductBo newProductBo = ProductBo.create(productBo.getName(), productBo.getDescription(), productBo.getPrice());
        productsMap.put(newProductBo.getId(), newProductBo);
        log.info("Created ProductBo:{}", newProductBo);

        return ResultInfo.success(newProductBo);
    }


    @PutMapping("/products/{id}")
    public ResultInfo<ProductBo> updateProduct(@PathVariable Long id, ProductBo productBo) {
        log.info("updateProduct:{}", productBo);

        ProductBo updateProductBo = productsMap.get(id);
        updateProductBo.setPrice(productBo.getPrice());
        updateProductBo.setDescription(productBo.getDescription());
        updateProductBo.setName(productBo.getName());
        log.info("Updated ProductBo:{}", updateProductBo);

        log.info("re-update ProductBo:{}", productsMap.get(id));

        return ResultInfo.success(updateProductBo);
    }

    @PutMapping("/products/json/{id}")
    public ResultInfo<ProductBo> updateJsonProduct(@PathVariable Long id, @RequestBody ProductBo productBo) {
        log.info("updateProduct:{}", productBo);

        ProductBo updateProductBo = productsMap.get(id);
        updateProductBo.setPrice(productBo.getPrice());
        updateProductBo.setDescription(productBo.getDescription());
        updateProductBo.setName(productBo.getName());
        log.info("Updated ProductBo:{}", updateProductBo);

        log.info("re-update ProductBo:{}", productsMap.get(id));

        return ResultInfo.success(updateProductBo);
    }

    @DeleteMapping("/products/{id}")
    public ResultInfo<ProductBo> deleteProduct(@PathVariable Long id) {
        log.info("deleteProduct:{}", id);

        ProductBo deleteProductBo = productsMap.get(id);
        productsMap.remove(id);
        log.info("Deleted ProductBo:{}", deleteProductBo);

        return ResultInfo.success(deleteProductBo);
    }
}
