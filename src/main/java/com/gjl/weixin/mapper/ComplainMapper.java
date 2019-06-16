package com.gjl.weixin.mapper;

import com.gjl.weixin.dto.ComplainDto;
import com.gjl.weixin.entity.Complain;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ComplainMapper {
    int insert(Complain record);

    int insertSelective(Complain record);

    List<Complain> findAll();

    int updateComplainById(String id);

    List<ComplainDto> findAllByAdmin(ComplainDto complainDto);
}