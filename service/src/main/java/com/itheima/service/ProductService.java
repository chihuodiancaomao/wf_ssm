package com.itheima.service;

import com.itheima.domain.Product;

import java.util.List;

public interface ProductService {
    /***
     * 查询Product表中的所有内容
     */
    List<Product> findAll(Integer currentPage,Integer pageSize);

    /***
     * 怎加一条信息
     * @return
     */
    Integer saveOne(Product product);
}
