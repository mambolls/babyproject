package com.baby.babyproject.module.server.impl;

import com.baby.babyproject.module.dao.entity.BabyNames;
import com.baby.babyproject.module.dao.entity.BabyNamesExample;
import com.baby.babyproject.module.dao.entity.User;
import com.baby.babyproject.module.dao.entity.UserExample;
import com.baby.babyproject.module.dao.server.IBabyNamesServiceDao;
import com.baby.babyproject.module.dao.server.IUserServiceDao;
import com.baby.babyproject.module.request.*;
import com.baby.babyproject.module.response.BabyNamesRsp;
import com.baby.babyproject.module.response.LoginResp;
import com.baby.babyproject.module.server.IBabyNamesService;
import com.baby.babyproject.util.JwtTokenUtil;
import com.baby.babyproject.util.JwtUserInfo;
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
import java.util.UUID;

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
    public Result<BabyNames> insertBabyName(BabyNamesSaveReq req) {
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
                user.setId(UUID.randomUUID().toString());
                Result result = this.userServiceDao.insertSelective(user);
                if (result.getCode() == Result.CODE_FAIL){
                    log.error("用户信息插入错误：{}",result.getMessage());
                    return result;
                }
            }else {
                user = object;
            }
        }
        BeanUtils.copyProperties(req,babyNames);
        babyNames.setId(UUID.randomUUID().toString());
        babyNames.setUesrId(user.getId());
        babyNames.setCommitTime(new Date());
        Result result = this.babyNamesServiceDao.insertSelective(babyNames);
       if (Result.CODE_SUCCESS == result.getCode()){
           result.setMessage("操作成功！");
           result.setObject(babyNames);
       }
        return result;
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
        List<String> userIds = new ArrayList<>();
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

    @Override
    public Result<BabyNamesRsp> getOne(BabyNameReq req) {
        BabyNames babyName = this.babyNamesServiceDao.selectByPrimaryKey(req.getId()).getObject();
        if (ObjectHelper.isEmpty(babyName)){
            Result<BabyNamesRsp> result = Result.newSuccess(null);
            result.setMessage("数据不存在！");
            return result;
        }
        BabyNamesRsp rsp = new BabyNamesRsp();
        BeanUtils.copyProperties(babyName,rsp);
        return Result.newSuccess(rsp);
    }

    /** 
     * @Name login
     * @Description 登录
     * @Param [req]
     * @Return com.baby.babyproject.util.Result<com.baby.babyproject.module.response.LoginResp>
     * @Author lilinsong 
     * @Date 12:34 2020/7/16
     **/
    @Override
    public Result<LoginResp> login(LoginReq req) {
        // 查询用户信息
        UserExample example = new UserExample();
        example.createCriteria().andPhoneEqualTo(req.getPhone());
        User user = this.userServiceDao.selectOneByExample(example).getObject();
        if (ObjectHelper.isEmpty(user)){
            return Result.newFailure("用户不存在","user is null");
        }
        if (ObjectHelper.isEmpty(user.getPassWord())){return Result.newFailure("用户不存在","user is null");}
        boolean equals = req.getPassword().equals(user.getPassWord());
        if (!equals){return Result.newFailure("密码错误","passWord is error");}
        // 获取token
        String uuid = UUID.randomUUID().toString();
        JwtUserInfo userInfo = new JwtUserInfo();
        userInfo.setPatientId(user.getId());
        userInfo.setPhoneNumber(user.getPhone());
        userInfo.setUuid(uuid);
        String token = JwtTokenUtil.generateToken(uuid, userInfo);
        LoginResp loginResp = new LoginResp();
        loginResp.setToken(token);
        loginResp.setStatus(equals);
        return Result.newSuccess(loginResp);
    }

    /** 
     * @Name tokenValid
     * @Description token验证
     * @Param [req]
     * @Return com.baby.babyproject.util.Result<java.lang.Boolean>
     * @Author lilinsong 
     * @Date 14:18 2020/7/16
     **/
    @Override
    public Result<Boolean> tokenValid(TokenValidReq req) {
        try{
            if (ObjectHelper.isEmpty(req.getToken())){return Result.newFailure("token is null","token为空");}
            JwtUserInfo userInfo = JwtTokenUtil.getPayLoad(req.getToken());
            if (ObjectHelper.isEmpty(userInfo)){return Result.newFailure("user is null","用户不存在");}
            User object = this.userServiceDao.selectByPrimaryKey(userInfo.getPatientId()).getObject();
            if (ObjectHelper.isEmpty(object)){return  Result.newFailure("user is null","用户不存在");}
            return Result.newSuccess(true);
        }catch (Exception e) {
            log.error("token 解析异常：{}",e.getMessage());
            return Result.newFailure("token is exception","token 解析异常");
        }
    }
}
