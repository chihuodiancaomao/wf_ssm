package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.itheima.dao.RoleDao;
import com.itheima.dao.UserInfoDao;
import com.itheima.dao.Users_RoleDao;
import com.itheima.domain.Role;
import com.itheima.domain.UserInfo;
import com.itheima.service.UserService;
import com.itheima.utils.PasswordEncoderUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService  {

    @Autowired
    private UserInfoDao userInfoDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private Users_RoleDao users_roleDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserInfo userInfo = userInfoDao.findByUsername(username);
        UserDetails user = new User(userInfo.getUsername(),userInfo.getPassword(),
                userInfo.getStatus()==0 ? false:true ,true,true,true,
                getAuthorities(userInfo.getRoles()) );
        return user;
    }
    private List<SimpleGrantedAuthority> getAuthorities(List<Role> roles){

        List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
        return authorities;
    }


    /***
     * 分页查询所有
     * @return
     */
    @Override
    public List<UserInfo> finAll(Integer currentPage , Integer pageSize) {
        PageHelper.startPage(currentPage,pageSize);
        return userInfoDao.findAll();
    }


    /***
     * 添加1条用户信息
     * @param userInfo
     * @return
     */
    @Override
    public Integer save(UserInfo userInfo) {

        String passwordEncoder = PasswordEncoderUtils.getPasswordEncoder(userInfo.getPassword());
        userInfo.setPassword(passwordEncoder);
        return userInfoDao.save(userInfo);
    }



    /***
     * 根据ID查询详细信息
     */
    @Override
    public UserInfo findById(String id) {
        return userInfoDao.findById(id);
    }


    /***
     * 根据用户Id查询该用户所有没有的角色
     * @param id
     * @return
     */
    @Override
    public List<Role> findUserByIdAndAllRole(String id) throws Exception{
        return roleDao.findUserByIdAndAllRole(id);
    }


    /***
     * 给用户绑定角色信息
     * @param ids
     * @param userId
     * @return
     */
    @Override
    public Integer addRoleToUser(String[] ids, String userId) {
        Integer  endResult=0;
        for (String id : ids) {
            Integer result = users_roleDao.addRoleToUser(id,userId);
            endResult +=result;
        }
        return endResult;
    }
}

