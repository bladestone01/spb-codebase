/*
 * create by mybatis-plus-generator  https://github.com/xiweile
 */
package org.bistu.web.webstore.dao.entity;

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
@TableName("tc_buy_order")
public class BuyOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 购买用户ID
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 商品ID
     */
    @TableField("product_id")
    private Long productId;

    /**
     * 购买时间
     */
    @TableField("buy_time")
    private Date buyTime;

    /**
     * 购买数量
     */
    @TableField("item_number")
    private Integer itemNumber;

    /**
     * 购买地址
     */
    @TableField("location")
    private String location;

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

    public static final String USER_ID = "user_id";

    public static final String PRODUCT_ID = "product_id";

    public static final String BUY_TIME = "buy_time";

    public static final String ITEM_NUMBER = "item_number";

    public static final String LOCATION = "location";

    public static final String CREATED_TIME = "created_time";

    public static final String UPDATED_TIME = "updated_time";

    public static final String CREATED_USER = "created_user";

    public static final String UPDATED_USER = "updated_user";

    public static final String VALID = "valid";

}
