package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.itheima.dao.ProductDao;
import com.itheima.domain.Product;
import com.itheima.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    /***
     * 查询Product表中的所有内容
     */
    @Override
    public List<Product> findAll(Integer currentPage,Integer pageSize) {
        PageHelper.startPage(currentPage,pageSize);
        return productDao.findAll();
    }


    /***
     * 增加一条信息
     * @return
     */
    @Override
    public Integer saveOne(Product product) {

        Integer result = productDao.saveOne(product);
        return result;
    }
}
