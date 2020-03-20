package com.binge.lesiyu.service;

import com.binge.lesiyu.bean.User;
import com.binge.lesiyu.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserService implements UserDetailsService {


    @Autowired
    private UserMapper userMapper;

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
}
