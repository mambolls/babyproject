package com.baby.babyproject.module.dao.server;

import com.baby.babyproject.module.dao.entity.BabyNames;
import com.baby.babyproject.module.dao.entity.BabyNamesExample;
import com.baby.babyproject.util.Result;

import java.util.List;

public interface IBabyNamesServiceDao {

    Result countByExample(BabyNamesExample example);

    Result deleteByExample(BabyNamesExample example);

    Result deleteByPrimaryKey(String id);

    Result insert(BabyNames record);

    Result insertSelective(BabyNames record);

    Result<List<BabyNames>> selectByExample(BabyNamesExample example);

    Result<BabyNames> selectByPrimaryKey(String id);

    Result updateByExampleSelective( BabyNames record, BabyNamesExample example);

    Result updateByExample(BabyNames record, BabyNamesExample example);

    Result updateByPrimaryKeySelective(BabyNames record);

    Result updateByPrimaryKey(BabyNames record);

    Result<BabyNames> selectOneByExample(BabyNamesExample example);

}
