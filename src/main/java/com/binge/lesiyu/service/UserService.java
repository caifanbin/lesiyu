package com.binge.lesiyu.service;

import com.binge.lesiyu.bean.User;
import com.binge.lesiyu.commons.AjaxResult;
import com.binge.lesiyu.commons.StringUtil;
import com.binge.lesiyu.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class UserService implements UserDetailsService {


    @Autowired
    private UserMapper userMapper;



    @Autowired
    private JavaMailSenderImpl javaMailSender;

    @Autowired
    private RedisTemplate redisTemplate;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User userByUsername = userMapper.getUserByUsername(username);
        if(userByUsername==null){
            System.out.println("没有这个账户"+username);
          throw new UsernameNotFoundException("没有这个账户！请重新输入");
        }
        System.out.println("登陆名为:"+username+"登陆密码为"+userByUsername.getPassword());
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        if(userByUsername.getAccess()==0){
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }else if(userByUsername.getId()==2){
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        }else {
            authorities.add(new SimpleGrantedAuthority("ROLE_PRO"));
        }
        return new org.springframework.security.core.userdetails.User(userByUsername.getUsername(),userByUsername.getPassword(),authorities);
    }

    public AjaxResult registered(User user,String authcode){

        System.out.println(authcode);
        System.out.println("ser");
        user.setAccess(2);
        System.out.println(user);
        AjaxResult result = new AjaxResult();
        Object uname = redisTemplate.opsForValue().get(user.getUsername());
        if(uname==null){
            Object s= redisTemplate.opsForValue().get(user.getUsername()+"code");
            System.out.println("验证码"+s);
        if(authcode.equals(s)) {
            try {
                redisTemplate.opsForValue().set(user.getEmail(),1);

                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
                String pwd = encoder.encode(user.getPassword());
                user.setPassword(pwd);
                Date date = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String time = dateFormat.format(date);
                user.setRegistrationTime(time);
                userMapper.saveUser(user);
                redisTemplate.opsForValue().set(user.getUsername(),1);
                result.setSuccess(true);
            } catch (Exception e) {
                e.printStackTrace();
                result.setMessage("注册失败");
                result.setSuccess(false);
            }
            redisTemplate.delete(user.getUsername()+"code");
        }else {
            result.setSuccess(false);
            result.setMessage("验证码错误！");
        }
        return result;
    }else {
            result.setSuccess(false);
            result.setMessage("用户名已存在");
            return result;
        }
    }

    public AjaxResult mailSender(String username, String email) {
        System.out.println("mailser");

        AjaxResult result = new AjaxResult();
        Object mail = redisTemplate.opsForValue().get(email);
        if(StringUtil.iNotEmpty(username) &&StringUtil.iNotEmpty(email)&&mail==null){
        try {
            String authcode = UUID.randomUUID().toString().substring(0, 4);
            System.out.println(authcode);

            redisTemplate.opsForValue().set(username+"code",authcode,300, TimeUnit.SECONDS);
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setSubject("乐思域注册中心");
            mailMessage.setText("您正在注册乐思域，您的验证码：【" + authcode + "】验证码5分钟内有效，请您千万不要泄露给他人！");
            mailMessage.setTo(email);
            mailMessage.setFrom("caifanbin@126.com");
            javaMailSender.send(mailMessage);

            result.setSuccess(true);
        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
        }}else {
            result.setMessage("邮箱已被注册！");
            result.setSuccess(false);
        }

        return result;
    }


   public User getByUserName(String username) {
        return userMapper.getUserByUsername(username);
    };


}
