package com.lg.dzf.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "company")
@Data
public class Company {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private String email;
    private String password;
    private Integer pass;
    private Date createtime;
    private String showtime;
}
