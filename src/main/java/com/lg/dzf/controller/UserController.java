package com.lg.dzf.controller;

import com.lg.dzf.pojo.Resume;
import com.lg.dzf.service.UserService;
import com.lg.dzf.vo.Pwd;
import com.lg.dzf.vo.ResumeVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("api/user")
@Slf4j
public class UserController {
    /**
     * 控制器(Controller)：
     * 控制器接受用户的输入并调用模型和视图去完成用户的需求。
     * 所以当单击Web页面中的超链接和发送HTML表单时，
     * 控制器本身不输出任何东西和做任何处理。
     * 它只是接收请求并决定调用哪个模型构件去处理请求，
     * 然后确定用哪个视图来显示模型处理返回的数据。
     * */

    //模型构件
    @Autowired
    private UserService service;

    @RequestMapping("jianli")
    public ModelAndView jianli(HttpServletRequest request){
        //ModelAndView 视图
        ModelAndView modelAndView = new ModelAndView();
        if(service.getResume(request) != null){
            Resume resume = service.getResume(request);
            modelAndView.addObject("resume", resume);
            modelAndView.setViewName("jianli");
        }else {
            modelAndView.setViewName("jianli1");
        }

        return modelAndView;
    }

    @RequestMapping("jianliinfo")
    public ModelAndView jianliinfo(@RequestParam("id") Integer id){
        ModelAndView modelAndView = new ModelAndView();
        Resume resume = service.getResumeInfo(id);
        modelAndView.addObject("resume", resume);
        modelAndView.setViewName("jianliinfo");
        log.info("resume:{}", resume);
        return modelAndView;
    }

    @RequestMapping("saveresume")
    @ResponseBody
    public Integer saveresume(HttpServletRequest request, @RequestBody Resume resume){
        return service.saveresume(request, resume);
    }

    @RequestMapping("sendresume")
    @ResponseBody
    public Integer sendresume(HttpServletRequest request, @RequestParam("pid")Integer pid){
        return service.sendresume(request, pid);
    }

    @RequestMapping("myresume")
    public String myresume(){
        return "myresume";
    }

    @RequestMapping("myunresume")
    public String myunresume(){
        return "myunresume";
    }

    @RequestMapping("mypassresume")
    @ResponseBody
    public List<ResumeVo> mypassresume(HttpServletRequest request){
        return service.mypassresume(request);
    }

    @RequestMapping("myunpassresume")
    @ResponseBody
    public List<ResumeVo> myunpassresume(HttpServletRequest request){
        return service.myunpassresume(request);
    }

    @RequestMapping("deleteoff")
    public String deleteoff(HttpServletRequest request,@RequestParam("rid")Integer rid, @RequestParam("pid")Integer pid){
        service.deleteoff(request, rid, pid);
        return "myunresume";
    }

    @RequestMapping("deleteon")
    public String deleteon(HttpServletRequest request,@RequestParam("rid")Integer rid, @RequestParam("pid")Integer pid){
        service.deleteon(request, rid, pid);
        return "myresume";
    }

    @RequestMapping("repwdview")
    public String repwdview(){
        return "repassword";
    }

    @RequestMapping("repwd")
    @ResponseBody
    public String repwd(HttpServletRequest request, @RequestBody Pwd pwd){
        return service.repwd(request, pwd);
    }

}
