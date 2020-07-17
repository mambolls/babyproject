package com.baby.babyproject.module.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName LoginReq
 * @Description
 * @Author lilinsong
 * @Date 2020/7/16 11:38
 * @Version 1.0
 */
@Data
public class LoginReq implements Serializable {

    private String phone;
    private String password;
}
