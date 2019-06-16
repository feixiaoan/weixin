package com.gjl.weixin.controller;

import com.gjl.weixin.common.GlobalError;
import com.gjl.weixin.entity.Complain;
import com.gjl.weixin.entity.ScheduledTask;
import com.gjl.weixin.mapper.ComplainMapper;
import com.gjl.weixin.mapper.ScheduledTaskMapper;
import com.gjl.weixin.mapper.SysParamMapper;
import com.gjl.weixin.utils.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.xml.bind.SchemaOutputResolver;
import java.util.List;
import java.util.Properties;

/**
 * @Author: WilliamJL
 * @Date: 2019/5/13 10:07
 * @Version 1.0
 */
@Controller
public class SendMailController {

    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);





    @Autowired
    private ComplainMapper complainMapper;
    @Autowired
    ScheduledTaskMapper scheduledTaskMapper;
    @Autowired
    SysParamMapper sysParamMapper;

     //test  start方法一会删除
    /*@PostMapping("/findAllComplain")
    public R findAllComplain(){

        List<Complain> list = complainMapper.findAll();
        String id = "3";
        int i = complainMapper.updateComplainById(id);
        return R.ok(list);
    }*/
    //test end 方法一会删除

    public R taskSendMail() {
       // ScheduledTask sendMailTask = scheduledTaskMapper.findTaskByKey("sendMail");
        String sysName = "sendMail开关";
        String mailVal = sysParamMapper.findSysParamByName(sysName).getSysvalue();
        String mailEnable = sysParamMapper.findSysParamByName(sysName).getEnabled();
        if("0".equals(mailVal) || "0".equals(mailEnable)){
            logger.info("定时任务未开启");
            return R.ok(GlobalError.ERROR_SCH_TASK);
        }
        List<Complain> list = complainMapper.findAll();
        if(list.size()<=0){
            System.out.println("数据库中无数据");
            return R.error(GlobalError.ERROR_COMPLAIN_DATA);
        }
        for(int i=0;i<list.size();i++){
            System.out.println("发邮件进行中");
            try{
                sendSmp(list.get(i).getComplainSubject(),list.get(i).getComplainReason());
                String id = list.get(i).getId().toString();
                //更改投诉表中是否发送邮件表识，1：也发送邮件，0 ：未发送邮件
                complainMapper.updateComplainById(id);
            }catch (Exception e){
                e.printStackTrace();
            }

        }

        System.out.println("发邮件成功");
        return R.ok();
    }

    public void sendSmp(String subject,String text) throws Exception{

        logger.debug("发邮件开始");
            Properties props = new Properties();
            props.setProperty("mail.smtp.auth", "true");
            props.setProperty("mail.transport.protocol", "smtp");
            props.put("mail.smtp.host","smtp.163.com");// smtp服务器地址
            Session session = Session.getInstance(props);
            session.setDebug(true);

            Message msg = new MimeMessage(session);
            msg.setSubject(subject);//设置主题
            msg.setText(text);//邮件内容
            msg.setFrom(new InternetAddress("15546601534@163.com"));//发件人邮箱(我的163邮箱)
            msg.setRecipient(Message.RecipientType.TO,
                    new InternetAddress("15546601534@163.com"));
            msg.setRecipient(Message.RecipientType.CC,
                new InternetAddress("2645019356@qq.com"));//收件人（抄送）邮箱(我的QQ邮箱)
            msg.saveChanges();

            Transport transport = session.getTransport();
            transport.connect("15546601534@163.com","15145175601xiao");//发件人邮箱,授权码(可以在邮箱设置中获取到授权码的信息)

            transport.sendMessage(msg, msg.getAllRecipients());

            transport.close();
        logger.debug("发邮件完成");

    }

}
