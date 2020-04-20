package com.binge.lesiyu;

import com.binge.lesiyu.bean.UserText;
import com.binge.lesiyu.commons.AjaxResult;
import com.binge.lesiyu.service.UserTextService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.List;
import java.util.UUID;

@SpringBootTest
class LesiyuApplicationTests {


    @Autowired
    private JavaMailSenderImpl javaMailSender;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private UserTextService userTextService;


    @Test
    void contextLoads() {

        String authcode =  UUID.randomUUID().toString().substring(0,4);
        System.out.println(authcode);
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setSubject("乐思域注册中心");
        mailMessage.setText("您正在注册乐思域，您的验证码：【"+authcode+"】请您千万不要泄露给他人！");
        mailMessage.setTo("745604472@qq.com");
        mailMessage.setFrom("caifanbin@126.com");
        javaMailSender.send(mailMessage);

    }


    @Test
    void contextLoads2() {

        redisTemplate.opsForValue().set("123","123");
    }
    @Test
    void contextLoads3() {

        AjaxResult allText = userTextService.getAllText();
        List<UserText> data = (List<UserText>) allText.getData();
        for (UserText text: data) {
            System.out.println(text.getTitle());
        }
    }



}
