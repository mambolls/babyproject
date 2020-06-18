package com.baby.babyproject.module.dao.server.impl;

import com.baby.babyproject.module.dao.entity.BabyProjectLog;
import com.baby.babyproject.module.dao.mapper.BabyProjectLogMapper;
import com.baby.babyproject.module.dao.server.IBabyProjectLogServiceDao;
import com.baby.babyproject.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName BabyProjectLogServiceDapImpl
 * @Description
 * @Author lilinsong
 * @Date 2020/6/16 17:03
 * @Version 1.0
 */
@Slf4j
@Service("babyProjectLogServiceDapImpl")
public class BabyProjectLogServiceDapImpl implements IBabyProjectLogServiceDao {

    @Autowired
    private BabyProjectLogMapper babyProjectLogMapper;

    @Override
    public Result insertSelective(BabyProjectLog record) {
        this.babyProjectLogMapper.insertSelective(record);
        return Result.newSuccess();
    }
}
