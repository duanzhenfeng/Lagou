package com.lg.dzf.mapper;

import com.lg.dzf.pojo.User;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

// @Repository 反射代理自动生成 通过mapper搜寻信息
@Repository
public interface UserMapper extends Mapper<User>{

}
