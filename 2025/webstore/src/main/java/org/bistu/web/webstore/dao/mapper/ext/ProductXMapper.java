package org.bistu.web.webstore.dao.mapper.ext;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.bistu.web.webstore.dao.entity.Product;

import java.util.List;

@Mapper
public interface ProductXMapper extends BaseMapper<Product> {

    /**
     * 根据名称查询
     */
    public List<Product> getByName(String name);

    /**
     *  根据名称更新
     * @param product
     * @return
     */
    public int updateByName(Product product);


    /**
     * 根据名称删除
     * @param name
     * @return
     */
    public int deleteByName(String name);

    /**
     * 逻辑删除
     * @param name
     * @return
     */
    public int logicDeleteByName(String name);


    /**
     * 创建一条记录.
     * @param product
     * @return
     */
    public int createOne(Product product);
}