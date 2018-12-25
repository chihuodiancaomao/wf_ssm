package com.itheima.dao;

import com.itheima.domain.Role;
import com.itheima.domain.UserInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

public interface UserInfoDao {

    /***
     * 根据用户名查找
     * @param username
     * @return
     */
    @Select("select * from users where username=#{username}")
    @Results({@Result(id=true,property = "id",column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "email",column = "email"),
            @Result(property = "password",column = "password"),
            @Result(property = "phoneNum",column = "phoneNum"),
            @Result(property = "status",column = "status"),
            @Result(property = "roles",column = "id",javaType =List.class ,many = @Many(select ="com.itheima.dao.RoleDao.findById"))
    })
    public UserInfo findByUsername(String username);


    /***
     * 分页查询所有
     * @return
     */
    @Select("select * from users")
    List<UserInfo> findAll();



    /***
     * 添加1条用户信息
     * @param userInfo
     * @return
     */
    @Insert("insert into users (username,email,password,phoneNum,status) values" +
            "(#{username},#{email},#{password},#{phoneNum},#{status})")
    Integer save(UserInfo userInfo);



    /***
     * 根据ID查询详细信息
     */
    @Select("select * from users where id=#{id}")
    @Results({@Result(id=true,property = "id",column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "email",column = "email"),
            @Result(property = "password",column = "password"),
            @Result(property = "phoneNum",column = "phoneNum"),
            @Result(property = "status",column = "status"),
            @Result(property = "roles",column = "id",javaType =List.class ,many = @Many(select ="com.itheima.dao.RoleDao.findById"))
    })
    UserInfo findById(String id);


    /***
     * 根据关联表查询详多条信息
     */
    @Select("select * from users where id in (select userId from users_role where roleId=#{id})")
    UserInfo findById_Users_Role(String id);


}

//    private String id;
//    private String username;
//    private String email;
//    private String password;
//    private String phoneNum;
//    private int status;
//    private String statusStr;
//    private List<Role> roles;
