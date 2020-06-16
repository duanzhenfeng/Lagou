package com.lg.dzf.service;

import com.lg.dzf.mapper.*;
import com.lg.dzf.pojo.*;
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
public class UserService {

    @Autowired
    private ResumeMapper resumeMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PositionMapper positionMapper;

    @Autowired
    private ResumeStatusMapper resumeStatusMapper;

    @Autowired
    private CompanyDetilMapper companyDetilMapper;

    public Integer saveresume(HttpServletRequest request, Resume resume) {
        User user = (User)request.getSession().getAttribute("user");
        resume.setUid(user.getId());
        Resume resume1 = new Resume();
        resume1.setUid(user.getId());
        log.info("resume:{}", resume.getImage());
        List<Resume> select = resumeMapper.select(resume1);
        if(select.size() != 0){
            resumeMapper.delete(resume1);
        }
        return resumeMapper.insert(resume);
    }

    public Integer sendresume(HttpServletRequest request, Integer pid) {
        User user = (User) request.getSession().getAttribute("user");
        Integer id = user.getId();
        Resume resume = new Resume();
        resume.setUid(id);
        List<Resume> resumes = resumeMapper.select(resume);
        if(resumeMapper.select(resume).isEmpty()){
            return 3;
        }
        Resume resume1 = resumes.get(0);
        Integer rid = resume1.getId();
        Position position = positionMapper.selectByPrimaryKey(pid);
        Integer cid = position.getCid();
        position.setHot(position.getHot() + 1);
        positionMapper.updateByPrimaryKeySelective(position);
        ResumeStatus resumeStatus = new ResumeStatus();
        resumeStatus.setCid(cid);
        resumeStatus.setPid(pid);
        resumeStatus.setRid(rid);
        resumeStatus.setStatus("已投递");
        if(!resumeStatusMapper.select(resumeStatus).isEmpty()){
            return 2;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(new Date());
        resumeStatus.setShowtime(format);
        return resumeStatusMapper.insert(resumeStatus);
    }

    public Integer findresume(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        Resume resume = new Resume();
        resume.setUid(user.getId());
        List<Resume> select = resumeMapper.select(resume);
        return select.size();
    }
    //获取简历
    public Resume getResume(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        Resume resume = new Resume();
        resume.setUid(user.getId());
        List<Resume> select = resumeMapper.select(resume);
        if(select.size() == 0){
            return null;
        }else{
            return select.get(0);
        }

    }

    public Resume getResumeInfo(Integer id) {
        return resumeMapper.selectByPrimaryKey(id);
    }

    public List<ResumeVo> mypassresume(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        Resume resume1 = new Resume();
        resume1.setUid(user.getId());
        List<Resume> select1 = resumeMapper.select(resume1);
        Resume resume2 = select1.get(0);
        ResumeStatus resumeStatus = new ResumeStatus();
        resumeStatus.setRid(resume2.getId());
        resumeStatus.setStatus("通知面试");
        List<ResumeStatus> select = resumeStatusMapper.select(resumeStatus);
        ArrayList<ResumeVo> list = new ArrayList<>();
        select.stream().forEach(r -> {
            Position position = positionMapper.selectByPrimaryKey(r.getPid());
            Resume resume = resumeMapper.selectByPrimaryKey(r.getRid());
            ResumeVo resumeVo = new ResumeVo();
            resumeVo.setPosition(position);
            resumeVo.setResume(resume);
            resumeVo.setResumeStatus(r);
            CompanyDetil companyDetil1 = new CompanyDetil();
            companyDetil1.setCid(position.getCid());
            List<CompanyDetil> select2 = companyDetilMapper.select(companyDetil1);
            resumeVo.setCname(select2.get(0).getCname());
            list.add(resumeVo);
        });
        return list;
    }

    public List<ResumeVo> myunpassresume(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        Resume resume1 = new Resume();
        resume1.setUid(user.getId());
        List<Resume> select1 = resumeMapper.select(resume1);
        Resume resume2 = select1.get(0);
        ResumeStatus resumeStatus = new ResumeStatus();
        resumeStatus.setRid(resume2.getId());
        resumeStatus.setStatus("未通过");
        List<ResumeStatus> select = resumeStatusMapper.select(resumeStatus);
        ArrayList<ResumeVo> list = new ArrayList<>();
        select.stream().forEach(r -> {
            Position position = positionMapper.selectByPrimaryKey(r.getPid());
            Resume resume = resumeMapper.selectByPrimaryKey(r.getRid());
            ResumeVo resumeVo = new ResumeVo();
            resumeVo.setPosition(position);
            resumeVo.setResume(resume);
            resumeVo.setResumeStatus(r);
            CompanyDetil companyDetil1 = new CompanyDetil();
            companyDetil1.setCid(position.getCid());
            List<CompanyDetil> select2 = companyDetilMapper.select(companyDetil1);
            resumeVo.setCname(select2.get(0).getCname());
            list.add(resumeVo);
        });
        return list;
    }

    public void deleteoff(HttpServletRequest request, Integer rid, Integer pid) {
        ResumeStatus resumeStatus = new ResumeStatus();
        resumeStatus.setStatus("未通过");
        resumeStatus.setRid(rid);
        resumeStatus.setPid(pid);
        List<ResumeStatus> select = resumeStatusMapper.select(resumeStatus);
        resumeStatusMapper.deleteByPrimaryKey(select.get(0).getId());
    }

    public void deleteon(HttpServletRequest request, Integer rid, Integer pid) {
        ResumeStatus resumeStatus = new ResumeStatus();
        resumeStatus.setStatus("通知面试");
        resumeStatus.setRid(rid);
        resumeStatus.setPid(pid);
        List<ResumeStatus> select = resumeStatusMapper.select(resumeStatus);
        resumeStatusMapper.deleteByPrimaryKey(select.get(0).getId());
    }

    public String repwd(HttpServletRequest request, Pwd pwd) {
        User user = (User) request.getSession().getAttribute("user");
        if(!StringUtils.equals(user.getPassword(), pwd.getOlderpwd())){
            return "2";
        }else {
            user.setPassword(pwd.getNewpwd());
            return userMapper.updateByPrimaryKeySelective(user) + "";
        }
    }
}
