package com.gjw;

import com.gjw.Utils.sendEmailUtil.SendEmail;
import com.gjw.bean.User;
import com.gjw.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@SpringBootTest
class SpringbootShiroApplicationTests {

    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;

//    @Autowired
//    JavaMailSenderImpl mailSender;


    @Test
    void contextLoads() {

//        User user = userService.queryUserByAcc("admin");
//        System.out.println(user);
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setSubject("你好啊！");
        msg.setText("xixixi!");
        msg.setTo("1258046117@qq.com");
        msg.setFrom("1258046117@qq.com");
//        mailSender.send(msg);

    }

    @Test
    void mimeEmail() throws MessagingException {
//        MimeMessage mimeMessage = mailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);

//        helper.setSubject("hello!!");
//        helper.setText("<h1 style='color:red'>xixihaha!!!</h1>",true);
//        helper.addAttachment("Xshell.exe",new File("D:\\xshell\\Xshell.exe"));
//        helper.addAttachment("1.png",new File("C:\\Users\\GJW\\Desktop\\1.png"));
//        helper.setTo("1258046117@qq.com");
//        helper.setFrom("1258046117@qq.com");
//        mailSender.send(mimeMessage);
    }

    @Autowired
    SendEmail sendEmail;
    @Test
    void testMethod(){
//        SendEmail sendEmail = new SendEmail();
//        sendEmail.send(true,"hello!!","<h1 style='color:red'>xixihaha!!!</h1>");
    }

}
