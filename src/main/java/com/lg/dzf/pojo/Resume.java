package com.lg.dzf.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "resume")
public class Resume {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private String username;
    private Integer sex;
    private String education;
    private String experience;
    private String phone;
    private String email;
    private String experiencedesc;
    private String skill;
    private String school;
    private String self;
    private Integer uid;
    private String image;
}
