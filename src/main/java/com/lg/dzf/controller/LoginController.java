package com.lg.dzf.controller;

import com.lg.dzf.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@Slf4j
public class LoginController {

    @Autowired
    private LoginService service;

    @RequestMapping("login")
    public String login(){
        return "login";
    }

    //退出
    @RequestMapping("loginout")
    public String loginout(HttpServletRequest request){
        request.getSession().invalidate();
        return "login";
    }

    @RequestMapping("mloginout")
    public String mloginout(HttpServletRequest request){
        request.getSession().invalidate();
        return "mlogin";
    }

    @RequestMapping("manager")
    public String manager(){
        return "mlogin";
    }

    @RequestMapping("mlogin")
    @ResponseBody
    public String mlogin(HttpServletRequest request,@RequestParam("username")String username, @RequestParam("password")String password){

        return service.mlogin(request,username, password);
    }

    @RequestMapping("index01")
    public String index01(HttpServletRequest request){
        Boolean aBoolean = service.create(request);
        if(aBoolean){
            return "redirect:/api/company/topositionsing";
        }else {
            return "index01";
        }
    }
    //在使用 @RequestMapping后，返回值通常解析为跳转路径
    @RequestMapping("register")

    public String register(){
        return "register";
    }

    @RequestMapping("create")
    public String create(){
        return "index06";
    }

    @RequestMapping("index")
    public String index(){
        return "index";
    }

    @RequestMapping("mindex")
    public String mindex(){
        return "mindex";
    }
    //登陆
    @RequestMapping("userlogin")
    @ResponseBody
    public String userlogin(HttpServletRequest request,
                            // @RequestParam
                            // 用于将指定的请求参数赋值给方法中的形参
                               @RequestParam("email")String email,
                               @RequestParam("password")String password,
                               @RequestParam("type")Integer type){
        return service.userlogin(request,email, password, type);
    }
    //注册
    @RequestMapping("userregister")
    @ResponseBody
    //@ResponseBody是作用在方法上的，@ResponseBody 表示该方法的返回结果直接写入 HTTP response body中，
    //@ResponseBody这个注解通常使用在控制层（controller）的方法上。
    public String userregister(@RequestParam("email")String email,
                               @RequestParam("password")String password,
                               @RequestParam("type")Integer type){
        return service.userregister(email, password, type);
    }

}
