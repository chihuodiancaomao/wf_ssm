package com.itheima.service;

import com.itheima.domain.Role;
import com.itheima.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    /***
     * 分页查询所有
     * @return
     */
    List<UserInfo> finAll(Integer currentPage , Integer pageSize);


    /***
     * 添加1条用户信息
     * @param userInfo
     * @return
     */
    Integer save(UserInfo userInfo);


    /***
     * 根据ID查询详细信息
     */
    UserInfo findById(String id);


    /***
     * 根据用户Id查询该用户所有没有的角色
     * @param id
     * @return
     */
    List<Role> findUserByIdAndAllRole(String id) throws Exception;

    /***
     * 给用户绑定角色信息
     * @param ids
     * @param userId
     * @return
     */
    Integer addRoleToUser(String[] ids, String userId);
}
