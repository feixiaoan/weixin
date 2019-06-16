package com.gjl.weixin.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gjl.weixin.dto.ComplainDto;
import com.gjl.weixin.entity.Complain;
import com.gjl.weixin.entity.Notice;
import com.gjl.weixin.entity.Student;
import com.gjl.weixin.mapper.NoticeMapper;
import com.gjl.weixin.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.security.provider.certpath.PKIXTimestampParameters;

import javax.annotation.RegEx;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Author: WilliamJL
 * @Date: 2019/6/1 21:12
 * @Version 1.0
 */
@RestController
@RequestMapping("/notice")
public class NoticeController {
    @Autowired
    private NoticeMapper noticeMapper;
    @Autowired
    private WeChatController weChatController;

    @RequestMapping("/findAllNotice")
    public R findAllNotice(String pageNum, String pageSize, Notice notice){
        if(pageNum==null){
            pageNum="1";
        }
        if(pageSize==null){
            pageSize="3";
        }
        PageHelper.startPage( Integer.valueOf(pageNum),Integer.valueOf(pageSize));
        List<Notice> list=noticeMapper.findAllNotice(notice);
        PageInfo pageInfo = new PageInfo<Notice>(list, 3);
        if(list.size()>0){
            return R.ok(pageInfo);
        }
        return R.error("没有通知信息");
    }


    @PostMapping("/push")
    public R push(Notice notice, HttpServletRequest request){
        if(notice.getNoticeSpare()!=null && notice.getNoticeSpare()!=""){
            R r = weChatController.push(notice.getNoticeSpare());
            if(r.getCode() == 1){
                return r;
            }
            String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            notice.setNoticeDate(date);
            notice.setNoticeContent(notice.getNoticeSpare());
            int i=noticeMapper.insert(notice);
            if( i>0 ){
                return R.ok();
            }
        }
            return R.error("插入失败");
    }

}
