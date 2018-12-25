package com.itheima.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.Orders;
import com.itheima.domain.Role;
import com.itheima.domain.UserInfo;
import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /***
     * 分页查询所有
     * @return
     */
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name="currentPage", required = true, defaultValue ="1") Integer currentPage,
                                @RequestParam(name="pageSize",required = true,defaultValue ="5") Integer pageSize){

        ModelAndView mv = new ModelAndView();
        List<UserInfo> userList = userService.finAll(currentPage,pageSize);
        PageInfo pageInfo = new PageInfo(userList);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("user_list");
        return mv;
    }

    /***
     * 添加1条用户信息
     * @param userInfo
     * @return
     */
    @RequestMapping("/save.do")
    public ModelAndView save(UserInfo userInfo){

        ModelAndView mv = new ModelAndView();
        Integer result = userService.save(userInfo);
        if(result==1){
            mv=findAll(1,5);
        }else{
            mv.setViewName("user_add");
        }
        return mv;
    }

    /***
     * 根据ID查询详细信息
     */
    @RequestMapping("/findById.do")
    public ModelAndView findById(String id){

        ModelAndView mv = new ModelAndView();
        UserInfo user = userService.findById(id);
        mv.addObject("user",user);
        mv.setViewName("user_show");
        return mv;
    }


    /***
     * 根据用户Id查询该用户所有没有的角色
     * @param id
     * @return
     */
    @RequestMapping("/findUserByIdAndAllRole.do")
    public ModelAndView findUserByIdAndAllRole(String id) throws Exception {

        ModelAndView mv = new ModelAndView();
        List<Role> roleList = userService.findUserByIdAndAllRole(id);
        mv.addObject("roleList",roleList);
        mv.addObject("userId",id);
        mv.setViewName("user_role_add");
        return mv;
    }


    /***
     * 给用户绑定角色信息
     * @param ids
     * @param userId
     * @return
     */
    @RequestMapping("/addRoleToUser.do")
    public ModelAndView addRoleToUser (String[] ids, String userId){

        ModelAndView mv = new ModelAndView();
        Integer result = userService.addRoleToUser(ids,userId);
        Integer idsLength = ids.length;
//        System.out.println("result:"+result);
//        System.out.println("idsLength:"+idsLength);
        if(result==0){
            mv.setViewName("user_role_add");
        }else if(idsLength==result){
            mv=findAll(1,5);
        }
        return mv;
    }

}
