package org.bistu.web.webstore.service;

import org.bistu.web.webstore.domain.ProductBo;

import java.util.List;

public interface IProductService {

    /**
     * 获取所有的记录。
     *
     * @return
     */
    public List<ProductBo> getOnes();

    public ProductBo getOne(Long id);

    public List<ProductBo> getOnesByPrice(float price);
}
