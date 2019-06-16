package com.gjl.weixin.utils;

import com.gjl.weixin.controller.StudentController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * @Author: WilliamJL
 * @Date: 2019/5/27 18:52
 * @Version 1.0
 */
@Component
public class SendMailUtil {

    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

    public static void SendMail(String emailCode,String subject,String text) throws Exception{

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
                new InternetAddress(emailCode));//收件人邮箱(我的QQ邮箱)
        msg.saveChanges();

        Transport transport = session.getTransport();
        transport.connect("15546601534@163.com","15145175601xiao");//发件人邮箱,授权码(可以在邮箱设置中获取到授权码的信息)

        transport.sendMessage(msg, msg.getAllRecipients());

        transport.close();
        logger.debug("发邮件完成");

    }
}
