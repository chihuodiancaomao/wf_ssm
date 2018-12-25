package com.itheima.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.domain.Permission;
import com.itheima.domain.Role;
import com.itheima.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;


    /***
     * 查询所有
     * @param currentPage
     * @param pageSize
     * @return
     */
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name="currentPage",required=true,defaultValue="1") Integer currentPage,
                                @RequestParam(name="pageSize",required = true,defaultValue = "5") Integer pageSize) {
        ModelAndView mv = new ModelAndView();
        List<Role> roleList = roleService.findAll(currentPage,pageSize);
        PageInfo pageInfo = new PageInfo(roleList);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("role_list");
        return mv;
    }


    /***
     * 存储一条信息
     * @param role
     * @return
     */
    @RequestMapping("/save.do")
    public ModelAndView save(Role role){
        ModelAndView mv = new ModelAndView();
        Integer result = roleService.save(role);
        if(result==1){
            mv = findAll(1,5);
        }else{
            mv.setViewName("role_add");
        }
        return mv;
    }

    /**
     * 根据角色Id查找该角色没有的权限
     * @param id
     * @return
     */
    @RequestMapping("/findRoleByIdAndAllPermission.do")
    public ModelAndView findRoleByIdAndAllPermission(String id) throws Exception{

        ModelAndView mv = new ModelAndView();
        List<Permission> permissionList =  roleService.findRoleByIdAndAllPermission(id);
        mv.addObject("permissionList",permissionList);
        mv.addObject("roleId",id);
        mv.setViewName("role_permission_add");
        return mv;
    }


    /***
     * 添加角色的权限
     * @param ids
     * @param roleId
     * @return
     */
    @RequestMapping("/addPermissionToRole.do")
    public ModelAndView addPermissionToRole (String[] ids, String roleId){

        ModelAndView mv = new ModelAndView();
        Integer result = roleService.addPermissionToRole(ids,roleId);
        Integer idsLength = ids.length;
        if(result==0){
            mv.setViewName("user_role_add");
        }else if(idsLength==result){
            mv=findAll(1,5);
        }
        return mv;
    }

}
