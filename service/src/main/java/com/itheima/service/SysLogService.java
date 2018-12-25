package com.itheima.service;

import com.itheima.domain.SysLog;

import java.util.List;

public interface SysLogService {

    //操作信息存入日志表中
    void save(SysLog sysLog);


    /***
     * 查看日志信息
     * @param currentPage
     * @param pageSize
     * @return
     */
    List<SysLog> findAll(Integer currentPage, Integer pageSize);
}
