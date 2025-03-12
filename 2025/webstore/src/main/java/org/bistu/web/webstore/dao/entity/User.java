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
 * @since 2025-03-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tc_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 姓名
     */
    @TableField("name")
    private String name;

    /**
     * 年龄
     */
    @TableField("age")
    private Integer age;

    /**
     * 出生年月日
     */
    @TableField("birth_date")
    private Date birthDate;

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

    public static final String AGE = "age";

    public static final String BIRTH_DATE = "birth_date";

    public static final String CREATED_TIME = "created_time";

    public static final String UPDATED_TIME = "updated_time";

    public static final String CREATED_USER = "created_user";

    public static final String UPDATED_USER = "updated_user";

    public static final String VALID = "valid";

}
