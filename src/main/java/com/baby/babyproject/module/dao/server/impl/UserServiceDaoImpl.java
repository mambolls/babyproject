package com.baby.babyproject.module.dao.server.impl;

import com.baby.babyproject.constants.StateNumber;
import com.baby.babyproject.module.dao.entity.User;
import com.baby.babyproject.module.dao.entity.UserExample;
import com.baby.babyproject.module.dao.mapper.UserMapper;
import com.baby.babyproject.module.dao.server.IUserServiceDao;
import com.baby.babyproject.util.ObjectHelper;
import com.baby.babyproject.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName UserServiceImpl
 * @Description 提交用户dao层service
 * @Author lilinsong
 * @Date 2020/4/17
 * @Version 1.0
 */
@Service
@Slf4j
public class UserServiceDaoImpl implements IUserServiceDao {
    
    /** 用户持久层mapper */
    @Autowired
    private UserMapper userMapper;

    @Override
    public Result countByExample(UserExample example) {
        try{
            long count = this.userMapper.countByExample(example);
            return Result.newSuccess(count);
        }catch (Exception e){
            log.error("获取数据count出错");
            e.printStackTrace();
            return Result.newException(StateNumber.DAO_ERR,e);
        }
    }

    @Override
    public Result deleteByExample(UserExample example) {
        try{
            int state = this.userMapper.deleteByExample(example);
            if (ObjectHelper.isNotEmpty(state) && 0 < state){
                return Result.newSuccess();
            }else {
                return Result.newFailure(StateNumber.DAO_FIL,"条件删除失败！");
            }
        }catch (Exception e){
            log.error("条件删除数据出错！");
            e.printStackTrace();
            return Result.newException(StateNumber.DAO_ERR,e);
        }
    }

    @Override
    public Result deleteByPrimaryKey(String id) {
        try{
            int state = this.userMapper.deleteByPrimaryKey(id);
            if (ObjectHelper.isNotEmpty(state) && 0 < state){
                return Result.newSuccess();
            }else {
                return Result.newFailure(StateNumber.DAO_FIL,"主键删除失败！");
            }
        }catch (Exception e){
            log.error("主键删除出错！");
            e.printStackTrace();
            return Result.newException(StateNumber.DAO_ERR,e);
        }
    }

    @Override
    public Result insert(User record) {
        try{
            int state = this.userMapper.insert(record);
            if (ObjectHelper.isNotEmpty(state) && 0 < state){
                return Result.newSuccess();
            }else {
                return Result.newFailure(StateNumber.DAO_FIL,"插入一条数据失败！");
            }
        }catch (Exception e){
            log.error("插入一条数据出错！");
            e.printStackTrace();
            return Result.newException(StateNumber.DAO_ERR,e);
        }
    }

    @Override
    public Result insertSelective(User record) {
        try{
            int state = this.userMapper.insertSelective(record);
            if (ObjectHelper.isNotEmpty(state) && 0 < state){
                return Result.newSuccess();
            }else {
                return Result.newFailure(StateNumber.DAO_FIL,"插入一条数据失败！");
            }
        }catch (Exception e){
            log.error("插入一条数据出错！");
            e.printStackTrace();
            return Result.newException(StateNumber.DAO_ERR,e);
        }
    }

    @Override
    public Result<List<User>> selectByExample(UserExample example) {
        try{
            List<User> userList = this.userMapper.selectByExample(example);
            return Result.newSuccess(userList);
        }catch (Exception e){
            log.error("条件获取数据出错！");
            e.printStackTrace();
            return Result.newException(StateNumber.DAO_ERR,e);
        }
    }

    @Override
    public Result<User> selectByPrimaryKey(String id) {
        try{
            User User = this.userMapper.selectByPrimaryKey(id);
            return Result.newSuccess(User);
        }catch (Exception e){
            log.error("条件获取数据出错！");
            e.printStackTrace();
            return Result.newException(StateNumber.DAO_ERR,e);
        }
    }

    @Override
    public Result updateByExampleSelective(User record, UserExample example) {
        try{
            int state = this.userMapper.updateByExampleSelective(record,example);
            if (ObjectHelper.isNotEmpty(state) && 0 < state){
                return Result.newSuccess();
            }else {
                return Result.newFailure(StateNumber.DAO_FIL,"修改数据失败！");
            }
        }catch (Exception e){
            log.error("修改数据出错！");
            e.printStackTrace();
            return Result.newException(StateNumber.DAO_ERR,e);
        }
    }

    @Override
    public Result updateByExample(User record, UserExample example) {
        try{
            int state = this.userMapper.updateByExample(record,example);
            if (ObjectHelper.isNotEmpty(state) && 0 < state){
                return Result.newSuccess();
            }else {
                return Result.newFailure(StateNumber.DAO_FIL,"修改数据失败！");
            }
        }catch (Exception e){
            log.error("修改数据出错！");
            e.printStackTrace();
            return Result.newException(StateNumber.DAO_ERR,e);
        }
    }

    @Override
    public Result updateByPrimaryKeySelective(User record) {
        try{
            int state = this.userMapper.updateByPrimaryKeySelective(record);
            if (ObjectHelper.isNotEmpty(state) && 0 < state){
                return Result.newSuccess();
            }else {
                return Result.newFailure(StateNumber.DAO_FIL,"修改单条数据失败！");
            }
        }catch (Exception e){
            log.error("修改单条数据出错！");
            e.printStackTrace();
            return Result.newException(StateNumber.DAO_ERR,e);
        }
    }

    @Override
    public Result updateByPrimaryKey(User record) {
        try{
            int state = this.userMapper.updateByPrimaryKey(record);
            if (ObjectHelper.isNotEmpty(state) && 0 < state){
                return Result.newSuccess();
            }else {
                return Result.newFailure(StateNumber.DAO_FIL,"修改单条数据失败！");
            }
        }catch (Exception e){
            log.error("修改单条数据出错！");
            e.printStackTrace();
            return Result.newException(StateNumber.DAO_ERR,e);
        }
    }

    @Override
    public Result<User> selectOneByExample(UserExample example) {
        try{
            User User = this.userMapper.selectOneByExample(example);
            return Result.newSuccess(User);
        }catch (Exception e){
            log.error("条件获取数据出错！");
            e.printStackTrace();
            return Result.newException(StateNumber.DAO_ERR,e);
        }
    }
}
