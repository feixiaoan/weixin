package com.gjl.weixin.mapper;

import com.gjl.weixin.dto.StudentDto;
import com.gjl.weixin.entity.Student;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StudentMapper {
    int insert(Student record);

    int insertSelective(Student record);

    int updateSelective(Student record);

    List<Student> login(@Param("userName") String userName, @Param("password")String password);

    List<StudentDto> findAll();

    int save(@Param("id")Integer id);

    @Delete("delete  from student where id=#{id}")
    int delete(@Param("id") String id);

    List<Student> findAll2();

    @Select("select * from student where card_id = #{cardId}")
    List<Student> findByCardId(String cardId);

    List<Student> findAllStudent(Student student);
}