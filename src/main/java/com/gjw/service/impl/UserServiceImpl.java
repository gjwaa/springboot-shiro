package com.gjw.service.impl;

import com.gjw.bean.User;
import com.gjw.mapper.UserMapper;
import com.gjw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User queryUserByAcc(String acc) {
        return userMapper.queryUserByAcc(acc);
    }

    @Override
    public int insertUser(User user) {
        return userMapper.insertUser(user);
    }
}
