package com.baby.babyproject.module.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @ClassName BabyNamesDelReq
 * @Description 删除请求体
 * @Author lilinsong
 * @Date 2020/4/18
 * @Version 1.0
 */
@Data
public class BabyNamesDelReq implements Serializable {
    /** 主键id */
    @NotNull(message = "参数id不能为空")
    private List<String> idList;
}
