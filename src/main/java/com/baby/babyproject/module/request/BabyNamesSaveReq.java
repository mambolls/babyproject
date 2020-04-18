package com.baby.babyproject.module.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName BabyNamesSaveReq
 * @Description 插入数据请求参数
 * @Author lilinsong
 * @Date 2020/4/18
 * @Version 1.0
 */
@Data
public class BabyNamesSaveReq implements Serializable {

    /** 宝贝姓名 */
    private String babyName;
    /** 宝贝姓名解释 */
    private String babyNameExplain;
    /** 提交者姓名 */
    private String userName;
    /** 提交者电话号码 */
    private String phone;
}
