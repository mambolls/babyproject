package com.baby.babyproject.module.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName TokenValidReq
 * @Description
 * @Author lilinsong
 * @Date 2020/7/16 14:16
 * @Version 1.0
 */
@Data
public class TokenValidReq implements Serializable {

    private String token;
}
