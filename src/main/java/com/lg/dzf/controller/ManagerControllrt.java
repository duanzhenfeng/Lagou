package com.lg.dzf.controller;

import com.lg.dzf.pojo.Company;
import com.lg.dzf.pojo.Manager;
import com.lg.dzf.service.ManagerService;
import com.lg.dzf.vo.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("api/manager")
public class ManagerControllrt {

    @Autowired
    private ManagerService service;

    @RequestMapping("managerlist")
    public String managerlist(){
        return "managerlist";
    }
    @RequestMapping("list")
    @ResponseBody
    public PageResult<Manager> list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                    @RequestParam(value = "limit", defaultValue = "10") Integer limit){
        return service.list(page, limit);
    }

    @RequestMapping("addManager")
    public String addManager(){
        return "addManager";
    }

    @RequestMapping("save")
    @ResponseBody
    public String save(@RequestBody Manager manager){
        return service.save(manager);
    }

    @RequestMapping("delete")
    @ResponseBody
    public String delete(@RequestParam("id") Integer id){
        return service.delete(id);
    }

    @RequestMapping("pass")
    public String pass(){
        return "uncompanypass";
    }

    @RequestMapping("mypass")
    @ResponseBody
    public String mypass(@RequestParam("id") Integer id){
        return service.mypass(id);
    }

    @RequestMapping("unpass")
    @ResponseBody
    public String unpass(@RequestParam("id") Integer id){
        return service.unpass(id);
    }

    @RequestMapping("passlist")
    @ResponseBody
    public PageResult<Company> passlist(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                        @RequestParam(value = "limit", defaultValue = "10") Integer limit){
        return service.passlist(page, limit);
    }

}
