package com.lg.dzf.vo;

import com.lg.dzf.pojo.Position;
import com.lg.dzf.pojo.Resume;
import com.lg.dzf.pojo.ResumeStatus;
import lombok.Data;

@Data
public class ResumeVo {
    private Resume resume;
    private Position position;
    private ResumeStatus resumeStatus;
    private String cname;
}
