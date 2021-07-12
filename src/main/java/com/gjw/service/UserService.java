package com.gjw.service;

import com.gjw.bean.User;
import org.springframework.scheduling.annotation.Async;

public interface UserService {

    User queryUserByAcc(String acc);

    //@Async开启异步，main方法中也要EnableAsync
    int insertUser(User user);


}
