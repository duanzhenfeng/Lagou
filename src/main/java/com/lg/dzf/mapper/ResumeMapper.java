package com.lg.dzf.mapper;

import com.lg.dzf.pojo.Resume;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.BaseMapper;

@Repository
public interface ResumeMapper extends BaseMapper<Resume> {

    @Insert("INSERT INTO resumestatus(pid, rid, `status`) VALUES(${pid}, ${rid}, '${status}')")
    Integer insertResume(@Param("pid") Integer pid, @Param("rid")Integer rid, @Param("status")String status);

}
