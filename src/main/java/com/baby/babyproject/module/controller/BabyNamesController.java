package com.baby.babyproject.module.controller;

import com.baby.babyproject.module.request.BabyNamesDelReq;
import com.baby.babyproject.module.request.BabyNamesListReq;
import com.baby.babyproject.module.request.BabyNamesSaveReq;
import com.baby.babyproject.module.response.BabyNamesRsp;
import com.baby.babyproject.module.server.IBabyNamesService;
import com.baby.babyproject.util.Result;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName BabyNamesController
 * @Description 控制接口
 * @Author lilinsong
 * @Date 2020/4/18
 * @Version 1.0
 */
@RestController()
@RequestMapping("/babyNames")
public class BabyNamesController {

    @Autowired
    private IBabyNamesService babyNamesService;

    @PostMapping("/insert")
    public Result<String> insert(@RequestBody BabyNamesSaveReq req){
        return this.babyNamesService.insertBabyName(req);
    }

    @PostMapping("/list")
    public Result<PageInfo<BabyNamesRsp>> insert(@RequestBody BabyNamesListReq req){
        return this.babyNamesService.listBabyNames(req);
    }
    @PostMapping("/delete")
    public Result<String> insert(@RequestBody BabyNamesDelReq req){
        return this.babyNamesService.deleteBabyNames(req);
    }
}
