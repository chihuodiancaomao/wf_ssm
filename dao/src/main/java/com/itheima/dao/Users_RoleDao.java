package com.itheima.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

public interface Users_RoleDao {

    @Insert("insert into users_role values (#{userId},#{roleId})")
    Integer addRoleToUser(@Param("roleId") String roleId,@Param("userId") String userId);
}
