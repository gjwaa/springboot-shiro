package com.gjw.Utils.sendEmailUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Component
public class SendEmail {

    @Autowired
    JavaMailSenderImpl mailSender;
    @Async
    public void send(boolean isMime, boolean isHtml, String title, String text, String from, String to) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(mimeMessage, isMime);
            helper.setSubject(title);
            helper.setText(text, isHtml);
            if (isMime) {
                helper.addAttachment("Xshell.exe", new File("D:\\xshell\\Xshell.exe"));
                helper.addAttachment("1.png", new File("C:\\Users\\GJW\\Desktop\\1.png"));
            }
            helper.setFrom(from);
            helper.setTo(to);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        mailSender.send(mimeMessage);
    }

}
