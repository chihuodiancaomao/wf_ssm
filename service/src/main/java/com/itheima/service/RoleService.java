package com.itheima.service;

import com.itheima.domain.Permission;
import com.itheima.domain.Role;

import java.util.List;

public interface RoleService {

    /***
     * 查询所有
     * @param currentPage
     * @param pageSize
     * @return
     */
    List<Role> findAll(Integer currentPage, Integer pageSize);


    /***
     * 存储一条信息
     * @param role
     * @return
     */
    Integer save(Role role);


    /**
     * 根据角色Id查找该角色没有的权限
     * @param id
     * @return
     */
    List<Permission> findRoleByIdAndAllPermission(String id) throws Exception;


    /***
     * 添加角色的权限
     * @param ids
     * @param roleId
     * @return
     */
    Integer addPermissionToRole(String[] ids, String roleId);
}
