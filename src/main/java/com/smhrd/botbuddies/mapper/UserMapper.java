package com.smhrd.botbuddies.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.smhrd.botbuddies.entity.User;

@Mapper
public interface UserMapper {

    public List<User> selectUser();

    public User login(String id, String password);


    public void signup(String id , String pw, String name, String phone);
    public int idcheck(String id);

}
