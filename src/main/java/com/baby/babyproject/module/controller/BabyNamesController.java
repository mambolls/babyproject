package com.baby.babyproject.module.controller;

import com.baby.babyproject.module.dao.entity.BabyNames;
import com.baby.babyproject.module.request.*;
import com.baby.babyproject.module.response.BabyNamesRsp;
import com.baby.babyproject.module.response.LoginResp;
import com.baby.babyproject.module.server.IBabyNamesService;
import com.baby.babyproject.util.Result;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @ClassName BabyNamesController
 * @Description 控制接口
 * @Author lilinsong
 * @Date 2020/4/18
 * @Version 1.0
 */
@RestController()
@RequestMapping("/api/babyNames")
public class BabyNamesController {

    @Autowired
    private IBabyNamesService babyNamesService;

    @PostMapping("/insert")
    public Result<BabyNames> insert(@RequestBody BabyNamesSaveReq req){
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

    @PostMapping("/getOne")
    public Result<BabyNamesRsp> getOne(@Valid @RequestBody BabyNameReq req){
        return this.babyNamesService.getOne(req);
    }

    @PostMapping("/valid")
    public Result<LoginResp> login(@RequestBody LoginReq req){
        return this.babyNamesService.login(req);

    }

    @PostMapping("/token")
    public Result<Boolean> token(@RequestBody TokenValidReq req){
        return this.babyNamesService.tokenValid(req);

    }
}
