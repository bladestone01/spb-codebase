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
 * 学校信息
 * </p>
 *
 * @author bladestone
 * @since 2025-03-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("school_info")
public class SchoolInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 学院名称
     */
    @TableField("name")
    private String name;

    /**
     * 学生数量
     */
    @TableField("student_num")
    private Integer studentNum;

    @TableField("created_time")
    private Date createdTime;

    @TableField("updated_time")
    private Date updatedTime;

    @TableField("valid")
    private Integer valid;

    @TableField("created_user")
    private String createdUser;

    @TableField("updated_user")
    private String updatedUser;


    public static final String ID = "id";

    public static final String NAME = "name";

    public static final String STUDENT_NUM = "student_num";

    public static final String CREATED_TIME = "created_time";

    public static final String UPDATED_TIME = "updated_time";

    public static final String VALID = "valid";

    public static final String CREATED_USER = "created_user";

    public static final String UPDATED_USER = "updated_user";

}
