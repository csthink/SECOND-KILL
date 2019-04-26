package com.csthink.secondkill.dao;

import com.csthink.secondkill.entity.User;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserDao {

    void insert(User user);

    void delete(Integer id);

    void update(User user);

//    @Select("select * from user where id=#{id}")
    User selectById(@Param("id") Integer id);

//    @Select("select * from user")
    List<User> selectAll();

}
