package com.lg.dzf.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "manager")
public class Manager {
    @KeySql(useGeneratedKeys = true)
    @Id
    private Integer id;
    private String username;
    private String password;
}
