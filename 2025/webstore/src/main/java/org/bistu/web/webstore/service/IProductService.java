package org.bistu.web.webstore.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.bistu.web.webstore.domain.ProductBo;

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

    public boolean updateOne(Long id, ProductBo productBo);


    public boolean deleteOne(Long id);


    /**
     * User Custom XML Operation
     */
    /**
     *  Custom MyBatis Method
     */
    public List<ProductBo> getOneByNameLike(String name);
    public int updateCustomByName(ProductBo productBo);
    public boolean deleteCustomByName(String name, Boolean logicFlag);
    public ProductBo createCustomOne(ProductBo productBo);


    public IPage<ProductBo> getOnesByPageable(Integer pageNo, Integer pageSize, ProductBo productBo);
}
