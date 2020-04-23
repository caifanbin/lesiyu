package com.binge.lesiyu.service;

import com.binge.lesiyu.bean.UserText;
import com.binge.lesiyu.commons.AjaxResult;
import com.binge.lesiyu.mapper.UserMapper;
import com.binge.lesiyu.mapper.UserTextMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class UserTextService {

    @Value("${properties.uploadPath}")
    private String rootpath;

    @Autowired
    private UserTextMapper userTextMapper;

    @Autowired
    private UserMapper userMapper;

    public AjaxResult saveText(UserText userText){

        /*String s = UUID.randomUUID().toString()+".html";

        File file = new File(rootpath,s);
        if(!file.exists()){
           try {
               file.createNewFile();
           }catch (IOException e){
               e.printStackTrace();
           }
        }
        FileReader fileReader=null;
        FileReader fileReader2=null;
        FileWriter fileWriter=null;
        int len=0;
        int las=0;
        try {
            fileWriter= new FileWriter(rootpath+s);
            fileReader=new FileReader(rootpath+"top.html");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            while((len=fileReader.read())!=-1) {
                fileWriter.write((char) len);
            }

                bufferedWriter.write(userText.getText());

            fileReader2=new FileReader(rootpath+"foot.html");
            while((las=fileReader2.read())!=-1) {
                fileWriter.write((char) len);
            }
            bufferedWriter.close();
        }catch (IOException e){
            e.printStackTrace();
        }
*/
        AjaxResult result = new AjaxResult();

        try {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = dateFormat.format(date);
        userText.setTime(time);
        userText.setUserid(userMapper.getUserByUsername(name).getId());
        userText.setUsername(name);
        userTextMapper.saveText(userText);
        long id = userText.getId();
        System.out.println("charu id" +id);
        result.setSuccess(true);
        result.setData(id);

        }catch (Exception e){

            result.setSuccess(false);
            result.setMessage("保存失败");
            e.printStackTrace();
        }
        return result;
    }

    public UserText getUserText(long id){
        UserText userText = userTextMapper.getText(id);
        return userText;

    }

    public AjaxResult getAllText(){
        AjaxResult result = new AjaxResult();
        try {
            List<UserText> all = userTextMapper.getAll();
            if(all!=null){
                result.setData(all);
                result.setSuccess(true);
            }
        }catch (Exception e){
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }

    public List<UserText> getall(){
        return userTextMapper.getAll();
    }



}
