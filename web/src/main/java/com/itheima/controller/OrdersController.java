package com.itheima.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.domain.Orders;
import com.itheima.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {
    @Autowired
    private OrdersService ordersService;



    /***
     * 分页查询所有
     * @return
     */
    @Secured({"ROLE_USER"})
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name="currentPage", required = true, defaultValue ="1") Integer currentPage,
                                @RequestParam(name="pageSize",required = true,defaultValue ="5") Integer pageSize){
        ModelAndView mv = new ModelAndView();
        List<Orders> orderList = ordersService.findAll(currentPage,pageSize);
        PageInfo pageInfo = new PageInfo(orderList);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("orders_list");
        return mv;
    }

    /***
     * 根据ID查询一条信息
     */
    @RequestMapping("findById.do")
    public ModelAndView findById(String id){
        ModelAndView mv = new ModelAndView();
        Orders orders = ordersService.findById(id);
        mv.addObject("orders",orders);
        mv.setViewName("orders_show");
        return mv;
    }
}
