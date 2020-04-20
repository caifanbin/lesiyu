package com.binge.lesiyu.controller;

import com.binge.lesiyu.bean.UserText;
import com.binge.lesiyu.commons.AjaxResult;
import com.binge.lesiyu.service.UserService;
import com.binge.lesiyu.service.UserTextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@Controller
public class BokeController {

    @Value("${properties.uploadPath}")
    private String rootpath;

    @Autowired
    private UserService userService;

    @Autowired
    private UserTextService userTextService;

    @RequestMapping("/editor")
    public String editor(){
        return "boke/editor";
    }

    @RequestMapping("/release")
    public String release(){
        return "boke/release";
    }

    @RequestMapping("/addtext")
    public String fu(UserText usertext, Model model){
        UserText userText = userTextService.saveText(usertext);
        System.out.println(userText);
        model.addAttribute("usertext",userText);
        return "boke/show";
    }

    @RequestMapping(value="/imageUpload",method= RequestMethod.POST)
    public void hello(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "editormd-image-file", required = false) MultipartFile attach){
        try {
            request.setCharacterEncoding( "utf-8" );
            response.setHeader( "Content-Type" , "text/html" );

/**
 * 文件路径不存在则需要创建文件路径
 */
            File filePath=new File(rootpath);
            if(!filePath.exists()){
                filePath.mkdirs();
            }
            System.out.println("a====="+rootpath);
            String b = rootpath+ File.separator+attach.getOriginalFilename();
            System.out.println("b======"+b);
//最终文件名
            File realFile=new File(b);
            attach.transferTo(realFile);
//下面response返回的json格式是editor.md所限制的，规范输出就OK
            response.getWriter().write( "{\"success\": 1, \"message\":\"上传成功\",\"url\":\"/upload/" + attach.getOriginalFilename() + "\"}" );
        } catch (Exception e) {
            e.printStackTrace();
            try {
                response.getWriter().write( "{\"success\":0}" );
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

  @RequestMapping("/aishow")
    public String aishow(Model model,long id){
      UserText userText = userTextService.getUserText(id);
      model.addAttribute("usertext",userText);
      return "/boke/show";
  }

  @RequestMapping("/getall")
    @ResponseBody
    public AjaxResult getall(){

      AjaxResult result = userTextService.getAllText();
      System.out.println(result.getSuccess());
      return result;
  }
}
