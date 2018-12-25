package com.itheima.dao;

import com.itheima.domain.Member;
import org.apache.ibatis.annotations.Select;

public interface MemberDao {
    /***
     * 根据Id查询一条语句
     */
    @Select("select * from member where id =#{id}")
    Member findById(String id);
}
