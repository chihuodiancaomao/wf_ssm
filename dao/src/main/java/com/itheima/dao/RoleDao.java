package com.itheima.dao;

import com.itheima.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface RoleDao {

    /***
     * 根据Id查询多条信息
     * @param id
     * @return
     */
    @Select("select * from role where id in (select roleId from users_role where userId = #{id})")
    @Results({@Result(id=true,property = "id",column = "id"),
    @Result(property = "roleName",column = "roleName"),
    @Result(property = "roleDesc",column = "roleDesc"),
    @Result(property = "permissions",column = "id",javaType =List.class,
            many = @Many(select = "com.itheima.dao.PermissionDao.findById"))
    })
    public List<Role> findById(String id);



    /***
     * 查询所有
     * @return
     */
    @Select("select * from role")
//    @Results({@Result(id=true,property = "id",column = "id"),
//            @Result(property = "roleName",column = "roleName"),
//            @Result(property = "roleDesc",column = "roleDesc"),
//            @Result(property = "permissions",column = "id",javaType =List.class,
//                    many = @Many(select = "com.itheima.dao.PermissionDao.findById")),
//            @Result(property = "users",column = "id",javaType =List.class,
//                    many = @Many(select = "com.itheima.dao.UserInfoDao.findById_Users_Role")),
//    })
    List<Role> finAll();



    /***
     * 存储一条信息
     * @param role
     * @return
     */
    @Insert("insert into role (roleName,roleDesc) values (#{roleName},#{roleDesc})")
    Integer save(Role role);


    /***
     * 根据用户Id查询该用户所有没有的角色
     * @param id
     * @return
     */
    @Select("select * from role where id not in (select roleId from users_role where userId = #{id})")
    List<Role> findUserByIdAndAllRole(String id) throws Exception;

}

//    private String id;
//    private String roleName;
//    private String roleDesc;
//    private List<Permission> permissions;
//    private List<UserInfo> users;