package com.baby.babyproject.module.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @ClassName BabyNameReq
 * @Description 根据id获取一个数据
 * @Author lilinsong
 * @Date 2020/6/18 9:02
 * @Version 1.0
 */
@Data
public class BabyNameReq implements Serializable {

    @NotNull(message = "id不能为空")
    private Integer id;
}
