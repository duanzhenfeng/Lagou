package com.lg.dzf.service;

import com.lg.dzf.mapper.CompanyDetilMapper;
import com.lg.dzf.mapper.CompanyMapper;
import com.lg.dzf.mapper.ManagerMapper;
import com.lg.dzf.mapper.UserMapper;
import com.lg.dzf.pojo.Company;
import com.lg.dzf.pojo.CompanyDetil;
import com.lg.dzf.pojo.Manager;
import com.lg.dzf.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class LoginService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private CompanyDetilMapper detilMapper;

    @Autowired
    private ManagerMapper managerMapper;

    public String userregister(String email, String password, Integer type) {
        String result = "3";
        if(type == 0){
            User user = new User();
            user.setEmail(email);
            List<User> select = userMapper.select(user);
            if (select.size() != 0){
                result = "1";
                return result;
            }else {
                user.setPassword(password);
                user.setCreatetime(new Date());
                Date createtime = user.getCreatetime();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String format = simpleDateFormat.format(createtime);
                user.setShowtime(format);
                userMapper.insert(user);
                result = "0";
                return result;
            }
        }else {
            Company company = new Company();
            company.setEmail(email);
            List<Company> select = companyMapper.select(company);
            if (select.size() != 0){
                result = "1";
                return result;
            }else {
                company.setPassword(password);
                company.setPass(0);
                company.setCreatetime(new Date());
                Date createtime = company.getCreatetime();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String format = simpleDateFormat.format(createtime);
                company.setShowtime(format);
                companyMapper.insert(company);
                result = "0";
                return result;
            }
        }
    }
    //获取登陆表单中radio的属性，type=0代表求职者，type=1代表招聘者
    public String userlogin(HttpServletRequest request, String email, String password, Integer type) {
        if (type == 0){
            User user = new User();
            user.setEmail(email);
            user.setPassword(password);
            List<User> select = userMapper.select(user);
            //返回值0，1，2 login.html页面ajax数据回显,
            // 0代表求职者登陆成功，
            // 1代表求职者与招聘登陆皆失败，
            // 2代表招聘者登陆成功
            if(select.size() == 0){
                return "1";
            }else {

                //将user对象存放在session中
                request.getSession().setAttribute("user", select.get(0));
                return "0";
            }
        }else{
            Company company = new Company();
            company.setEmail(email);
            company.setPassword(password);
            List<Company> select = companyMapper.select(company);
            if(select.size() == 0){
                return "1";
            }else {
                request.getSession().setAttribute("user", select.get(0));
                return "2";
            }
        }
    }

    public Boolean create(HttpServletRequest request) {
        Company company = (Company)request.getSession().getAttribute("user");
        CompanyDetil companyDetil = new CompanyDetil();
        companyDetil.setCid(company.getId());
        List<CompanyDetil> select = detilMapper.select(companyDetil);
        if(select.size() == 0){
            return false;
        }else {
            return true;
        }
    }

    public String mlogin(HttpServletRequest request,String username, String password) {

        Manager manager = new Manager();
        manager.setUsername(username);
        manager.setPassword(password);
        List<Manager> select = managerMapper.select(manager);
        if(CollectionUtils.isEmpty(select)){
            return "2";
        }
        HttpSession session = request.getSession();
        session.setAttribute("user",select.get(0));
        return "1";

    }
}
