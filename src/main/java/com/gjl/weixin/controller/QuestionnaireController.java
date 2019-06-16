package com.gjl.weixin.controller;


import com.gjl.weixin.entity.Questionnaire;
import com.gjl.weixin.entity.Student;
import com.gjl.weixin.mapper.QuestionnaireMapper;
import com.gjl.weixin.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/questionnaire")
public class QuestionnaireController {
    @Autowired
    private QuestionnaireMapper questionnaireMapper;

    @GetMapping("/findAll")
    public R findAll(HttpServletResponse response, HttpServletRequest request){
       /* response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with,content-type");*/
        HttpSession session=request.getSession();
        //查询当前用户信息
        Student userInfo = (Student)session.getAttribute("userInfo");
        if(userInfo == null){
            return R.error("用户未登录");
        }
        List<Questionnaire> list = questionnaireMapper.findAll();
        if(list.size()>0){
            return R.ok(list);
        }
        return R.error("题库中没有题");
    }

}
