package com.baby.babyproject.module.dao.server;

import com.baby.babyproject.module.dao.entity.BabyProjectLog;
import com.baby.babyproject.module.dao.entity.User;
import com.baby.babyproject.util.Result;

public interface IBabyProjectLogServiceDao {

    Result insertSelective(BabyProjectLog record);
}
