package org.bistu.web.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.extern.slf4j.Slf4j;
import org.bistu.web.domain.ProductBo;
import org.bistu.web.domain.ResultInfo;
import org.bistu.web.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/webstore/page")
public class PageProductController {
    @Autowired
    private IProductService productService;

    @GetMapping("/products")
    public ResultInfo<IPage<ProductBo>> getProductsByPage(@RequestParam(name = "pageNo", required = false, defaultValue = "1") Integer pageNo,
                                                          @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                                          @RequestParam(name = "price", required = false, defaultValue = "0.0") Float price,
                                                          @RequestParam(name = "name", required = false, defaultValue = "") String name) {
        log.info("Pageable Query, pageNo:{}, pageSize:{}, price:{}, name:{}", pageNo, pageSize, price, name);
        ProductBo queryProductBo = ProductBo.create(name, "", price);

        IPage<ProductBo> productBoIPage = this.productService.getOnesByPageable(pageNo, pageSize, queryProductBo);
        log.info("query pageable result:{}", productBoIPage);

        return ResultInfo.success(productBoIPage);
    }
}
