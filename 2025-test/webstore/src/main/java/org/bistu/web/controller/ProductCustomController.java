package org.bistu.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.bistu.web.domain.ProductBo;
import org.bistu.web.domain.ResultInfo;
import org.bistu.web.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.Result;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/webstore/custom")
public class ProductCustomController {
    @Autowired
    private IProductService productService;

    @GetMapping("/products")
    public ResultInfo<List<ProductBo>> getProductByName(@RequestParam String name)  {
        log.info("Get Producgts By Name:{}", name);
        List<ProductBo> productBoList = this.productService.getOneByNameLike(name);
        log.info("Query Product Bos:{}", productBoList);

        return ResultInfo.success(productBoList);
    }


   @PostMapping("/products")
    public ResultInfo<ProductBo> createOne(@RequestBody ProductBo productBo) {
        log.info("Create One:{}", productBo);
        ProductBo newProductBo = this.productService.createCustomOne(productBo);
        log.info("Created ProductBo:{}", newProductBo);

        return ResultInfo.success(newProductBo);
   }

   @PutMapping("/products/name")
    public ResultInfo<ProductBo> updateCustomByName(@RequestBody ProductBo productBo) {
        log.info("Update One:{}", productBo);
        int updateCount = this.productService.updateCustomByName(productBo);
        log.info("Updated ProductBo:{}", updateCount);

        return ResultInfo.success(updateCount);
   }

   @DeleteMapping("/products/name")
    public ResultInfo<Boolean> deleteOne(@RequestParam String name, @RequestParam(required = false, defaultValue = "false") Boolean logicFlag) {
        log.info("Delete One Name:{}", name);
        Boolean resultStatus = this.productService.deleteCustomByName(name, logicFlag);
        log.info("Deleted Product:{}", resultStatus);

        return ResultInfo.success(resultStatus);
    }
}
