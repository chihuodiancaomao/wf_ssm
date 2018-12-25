package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.itheima.dao.OrdersDao;
import com.itheima.domain.Orders;
import com.itheima.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersDao ordersDao;

    /***
     * 分页查询所有
     * @return
     */
    @Override
    public List<Orders> findAll(Integer currentPage,Integer pageSize) {
        PageHelper.startPage(currentPage,pageSize);
        return ordersDao.findAll();
    }

    /***
     * 根据ID查询一条信息
     */
    @Override
    public Orders findById(String id) {
        return ordersDao.findById(id);
    }
}
