package com.baby.babyproject.daotest;

import com.alibaba.fastjson.JSON;
import com.baby.babyproject.compoment.JedisUtil;
import com.baby.babyproject.module.dao.entity.BabyNames;
import com.baby.babyproject.module.dao.entity.BabyNamesExample;
import com.baby.babyproject.module.dao.entity.User;
import com.baby.babyproject.module.dao.entity.UserExample;
import com.baby.babyproject.module.dao.server.IBabyNamesServiceDao;
import com.baby.babyproject.module.dao.server.IUserServiceDao;
import com.baby.babyproject.util.FormatJsonUtil;
import com.baby.babyproject.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

/**
 * @ClassName DaoTest
 * @Description
 * @Author lilinsong
 * @Date 2020/4/17
 * @Version 1.0
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class DaoTest {

    @Autowired
    private IBabyNamesServiceDao babyNamesService;

    @Autowired
    private IUserServiceDao userService;

    @Test
    public void insertBabyName(){
        BabyNames babyNames = new BabyNames();
        babyNames.setBabyName("李哈哈");
        babyNames.setBabyNameExplain("哈哈哈");
        babyNames.setCommitTime(new Date());
        User user = new User();
        user.setUserName("李林松");
        user.setPhone("1221212121");
        user.setCreateTime(new Date());
        this.userService.insertSelective(user);
        babyNames.setUesrId(user.getId());
        this.babyNamesService.insertSelective(babyNames);
    }

    @Test
    public void getBabyName(){
        BabyNamesExample example = new BabyNamesExample();
        Result<List<BabyNames>> listResult = this.babyNamesService.selectByExample(example);
        log.info("babyNames{}", FormatJsonUtil.formatJson(JSON.toJSONString(listResult)));
        log.info("这是一条分割线=======================================================");
        UserExample userExample = new UserExample();
        userExample.createCriteria().andIdEqualTo(2);
        Result<List<User>> userList = this.userService.selectByExample(userExample);
        log.info("users{}", FormatJsonUtil.formatJson(JSON.toJSONString(userList)));
    }

    @Test
    public void redisTest(){
        JedisUtil.set("testkey","李林松");
        log.info(JedisUtil.get("testkey"));
    }
}
