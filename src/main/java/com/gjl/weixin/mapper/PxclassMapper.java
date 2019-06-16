package com.gjl.weixin.mapper;

import com.gjl.weixin.entity.Pxclass;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PxclassMapper {
    int insert(Pxclass record);

    int insertSelective(Pxclass record);
    //@Select("select * from pxclass where id=#{id}")
    List<Pxclass> findPxclassById(String id);

    List<Pxclass> findAll();

    List<Pxclass> findPxclassByName(String pxclassName);

    @Delete("delete from pxclass where id = #{id}")
    int deleteById(@Param("id") String id);

    int save(Pxclass pxclass);

    List<Pxclass> findAllClass(Pxclass pxclass);
}