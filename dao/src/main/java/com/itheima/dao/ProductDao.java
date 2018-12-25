package com.itheima.dao;

import com.itheima.domain.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/***
 * 查询Product表中的所有内容
 */

public interface ProductDao {
    /***
     * 查询product表所有信息
     * @return
     */
    List<Product> findAll();

    /***
     * 增加一条信息
     * @return
     */
    Integer saveOne(Product product);


    /***
     * 根据Id查询一条信息
     * @param id
     * @return
     */
    @Select("select * from product where id=#{id}")
    Product findById(String id);
}
