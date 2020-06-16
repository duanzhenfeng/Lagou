package com.lg.dzf.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "com_detil")
@Data
public class CompanyDetil {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private String cname;
    private String name;
    private String image;
    private String website;
    private String city;
    private String industry;
    private String scale;
    private String develop;
    private String descr;
    private Date createtime;
    private Integer cid;
    private String showtime;
}
