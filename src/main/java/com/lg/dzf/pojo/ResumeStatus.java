package com.lg.dzf.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "resumestatus")
public class ResumeStatus {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private Integer pid;
    private Integer rid;
    private Integer cid;
    private String status;
    private String showtime;
}
