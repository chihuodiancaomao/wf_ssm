package com.itheima.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.Permission;
import com.itheima.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;


    /***
     * 查询所有
     * @param currentPage
     * @param pageSize
     * @return
     */
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name="currentPage",required = true,defaultValue ="1") Integer currentPage,
                                @RequestParam(name="pageSize",required = true,defaultValue ="5") Integer pageSize) {

        ModelAndView mv = new ModelAndView();
        List<Permission> permissionList = permissionService.findAll(currentPage,pageSize);
        PageInfo pageInfo = new PageInfo(permissionList);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("permission_list");
        return mv;
    }


    /***
     * 添加一条数据
     * @param permission
     * @return
     */
    @RequestMapping("/save.do")
    public ModelAndView save(Permission permission){

        ModelAndView mv = new ModelAndView();
        Integer result = permissionService.save(permission);
        if(result==1){
            mv=findAll(1,5);
        }
        else if(result==0){
            mv.setViewName("product_add");
        }
        return mv;
    }
}
