package com.baby.babyproject.module.server.impl;

import com.baby.babyproject.module.dao.entity.BabyNames;
import com.baby.babyproject.module.dao.entity.BabyNamesExample;
import com.baby.babyproject.module.dao.entity.User;
import com.baby.babyproject.module.dao.entity.UserExample;
import com.baby.babyproject.module.dao.server.IBabyNamesServiceDao;
import com.baby.babyproject.module.dao.server.IUserServiceDao;
import com.baby.babyproject.module.request.BabyNamesDelReq;
import com.baby.babyproject.module.request.BabyNamesListReq;
import com.baby.babyproject.module.request.BabyNamesSaveReq;
import com.baby.babyproject.module.response.BabyNamesRsp;
import com.baby.babyproject.module.server.IBabyNamesService;
import com.baby.babyproject.util.ObjectHelper;
import com.baby.babyproject.util.Result;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName BabyNamesServiceImpl
 * @Description 业务处理service
 * @Author lilinsong
 * @Date 2020/4/18
 * @Version 1.0
 */
@Service
@Slf4j
public class BabyNamesServiceImpl implements IBabyNamesService {

    /** 持久层处理service */
    @Autowired
    private IBabyNamesServiceDao babyNamesServiceDao;

    @Autowired
    private IUserServiceDao userServiceDao;

    @Override
    public Result<String> insertBabyName(BabyNamesSaveReq req) {
        User user = new User();
        BabyNames babyNames = new BabyNames();
        String phone = req.getPhone();
        String userName = req.getUserName();
        if (ObjectHelper.isNotEmpty(phone)
                || ObjectHelper.isNotEmpty(userName)){
            UserExample userExample = new UserExample();
            userExample.createCriteria().andPhoneEqualTo(phone);
            User object = this.userServiceDao.selectOneByExample(userExample).getObject();
            if (ObjectHelper.isEmpty(object)){
                user.setCreateTime(new Date());
                user.setPhone(phone);
                user.setUserName(userName);
                this.userServiceDao.insertSelective(user);
            }else {
                user = object;
            }
        }
        BeanUtils.copyProperties(req,babyNames);
        babyNames.setUesrId(user.getId());
        babyNames.setCommitTime(new Date());
        this.babyNamesServiceDao.insertSelective(babyNames);
        return Result.newSuccess("操作成功！");
    }

    @Override
    public Result<PageInfo<BabyNamesRsp>> listBabyNames(BabyNamesListReq req) {
        Page<BabyNamesRsp> page = PageHelper.startPage(req.getPageNum(),
                req.getPageSize());
        BabyNamesExample babyNamesExample = new BabyNamesExample();
        BabyNamesExample.Criteria criteria = babyNamesExample.createCriteria();

        UserExample userExample = new UserExample();
        UserExample.Criteria userExampleCriteria = userExample.createCriteria();

        if (ObjectHelper.isNotEmpty(req.getBabyName())){
            criteria.andBabyNameLike("%" + req.getBabyName() + "%");
        }
        List<BabyNamesRsp> result = new ArrayList<>();
        List<BabyNames> babyNames = this.babyNamesServiceDao.selectByExample(babyNamesExample).getObject();
        List<Integer> userIds = new ArrayList<>();
        babyNames.stream().forEach(e -> {
            if (ObjectHelper.isNotEmpty(e.getUesrId())){
                userIds.add(e.getUesrId());
            }
        });
        userExampleCriteria.andIdIn(userIds);
        List<User> users = this.userServiceDao.selectByExample(userExample).getObject();
        babyNames.stream().forEach(e ->{
            BabyNamesRsp rsp = new BabyNamesRsp();
            BeanUtils.copyProperties(e,rsp);
            users.stream().forEach(user ->{
                if (user.getId().equals(e.getUesrId())){
                    rsp.setUser(user);
                }
            });
            result.add(rsp);
        });
        // 需要转换的对象
        PageInfo<BabyNamesRsp> target = new PageInfo<>(page);
        target.setList(result);
        return Result.newSuccess(target);
    }

    @Override
    public Result<String> deleteBabyNames(BabyNamesDelReq req) {
        if (CollectionUtils.isEmpty(req.getIdList())){
            return Result.newFailure("参数不能为空！","参数id为空！");
        }
        BabyNamesExample example = new BabyNamesExample();
        example.createCriteria().andIdIn(req.getIdList());
        this.babyNamesServiceDao.deleteByExample(example);
        return Result.newSuccess("操作成功！");
    }
}
