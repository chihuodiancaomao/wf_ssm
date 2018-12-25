package com.itheima.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.Product;
import com.itheima.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;



    /***
     * 分页查询所有
     * @return
     */
    @RolesAllowed({"ROLE_ADMIN"})
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name="currentPage",required = true,defaultValue ="1") Integer currentPage,
                                 @RequestParam(name="pageSize",required = true,defaultValue ="5") Integer pageSize){
        ModelAndView mv = new ModelAndView();
        List<Product> list = productService.findAll(currentPage,pageSize);
        PageInfo pageInfo = new PageInfo(list);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("product_list");
        return mv;
    }


    /***
     * 存储一条信息
     * @param product
     * @return
     */
    @RequestMapping("/saveOne")
    public ModelAndView saveOne(Product product){
        ModelAndView mv = new ModelAndView();
        System.out.println(product.getDepartureTime());
        Integer result = productService.saveOne(product);
        if (result==1){
            mv=findAll(1,5);
        }else {
            mv.setViewName("product_add");
        }
        return mv;
    }
}
