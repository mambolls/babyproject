package com.baby.babyproject.module.response;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName LoginResp
 * @Description 登录响应
 * @Author lilinsong
 * @Date 2020/7/16 11:49
 * @Version 1.0
 */
@Data
public class LoginResp implements Serializable {

    /** 登录后token */
    private String token;

    /** 登录状态：true 成功  */
    private Boolean status;
}
