package com.baby.babyproject.module.request;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;

/**
 * @ClassName BabyNamesListReq
 * @Description 条件获取宝贝姓名参数
 * @Author lilinsong
 * @Date 2020/4/18
 * @Version 1.0
 */
@Data
public class BabyNamesListReq implements Serializable {

    @Min(value = 1, message = "不合法的分页参数")
    private int pageNum = 1;

    @Min(value = 1, message = "不合法的分页参数")
    @Max(value = 100, message = "不合法的分页参数")
    private int pageSize = 20;

    /** 宝贝姓名 */
    private String babyName;
}
