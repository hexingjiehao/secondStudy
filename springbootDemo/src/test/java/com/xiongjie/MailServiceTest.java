package com.xiongjie;

import com.xiongjie.service.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 * Created by xiongjie on 2018/10/22.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MailServiceTest {

    @Autowired
    private MailService mailService;
    @Autowired
    private TemplateEngine templateEngine;

    @Test
    public void testSimpleMail(){
        mailService.sendSimpleMail("2711992339@qq.com","test springboot+simpalemail","hello,mail");
    }

    @Test
    public void testHtmlMail(){
        String content="<html>\n" +
                "<body>\n" +
                "    <h3>hello world ! 这是一封Html邮件!</h3>\n" +
                "</body>\n" +
                "</html>";
        mailService.sendHtmlMail("2711992339@qq.com","test springboot+htmemail",content);
    }

    @Test
    public void sendAttachmentsMail() {
        String filePath="E://360解压缩密码.txt";
//        String filePath="E://sideFile.txt";
        mailService.sendAttachmentsMail("2711992339@qq.com", "test springboot+attachment", "有附件，请查收！", filePath);
    }

    @Test
    public void sendInlineResourceMail() {
        String rscId = "xiongjie1";
        String content="<html><body>这是有图片的邮件：<img src=\'cid:" + rscId + "\' ></body></html>";
        String imgPath = "E:\\Documents\\Pictures\\风景画\\1.jpg";
        mailService.sendInlineResourceMail("2711992339@qq.com", "test springboot+picture attachment", content, imgPath, rscId);
    }

    @Test
    public void sendTemplateMail() {
        //创建邮件正文
        Context context = new Context();
        context.setVariable("id", "006");
        String emailContent =  templateEngine.process("emailTemplate", context);

        mailService.sendHtmlMail("2711992339@qq.com","springboot+templateMail",emailContent);
    }

}


