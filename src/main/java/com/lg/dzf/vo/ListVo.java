package com.lg.dzf.vo;

import com.lg.dzf.pojo.CompanyDetil;
import com.lg.dzf.pojo.Position;
import lombok.Data;

@Data
//返回视图对象
public class ListVo {

    private Position position;

    private CompanyDetil companyDetil;

}
