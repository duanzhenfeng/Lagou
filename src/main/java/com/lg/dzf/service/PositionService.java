package com.lg.dzf.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lg.dzf.mapper.CompanyDetilMapper;
import com.lg.dzf.mapper.PositionMapper;
import com.lg.dzf.pojo.CompanyDetil;
import com.lg.dzf.pojo.Position;
import com.lg.dzf.vo.ListVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class PositionService {

    @Autowired
    private PositionMapper positionMapper;

    @Autowired
    private CompanyDetilMapper companyDetilMapper;

    public List<ListVo> hotlist() {
        PageHelper.startPage(1, 10);
        Example example = new Example(Position.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("status", 1);
        example.setOrderByClause("hot DESC");
        List<Position> positions = positionMapper.selectByExample(example);
        List<ListVo> list = new ArrayList<>();
        positions.stream().forEach(p -> {
            ListVo listVo = new ListVo();
            listVo.setPosition(p);
            list.add(listVo);
        });
        list.stream().forEach(v -> {
            Integer cid = v.getPosition().getCid();
            CompanyDetil companyDetil = new CompanyDetil();
            companyDetil.setCid(cid);
            List<CompanyDetil> select = companyDetilMapper.select(companyDetil);
            CompanyDetil companyDetil1 = select.get(0);
            v.setCompanyDetil(companyDetil1);
        });
        return list;
    }

    public List<ListVo> newlist() {
        PageHelper.startPage(1, 10);
        Example example = new Example(Position.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIsNotNull("id");
        criteria.andEqualTo("status", 1);
        example.setOrderByClause("createtime DESC");
        List<Position> positions = positionMapper.selectByExample(example);
        List<ListVo> list = new ArrayList<>();
        positions.stream().forEach(p -> {
            ListVo listVo = new ListVo();
            listVo.setPosition(p);
            list.add(listVo);
        });
        list.stream().forEach(v -> {
            Integer cid = v.getPosition().getCid();
            CompanyDetil companyDetil = new CompanyDetil();
            companyDetil.setCid(cid);
            List<CompanyDetil> select = companyDetilMapper.select(companyDetil);
            CompanyDetil companyDetil1 = select.get(0);
            v.setCompanyDetil(companyDetil1);
        });
        return list;
    }

    public ListVo info(Integer id, Integer cid) {
        Position position = positionMapper.selectByPrimaryKey(id);
        CompanyDetil companyDetil1 = new CompanyDetil();
        companyDetil1.setCid(cid);
        List<CompanyDetil> select = companyDetilMapper.select(companyDetil1);
        ListVo listVo = new ListVo();
        listVo.setPosition(position);
        listVo.setCompanyDetil(select.get(0));
        return listVo;
    }

    public List<ListVo> search(String kd) {
        Example example = new Example(Position.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andLike("allkey", "%" + kd + "%");
        criteria.andEqualTo("status", 1);
        example.setOrderByClause("createtime DESC");
        List<Position> positions = positionMapper.selectByExample(example);
        List<ListVo> list = new ArrayList<>();
        positions.stream().forEach(p -> {
            ListVo listVo = new ListVo();
            listVo.setPosition(p);
            list.add(listVo);
        });
        list.stream().forEach(v -> {
            Integer cid = v.getPosition().getCid();
            CompanyDetil companyDetil = new CompanyDetil();
            companyDetil.setCid(cid);
            List<CompanyDetil> select = companyDetilMapper.select(companyDetil);
            CompanyDetil companyDetil1 = select.get(0);
            v.setCompanyDetil(companyDetil1);
        });
        return list;
    }
}
