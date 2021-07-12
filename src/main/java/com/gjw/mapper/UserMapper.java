package com.gjw.mapper;

import com.gjw.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper {

    User queryUserByAcc(String acc);

    int insertUser(User user);

}
