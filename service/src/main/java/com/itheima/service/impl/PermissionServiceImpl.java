package com.itheima.service.impl;


import com.github.pagehelper.PageHelper;
import com.itheima.dao.PermissionDao;
import com.itheima.domain.Permission;
import com.itheima.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionDao permissionDao;


    /***
     * 查询所有
     * @param currentPage
     * @param pageSize
     * @return
     */
    @Override
    public List<Permission> findAll(Integer currentPage, Integer pageSize) {
        PageHelper.startPage(currentPage,pageSize);
        return permissionDao.findAll();
    }


    /***
     * 添加一条数据
     * @param permission
     * @return
     */
    @Override
    public Integer save(Permission permission) {
        return permissionDao.save(permission);
    }
}
