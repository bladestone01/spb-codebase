/*
 * create by mybatis-plus-generator  https://github.com/xiweile
 */
package org.bistu.web.dao.mapper;

import org.bistu.web.consts.ValidEnum;
import org.bistu.web.dao.entity.SchoolInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 学校信息 Mapper 接口
 * </p>
 *
 * @author bladestone
 * @since 2025-03-13
 */
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

/**
 * 完全基于注解实现增删改查功能.
 *
 * @author chenjunfeng
 *
 */
@Mapper
public interface SchoolInfoMapper extends BaseMapper<SchoolInfo> {
    @Results(id="schoolMap", value={
            @Result(property="createdBy", column="created_user")
    })
    @Select("select id, name, student_num,  valid, created_time, created_user, updated_time, updated_user from school_info")
    public List<SchoolInfo> getSchools();

    @Results(id="schoolMap2", value={
            @Result(property="createdBy", column="created_user"),
            @Result(property="valid", column="valid", javaType= ValidEnum.class)
    })
    @Select("select id, name, student_num,  valid, created_time, created_user, updated_time, updated_user from school_info")
    public List<SchoolInfo> getSchoolEnums();

    //展示结果Result注解
    //${id}类型映射
    @ResultMap("schoolMap")
    @Select("select id, name, student_num,  valid, created_time, created_user, updated_time, updated_user from school_info where id = #{id}")
    public SchoolInfo getSchoolById(Long id);

    @Select("select id, name, student_num,  valid, created_time, created_user, updated_time, updated_user from school_info where where name = #{name}")
    @ResultMap("schoolMap")
    public List<SchoolInfo> getSchoolByName(String name);


    @Insert("insert into school_info(name, student_num, created_user, updated_user) "
            + "values(#{name}, #{studentNum},#{createdBy}, #{updatedUser})")
    //返回PO的id属性
    //@Options(useGeneratedKeys=true, keyProperty="id"), 与@Options等同
    @SelectKey(statement = "select last_insert_id()", keyProperty = "id", before = false, resultType = Long.class)
    public Long insertOne(SchoolInfo schoolInfo);

    //自增主键
    @Options(useGeneratedKeys=true, keyProperty="id")
    @Insert("insert into school_info(name, student_num, valid, created_user, updated_user) "
            + "values(#{name}, #{studentNum}, #{valid},#{createdBy}, #{updatedUser})")
    @SelectKey(statement = "select last_insert_id()", before = false, resultType = Long.class, keyProperty = "id")
    public Long insert(String name, int studentNum, int valid, String createdBy, String updatedBy);

    @Update("update school_info set student_num=#{studentNum}, valid=${valid} where name=#{schoolName}")
    public void updateSchool(String schoolName, int studentNum, int valid);


    /**
     * 重载方法不支持.
     *
     * @param id
     * @param schoolName
     * @param studentNum
     * @param valid
     */
    @Update("update school_info set name=#{schoolName}, student_num=#{studentNum}, valid=#{valid} where id = #{id}")
    public void updateSchool2(Long id, String schoolName, int studentNum, int valid);


    @Delete("delete from school_info where name = #{schoolName}")
    public void deleteByName(String schoolName);
}