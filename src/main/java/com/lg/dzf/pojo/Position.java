package com.lg.dzf.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "position")
public class Position {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private String cname;
    private String branch;
    private String salarymin;
    private String salarymax;
    private String salary;
    private String paddress;
    private String address;
    private String experience;
    private String education;
    private String description;
    private String email;
    private Integer cid;
    private Date createtime;
    private String showtime;
    private Integer status;
    private Integer hot;
    private String allkey;
}
