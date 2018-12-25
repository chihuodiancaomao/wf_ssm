package com.itheima.service;

import com.itheima.domain.Permission;

import java.util.List;

public interface PermissionService {

    /***
     * 查询所有
     * @param currentPage
     * @param pageSize
     * @return
     */
    List<Permission> findAll(Integer currentPage, Integer pageSize);



    /***
     * 添加一条数据
     * @param permission
     * @return
     */
    Integer save(Permission permission);
}
