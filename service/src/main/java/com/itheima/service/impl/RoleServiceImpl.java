package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.itheima.dao.PermissionDao;
import com.itheima.dao.RoleDao;
import com.itheima.dao.Role_PermissionDao;
import com.itheima.domain.Permission;
import com.itheima.domain.Role;
import com.itheima.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PermissionDao permissionDao;

    @Autowired
    private Role_PermissionDao role_permissionDao;

    /***
     * 查询所有
     * @param currentPage
     * @param pageSize
     * @return
     */
    @Override
    public List<Role> findAll(Integer currentPage, Integer pageSize) {
        PageHelper.startPage(currentPage,pageSize);
        return roleDao.finAll();
    }


    /***
     * 存储一条信息
     * @param role
     * @return
     */
    @Override
    public Integer save(Role role) {
        return roleDao.save(role);
    }


    /**
     * 根据角色Id查找该角色没有的权限
     * @param id
     * @return
     */
    @Override
    public List<Permission> findRoleByIdAndAllPermission(String id) throws Exception {
        return permissionDao.findRoleByIdAndAllPermission(id);
    }


    /***
     * 添加角色的权限
     * @param ids
     * @param roleId
     * @return
     */
    @Override
    public Integer addPermissionToRole(String[] ids, String roleId) {
        Integer  endResult=0;
        for (String id : ids) {
            Integer result = role_permissionDao.addPermissionToRole(id,roleId);
            endResult +=result;
        }
        return endResult;
    }
}
