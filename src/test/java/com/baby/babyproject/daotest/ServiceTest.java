package com.baby.babyproject.daotest;

import com.alibaba.fastjson.JSON;
import com.baby.babyproject.module.dao.entity.BabyNames;
import com.baby.babyproject.module.request.BabyNamesDelReq;
import com.baby.babyproject.module.request.BabyNamesListReq;
import com.baby.babyproject.module.request.BabyNamesSaveReq;
import com.baby.babyproject.module.response.BabyNamesRsp;
import com.baby.babyproject.module.server.IBabyNamesService;
import com.baby.babyproject.util.FormatJsonUtil;
import com.baby.babyproject.util.Result;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ServiceTest
 * @Description
 * @Author lilinsong
 * @Date 2020/4/18
 * @Version 1.0
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class ServiceTest {

    @Autowired
    private IBabyNamesService babyNamesService;

    @Test
    public void insert(){
        BabyNamesSaveReq babyNamesSaveReq = new BabyNamesSaveReq();
        babyNamesSaveReq.setBabyName("李呵呵");
        babyNamesSaveReq.setBabyNameExplain("呵呵");
        babyNamesSaveReq.setUserName("李林松");
        babyNamesSaveReq.setPhone("1221212121");
        Result<BabyNames> stringResult = this.babyNamesService.insertBabyName(babyNamesSaveReq);
        log.info("新增结果：{}", FormatJsonUtil.formatJson(JSON.toJSONString(stringResult)));
    }

    @Test
    public void list(){
        BabyNamesListReq babyNamesListReq = new BabyNamesListReq();
        babyNamesListReq.setPageNum(1);
//        babyNamesListReq.setPageSize();
//        babyNamesListReq.setBabyName();
        Result<PageInfo<BabyNamesRsp>> result = this.babyNamesService.listBabyNames(babyNamesListReq);
        log.info("获取结果：{}", FormatJsonUtil.formatJson(JSON.toJSONString(result)));
    }

    @Test
    public void delete(){
        BabyNamesDelReq req = new BabyNamesDelReq();
        List<Integer> idList = new ArrayList<>();
        idList.add(2);
        req.setIdList(idList);
        log.info(idList.size()+"");
        Result<String> result = this.babyNamesService.deleteBabyNames(req);
        log.info("获取结果：{}", FormatJsonUtil.formatJson(JSON.toJSONString(result)));
    }
}
