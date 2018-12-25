package com.itheima.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

public interface Role_PermissionDao {

    /***
     * 添加角色的权限
     * @param roleId
     * @return
     */
    @Insert("insert into role_permission values(#{permissionId},#{roleId})")
    Integer addPermissionToRole(@Param("permissionId") String permissionId,@Param("roleId") String roleId);
}
