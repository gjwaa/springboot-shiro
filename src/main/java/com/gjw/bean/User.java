package com.gjw.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("用户实体类")
public class User {

    @ApiModelProperty("主键")
    private Integer uid;
    private String acc;
    private String pwd;
    private String salt;
    private String perms;


}
