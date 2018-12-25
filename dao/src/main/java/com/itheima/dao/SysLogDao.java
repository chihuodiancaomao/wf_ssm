package com.itheima.dao;

import com.itheima.domain.SysLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SysLogDao {

    //操作信息存入日志表中
    @Insert("insert into syslog (visitTime,username,ip,url,executionTime,method) values(" +
            "#{visitTime},#{username},#{ip},#{url},#{executionTime},#{method})")
    void save(SysLog sysLog);

    //查看日志信息
    @Select("select * from sysLog")
    List<SysLog> findAll();
}
