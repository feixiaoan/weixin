package com.gjl.weixin.mapper;

import com.gjl.weixin.entity.Questionnaire;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QuestionnaireMapper {
    int insert(Questionnaire record);

    int insertSelective(Questionnaire record);

    List<Questionnaire> findAll();
}