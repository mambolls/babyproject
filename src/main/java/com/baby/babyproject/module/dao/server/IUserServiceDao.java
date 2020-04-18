package com.baby.babyproject.module.dao.server;

import com.baby.babyproject.module.dao.entity.User;
import com.baby.babyproject.module.dao.entity.UserExample;
import com.baby.babyproject.util.Result;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IUserServiceDao {

    Result countByExample(UserExample example);

    Result deleteByExample(UserExample example);

    Result deleteByPrimaryKey(Integer id);

    Result insert(User record);

    Result insertSelective(User record);

    Result<List<User>> selectByExample(UserExample example);

    Result<User> selectByPrimaryKey(Integer id);

    Result updateByExampleSelective( User record,  UserExample example);

    Result updateByExample( User record,  UserExample example);

    Result updateByPrimaryKeySelective(User record);

    Result updateByPrimaryKey(User record);

    Result<User> selectOneByExample(UserExample example);
}
