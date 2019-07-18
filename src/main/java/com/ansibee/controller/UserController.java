package com.ansibee.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/userinfo")
public class UserController {

    @RequestMapping("/test")
    public String test(){
        return "test";
    }

    @RequestMapping("/add")
    public String add(){
        return "user/add";
    }

    @RequestMapping("/update")
    public String update(){
        return "user/update";
    }

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("/noAuth")
    public String noAuth(){
        return "noAuth";
    }

    @RequestMapping("/login")
    public String login(String username,
                        String password,
                        Model model){

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        try {
            subject.login(token);
            return "test";
        } catch (UnknownAccountException e){
            model.addAttribute("msg","用户不存在");
            return "redirect:/userinfo/toLogin";
        } catch (IncorrectCredentialsException e){
            model.addAttribute("msg","密码错误");
            return "redirect:/userinfo/toLogin";
        }
    }
}
