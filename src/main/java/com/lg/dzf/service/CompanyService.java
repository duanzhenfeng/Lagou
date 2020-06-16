package com.lg.dzf.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lg.dzf.mapper.*;
import com.lg.dzf.pojo.*;
import com.lg.dzf.vo.PageResult;
import com.lg.dzf.vo.Pwd;
import com.lg.dzf.vo.ResumeVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class CompanyService {

    @Autowired
    private CompanyDetilMapper companyDetilMapper;

    @Autowired
    private PositionMapper positionMapper;

    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private ResumeStatusMapper resumeStatusMapper;

    @Autowired
    private ResumeMapper resumeMapper;

    public Integer save(HttpServletRequest request, CompanyDetil companyDetil) {
        Company company = (Company)request.getSession().getAttribute("user");
        companyDetil.setCid(company.getId());
        companyDetil.setCreatetime(new Date());
        Date createtime = companyDetil.getCreatetime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(createtime);
        companyDetil.setShowtime(format);

        return companyDetilMapper.insert(companyDetil);
    }

    public Integer savedetil(HttpServletRequest request, Position position) {
        Company company = getCompany(request);

        Company company1 = companyMapper.selectByPrimaryKey(company.getId());


        if(company1.getPass() == 2){
            return 2;
        }
        if(company1.getPass() == 0){
            return 0;
        }
        position.setCid(company.getId());
        position.setSalary(position.getSalarymin() + "K-" + position.getSalarymax() + "K");
        position.setCreatetime(new Date());
        position.setStatus(1);
        position.setHot(0);
        Date createtime1 = position.getCreatetime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(createtime1);
        position.setShowtime(format);

        String allkey = position.getPaddress() + position.getCname() + position.getEducation();
        position.setAllkey(allkey);

        return positionMapper.insert(position);
    }

    public Company getCompany(HttpServletRequest request) {
        return (Company)request.getSession().getAttribute("user");

    }

    public List<Position> positionslist(HttpServletRequest request) {
        Company company = getCompany(request);
        Position position = new Position();
        position.setStatus(1);
        position.setCid(company.getId());
        List<Position> select = positionMapper.select(position);
        return select;
    }

    public List<Position> unpositionslist(HttpServletRequest request) {
        Company company = getCompany(request);
        Position position = new Position();
        position.setStatus(0);
        position.setCid(company.getId());
        List<Position> select = positionMapper.select(position);
        return select;
    }

    public List<ResumeVo> positionsing(HttpServletRequest request) {
        return getlist(request, "已投递");
    }

    public List<ResumeVo> positionson(HttpServletRequest request) {
        return getlist(request, "通知面试");
    }

    public List<ResumeVo> positionsoff(HttpServletRequest request) {
        return getlist(request, "未通过");
    }

    public List<ResumeVo> getlist(HttpServletRequest request, String status){
        Company company = getCompany(request);
        ResumeStatus resumeStatus = new ResumeStatus();
        resumeStatus.setCid(company.getId());
        resumeStatus.setStatus(status);
        List<ResumeStatus> select = resumeStatusMapper.select(resumeStatus);
        ArrayList<ResumeVo> list = new ArrayList<>();
        select.stream().forEach(r -> {
            log.info("pid:{}", r.getPid());
            Position position = positionMapper.selectByPrimaryKey(r.getPid());
            log.info("position:{}", position);
            Resume resume = resumeMapper.selectByPrimaryKey(r.getRid());
            ResumeVo resumeVo = new ResumeVo();
            resumeVo.setPosition(position);
            resumeVo.setResume(resume);
            resumeVo.setResumeStatus(r);
            list.add(resumeVo);
        });
        return list;
    }

    public List<ResumeVo> resumeing(HttpServletRequest request) {
        getlist(request, "已投递");
        return null;
    }

    public void off(Integer id) {
        Position position = positionMapper.selectByPrimaryKey(id);
        position.setStatus(0);
        positionMapper.updateByPrimaryKeySelective(position);
    }

    public void on(Integer id) {
        Position position = positionMapper.selectByPrimaryKey(id);
        position.setStatus(1);
        positionMapper.updateByPrimaryKeySelective(position);
    }

    public void delete(Integer id) {
        positionMapper.deleteByPrimaryKey(id);
    }

    public ResumeVo getResumeInfo(HttpServletRequest request, Integer id, Integer pid) {
        Company company = getCompany(request);
        ResumeStatus resumeStatus = new ResumeStatus();
        resumeStatus.setCid(company.getId());
        resumeStatus.setRid(id);
        resumeStatus.setPid(pid);
        List<ResumeStatus> select = resumeStatusMapper.select(resumeStatus);
        Resume resume = resumeMapper.selectByPrimaryKey(id);
        ResumeVo resumeVo = new ResumeVo();
        resumeVo.setResume(resume);
        resumeVo.setResumeStatus(resumeStatus);
        return resumeVo;
    }

    public Integer pass(HttpServletRequest request, Integer id, Integer pid) {
        Company company = getCompany(request);
        ResumeStatus resumeStatus = new ResumeStatus();
        resumeStatus.setCid(company.getId());
        resumeStatus.setRid(id);
        resumeStatus.setPid(pid);
        List<ResumeStatus> select = resumeStatusMapper.select(resumeStatus);
        ResumeStatus resumeStatus1 = select.get(0);
        resumeStatus1.setStatus("通知面试");
        return resumeStatusMapper.updateByPrimaryKeySelective(resumeStatus1);
    }

    public Integer unpass(HttpServletRequest request, Integer id, Integer pid) {
        Company company = getCompany(request);
        ResumeStatus resumeStatus = new ResumeStatus();
        resumeStatus.setCid(company.getId());
        resumeStatus.setRid(id);
        resumeStatus.setPid(pid);
        List<ResumeStatus> select = resumeStatusMapper.select(resumeStatus);
        ResumeStatus resumeStatus1 = select.get(0);
        resumeStatus1.setStatus("未通过");
        return resumeStatusMapper.updateByPrimaryKeySelective(resumeStatus1);
    }

    public void deleter(HttpServletRequest request, Integer id) {
        Company company = getCompany(request);
        ResumeStatus resumeStatus = new ResumeStatus();
        resumeStatus.setCid(company.getId());
        resumeStatus.setRid(id);
        List<ResumeStatus> select = resumeStatusMapper.select(resumeStatus);
        ResumeStatus resumeStatus1 = select.get(0);
        resumeStatusMapper.deleteByPrimaryKey(resumeStatus1.getId());
    }

    public CompanyDetil companyinfo(HttpServletRequest request) {
        Company company = getCompany(request);
        CompanyDetil companyDetil = new CompanyDetil();
        companyDetil.setCid(company.getId());
        List<CompanyDetil> select = companyDetilMapper.select(companyDetil);
        if(select.size() == 0){
            return null;
        }else {
            return select.get(0);
        }
    }

    public Integer updatecompany(HttpServletRequest request, CompanyDetil companyDetil) {
        Company company = (Company)request.getSession().getAttribute("user");
        companyDetil.setCid(company.getId());
        log.info("更新：{}", companyDetil);
        company.setPass(0);
        CompanyDetil companyDetil1 = new CompanyDetil();
        companyDetil1.setCid(company.getId());
        companyDetilMapper.delete(companyDetil1);
        companyMapper.deleteByPrimaryKey(company.getId());
        companyMapper.insert(company);
        return companyDetilMapper.insert(companyDetil);
    }

    public String repwd(HttpServletRequest request, Pwd pwd) {
        Company company = (Company)request.getSession().getAttribute("user");
        if(!StringUtils.equals(company.getPassword(), pwd.getOlderpwd())){
            return "2";
        }else {
            company.setPassword(pwd.getNewpwd());
            return companyMapper.updateByPrimaryKeySelective(company) + "";
        }
    }

    public CompanyDetil info(Integer id) {
        CompanyDetil companyDetil = new CompanyDetil();
        companyDetil.setCid(id);
        List<CompanyDetil> select = companyDetilMapper.select(companyDetil);
        return select.get(0);
    }

    public PageResult<Company> list(Integer page, Integer limit) {
        // 打开分页
        PageHelper.startPage(page, limit);
        Company company = new Company();
        company.setPass(1);
        List<Company> companies = companyMapper.select(company);
        // 获取分页信息对象
        PageInfo<Company> pageInfo = new PageInfo<>(companies);
        // 返回分页对象
        return new PageResult<Company>("0", "查询成功", pageInfo.getTotal(), pageInfo.getList());
    }

    public String deleteCompany(Integer id) {
        CompanyDetil companyDetil = new CompanyDetil();
        companyDetil.setCid(id);
        companyDetilMapper.delete(companyDetil);
        return companyMapper.deleteByPrimaryKey(id) + "";
    }
}
