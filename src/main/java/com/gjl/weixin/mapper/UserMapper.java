package com.gjl.weixin.mapper;

import com.gjl.weixin.entity.Student;
import com.gjl.weixin.entity.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    int insert(User record);

    int insertSelective(User record);

    List<User> login(@Param("userName") String userName, @Param("password")String password);

    List<User> findAll();

    List<User> findAllByCondition(User user);
    @Delete("delete from user where id = #{id}")
    int deleteById(@Param("id")String id);

    int save(User user);

    int findUserById(String id);
}