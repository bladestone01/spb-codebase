/*
 * create by mybatis-plus-generator  https://github.com/xiweile
 */
package org.bistu.web.webstore.dao.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author bladestone
 * @since 2025-03-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tc_product")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 产品名称
     */
    @TableField("name")
    private String name;

    /**
     * 描述信息
     */
    @TableField("description")
    private String description;

    /**
     * 价格信息
     */
    @TableField("price")
    private BigDecimal price;

    @TableField("created_time")
    private Date createdTime;

    @TableField("updated_time")
    private Date updatedTime;

    @TableField("created_user")
    private String createdUser;

    @TableField("updated_user")
    private String updatedUser;

    @TableField("valid")
    private Integer valid;


    public static final String ID = "id";

    public static final String NAME = "name";

    public static final String DESCRIPTION = "description";

    public static final String PRICE = "price";

    public static final String CREATED_TIME = "created_time";

    public static final String UPDATED_TIME = "updated_time";

    public static final String CREATED_USER = "created_user";

    public static final String UPDATED_USER = "updated_user";

    public static final String VALID = "valid";

}
