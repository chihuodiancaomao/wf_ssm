package com.itheima.service;

import com.itheima.domain.Orders;

import java.util.List;

public interface OrdersService {


    /***
     * 分页查询所有
     * @return
     */
    List<Orders> findAll(Integer currentPage,Integer pageSize);


    /***
     * 根据ID查询一条信息
     */
    Orders findById(String id);
}
