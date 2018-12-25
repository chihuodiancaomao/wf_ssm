package com.itheima.dao;

import com.itheima.domain.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PermissionDao {


    /***
     * 根据Id查询信息
     * @param id
     * @return
     */
    @Select("select * from permission where id in" +
            "(select permissionId from role_permission where roleId=#{id})")
    public List<Permission> findById(String id);


    /***
     * 查询所有
     * @return
     */
    @Select("select * from permission")
    List<Permission> findAll();



    /***
     * 添加一条数据
     * @param permission
     * @return
     */
    @Insert("insert into permission (permissionName,url) values (#{permissionName},#{url})")
    Integer save(Permission permission);


    /**
     * 根据角色Id查找该角色没有的权限
     * @param id
     * @return
     */
    @Select("select * from permission where id not in (select permissionId from role_permission where roleId=#{id})")
    List<Permission> findRoleByIdAndAllPermission(String id);
}
