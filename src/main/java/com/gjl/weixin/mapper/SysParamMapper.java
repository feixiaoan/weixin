package com.gjl.weixin.mapper;

import com.gjl.weixin.entity.Student;
import com.gjl.weixin.entity.SysParam;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SysParamMapper {
    int insert(SysParam record);

    int insertSelective(SysParam record);

    @Select("select * from sysparam")
    List<SysParam> findSysParamAll();

    @Select("select * from student where id = #{sysParamId}")
    List<SysParam> findByCardId(String sysParamId);

    @Delete("delete  from sysparam where id=#{id}")
    int deleteById(@Param("id") String id);

    @Update("update sysparam set sysvalue = #{sysvalue} where id = #{id}")
    int save(SysParam record);

    @Select("select * from sysparam where id = #{id}")
    List<SysParam> findSysParamById(String id);
    @Select("select * from sysParam where sysName = #{sysName}")
    SysParam findSysParamByName(String sysName);

    List<SysParam> findAllSysParam(SysParam sysParam);
    @Select("select * from sysParam where sysCode = #{sysCode} and sysValue = 1 and enabled = 1")
    List<SysParam> findSysParamByCode(String sysCode);
}