package com.baby.babyproject.module.response;

import com.baby.babyproject.module.dao.entity.User;
import lombok.Data;

import java.io.Serializable;
import java.net.UnknownServiceException;
import java.util.Date;

/**
 * @ClassName BabyNamesRsp
 * @Description 宝贝姓名数据获取响应请求
 * @Author lilinsong
 * @Date 2020/4/18
 * @Version 1.0
 */
@Data
public class BabyNamesRsp implements Serializable {

    /** 主键id */
    private Integer id;
    /** 宝贝姓名 */
    private String babyName;
    /** 宝贝姓名解释 */
    private String babyNameExplain;
    /** 提交时间 */
    private Date commitTime;
    /** 提交人 */
    private User user;
}
