package org.bistu.web.service;

import org.bistu.web.domain.ProductBo;

import java.util.List;

public interface IProductService {

    public ProductBo getOne(Long id);

    public List<ProductBo> getOnes();

    /**
     * 基于价格进行查询.
     *
     * @param price
     * @return
     */

    public List<ProductBo> getOnes(Float price);


    public ProductBo createOne(ProductBo productBo);

    /**
     *
     * @param name
     * @param description
     * @param price
     * @return
     */
    public ProductBo createOne(String name, String description, float price);

    public ProductBo udpateOne(Long id, ProductBo productBo);


    public ProductBo deleteOne(Long id);
}
