package com.lg.dzf.controller;

import com.lg.dzf.pojo.Company;
import com.lg.dzf.pojo.CompanyDetil;
import com.lg.dzf.pojo.Position;
import com.lg.dzf.pojo.Resume;
import com.lg.dzf.service.CompanyService;
import com.lg.dzf.vo.PageResult;
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
@RequestMapping("api/company")
@Slf4j
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @RequestMapping("save")
    @ResponseBody
    public Integer save(HttpServletRequest request, @RequestBody CompanyDetil companyDetil){
        log.info("c:{}",companyDetil);
        return companyService.save(request,companyDetil);
    }

    @RequestMapping("index")
    public String index(){
        return "index";
    }

    @RequestMapping("index06")
    public String index06(){
        return "index06";
    }

    @RequestMapping("reing")
    public String reing(){
        return "resumeing";
    }
    @RequestMapping("reon")
    public String reon(){
        return "resumeon";
    }
    @RequestMapping("reoff")
    public String reoff(){
        return "resumeoff";
    }

    @RequestMapping("resumeing")
    @ResponseBody
    public List<ResumeVo> resumeing(HttpServletRequest request){
        return companyService.resumeing(request);
    }

    @RequestMapping("create")
    public String create(){
        return "create";
    }

    @RequestMapping("savedetil")
    @ResponseBody
    public Integer savedetil(HttpServletRequest request, @RequestBody Position position){
        return companyService.savedetil(request, position);
    }

    @RequestMapping("positions")
    public String positions(){
        return "positions";
    }

    @RequestMapping("unpositions")
    public String unpositions(){
        return "unpositions";
    }

    @RequestMapping("positionslist")
    @ResponseBody
    public List<Position> positionslist(HttpServletRequest request){
        return companyService.positionslist(request);
    }

    @RequestMapping("unpositionslist")
    @ResponseBody
    public List<Position> unpositionslist(HttpServletRequest request){
        return companyService.unpositionslist(request);
    }

    @RequestMapping("topositionsing")
    public String topositionsing(){
        return "positionsing";
    }

    @RequestMapping("topositionsoff")
    public String topositionsoff(){
        return "positionsoff";
    }

    @RequestMapping("topositionson")
    public String topositionson(){
        return "positionson";
    }


    @RequestMapping("positionsing")
    @ResponseBody
    public List<ResumeVo> positionsing(HttpServletRequest request){
        return companyService.positionsing(request);
    }

    @RequestMapping("positionson")
    @ResponseBody
    public List<ResumeVo> positionson(HttpServletRequest request){
        return companyService.positionson(request);
    }

    @RequestMapping("positionsoff")
    @ResponseBody
    public List<ResumeVo> positionsoff(HttpServletRequest request){
        return companyService.positionsoff(request);
    }

    @RequestMapping("off")
    public String off(@RequestParam("id") Integer id){
        companyService.off(id);
        return "positions";
    }

    @RequestMapping("on")
    public String on(@RequestParam("id") Integer id){
        companyService.on(id);
        return "unpositions";
    }

    @RequestMapping("delete")
    public String delete(@RequestParam("id") Integer id){
        companyService.delete(id);
        return "unpositions";
    }
    @RequestMapping("deleteon")
    public String deleteon(HttpServletRequest request, @RequestParam("id") Integer id){
        companyService.deleter(request, id);
        return "positionson";
    }
    @RequestMapping("deleteoff")
    public String deleteoff(HttpServletRequest request, @RequestParam("id") Integer id){
        companyService.deleter(request, id);
        return "positionsoff";
    }

    @RequestMapping("check")
    public ModelAndView jianliinfo(HttpServletRequest request, @RequestParam("id") Integer id, @RequestParam("pid") Integer pid){
        ModelAndView modelAndView = new ModelAndView();
        ResumeVo resume = companyService.getResumeInfo(request, id, pid);
        modelAndView.addObject("resume", resume);
        modelAndView.setViewName("check");
        log.info("resume:{}", resume);
        return modelAndView;
    }

    @RequestMapping("pass")
    @ResponseBody
    public Integer pass(HttpServletRequest request, @RequestParam("id") Integer id, @RequestParam("pid") Integer pid){
        return companyService.pass(request, id, pid);
    }

    @RequestMapping("unpass")
    @ResponseBody
    public Integer unpass(HttpServletRequest request, @RequestParam("id") Integer id, @RequestParam("pid") Integer pid){
        return companyService.unpass(request, id, pid);
    }

    @RequestMapping("updatecompany")
    @ResponseBody
    public Integer updatecompany(HttpServletRequest request, @RequestBody CompanyDetil companyDetil){
        log.info("c:{}",companyDetil);
        return companyService.updatecompany(request,companyDetil);
    }

    @RequestMapping("companyinfo")
    public ModelAndView companyinfo(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        if(companyService.companyinfo(request) != null){
            CompanyDetil companyinfo = companyService.companyinfo(request);
            modelAndView.addObject("companyinfo", companyinfo);
            modelAndView.setViewName("companyinfo");
        }else{
            //发布公司页面
            modelAndView.setViewName("index01");
        }
        return modelAndView;
    }
    @RequestMapping("info")
    public ModelAndView cinfo(@RequestParam("id") Integer id){
        ModelAndView modelAndView = new ModelAndView();
        CompanyDetil companyinfo = companyService.info(id);
        modelAndView.addObject("companyinfo", companyinfo);
        modelAndView.setViewName("cinfo");
        return modelAndView;
    }

    @RequestMapping("repwdview")
    public String repwdview(){
        return "repwd";
    }

    @RequestMapping("companylist")
    public String companylist(){
        return "companylist";
    }

    @RequestMapping("list")
    @ResponseBody
    public PageResult<Company> list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                    @RequestParam(value = "limit", defaultValue = "10") Integer limit){
        return companyService.list(page, limit);
    }

    @RequestMapping("repwd")
    @ResponseBody
    public String repwd(HttpServletRequest request, @RequestBody Pwd pwd){
        return companyService.repwd(request, pwd);
    }

    @RequestMapping("deleteCompany")
    @ResponseBody
    public String deleteCompany(@RequestParam("id")Integer id){
        return companyService.deleteCompany(id);
    }

}
