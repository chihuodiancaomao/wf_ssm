package com.itheima.dao;

import com.itheima.domain.Orders;

import java.util.List;

public interface OrdersDao {

    /***
     * 查询所有
     * @return
     */
    List<Orders> findAll();


    /***
     * 根据ID查询一条信息
     */
    Orders findById(String id);
}
