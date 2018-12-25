package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.itheima.dao.SysLogDao;
import com.itheima.domain.SysLog;
import com.itheima.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysLogServiceImpl  implements SysLogService{


    @Autowired
    private SysLogDao sysLogDao;

    //操作信息存入日志表中
    @Override
    public void save(SysLog sysLog) {
        sysLogDao.save(sysLog);
    }



    /***
     * 查看日志信息
     * @param currentPage
     * @param pageSize
     * @return
     */
    @Override
    public List<SysLog> findAll(Integer currentPage, Integer pageSize) {

        PageHelper.startPage(currentPage,pageSize);
        return sysLogDao.findAll();
    }
}
