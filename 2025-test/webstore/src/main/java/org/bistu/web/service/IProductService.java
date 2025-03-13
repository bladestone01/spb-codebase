package org.bistu.web.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.bistu.web.dao.entity.Product;
import org.bistu.web.domain.ProductBo;

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

    /**
     * 根据产品名称模糊查询
     * @param name
     * @return
     */

    public ProductBo createOne(String name, String description, float price);

    public ProductBo createOne(ProductBo productBo);

    public ProductBo updateOne(Long id, ProductBo productBo);

    public Boolean deleteOne(Long id);


    /**
     *  Custom MyBatis Method
     */
    public List<ProductBo> getOneByNameLike(String name);
    public int updateCustomByName(ProductBo productBo);
    public boolean deleteCustomByName(String name, Boolean logicFlag);
    public ProductBo createCustomOne(ProductBo productBo);


    /**
     * pageable query
     */
    public IPage<ProductBo> getOnesByPageable(Integer pageNo, Integer pageSize, ProductBo productBo);

}
