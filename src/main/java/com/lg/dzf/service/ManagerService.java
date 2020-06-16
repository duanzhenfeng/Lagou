package com.lg.dzf.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lg.dzf.mapper.CompanyMapper;
import com.lg.dzf.mapper.ManagerMapper;
import com.lg.dzf.pojo.Company;
import com.lg.dzf.pojo.Manager;
import com.lg.dzf.pojo.Resume;
import com.lg.dzf.vo.PageResult;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
@Log4j2
public class ManagerService {

    @Autowired
    private ManagerMapper mapper;
    @Autowired
    private CompanyMapper companyMapper;

    public PageResult<Manager> list(Integer page, Integer limit) {
        // 打开分页
        PageHelper.startPage(page, limit);
        List<Manager> managers = mapper.selectAll();;
        // 获取分页信息对象
        PageInfo<Manager> pageInfo = new PageInfo<>(managers);
        // 返回分页对象
        return new PageResult<Manager>("0", "查询成功", pageInfo.getTotal(), pageInfo.getList());
    }

    public String save(Manager manager) {

        Manager manager1 = new Manager();
        List<Manager> select = mapper.select(manager1);
        if(select.size() != 0){
            mapper.delete(manager1);
        }
        return mapper.insert(manager) + "";
    }

    public String delete(Integer id) {
        return mapper.deleteByPrimaryKey(id) + "";
    }

    public PageResult<Company> passlist(Integer page, Integer limit) {
        // 打开分页
        PageHelper.startPage(page, limit);
        Company company = new Company();
        company.setPass(0);
        List<Company> companies = companyMapper.select(company);
        // 获取分页信息对象
        PageInfo<Company> pageInfo = new PageInfo<>(companies);
        // 返回分页对象
        log.info("pageinfo:{}", pageInfo);
        return new PageResult<Company>("0", "查询成功", pageInfo.getTotal(), pageInfo.getList());
    }

    public String mypass(Integer id) {
        Company company = companyMapper.selectByPrimaryKey(id);
        company.setPass(1);
        log.info("company:{}",company);
        companyMapper.deleteByPrimaryKey(company.getId());
        return companyMapper.insert(company) + "";
    }

    public String unpass(Integer id) {
        Company company = companyMapper.selectByPrimaryKey(id);
        company.setPass(2);
        companyMapper.deleteByPrimaryKey(company.getId());
        return companyMapper.insert(company) + "";
    }
}
