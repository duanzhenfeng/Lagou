package com.lg.dzf.controller;

import com.lg.dzf.common.JsonUtils;
import com.lg.dzf.service.PositionService;
import com.lg.dzf.vo.ListVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("api/position")
@Slf4j
public class PositionController {

    @Autowired
    private PositionService service;

    @RequestMapping("hotlist")
    @ResponseBody
    public List<ListVo> hotlist(){
        return service.hotlist();
    }

    @RequestMapping("newlist")
    @ResponseBody
    public List<ListVo> newlist(){
        return service.newlist();
    }

    @RequestMapping("info")
    public ModelAndView info(@RequestParam("id")Integer id, @RequestParam("cid")Integer cid){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("toudi");
        ListVo info = service.info(id, cid);
        modelAndView.addObject("info", info);
        return modelAndView;
    }

    @RequestMapping("search")
    //返回list.html
    public String search(Model model,  @RequestParam("kd")String kd){
        model.addAttribute("kd", kd);
        return "list";
    }

    @RequestMapping("page")
    @ResponseBody
    public String page(@RequestParam("kd")String kd){
        List<ListVo> search = service.search(kd);
        return JsonUtils.toString(search);
    }

}
