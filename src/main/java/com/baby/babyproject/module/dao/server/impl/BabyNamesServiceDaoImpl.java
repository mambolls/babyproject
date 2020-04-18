package com.baby.babyproject.module.dao.server.impl;

import com.baby.babyproject.constants.StateNumber;
import com.baby.babyproject.module.dao.entity.BabyNames;
import com.baby.babyproject.module.dao.entity.BabyNamesExample;
import com.baby.babyproject.module.dao.mapper.BabyNamesMapper;
import com.baby.babyproject.module.dao.server.IBabyNamesServiceDao;
import com.baby.babyproject.util.ObjectHelper;
import com.baby.babyproject.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName BabyNamesServiceImple
 * @Description 宝贝姓名dao层service
 * @Author lilinsong
 * @Date 2020/4/17
 * @Version 1.0
 */
@Slf4j
@Service
public class BabyNamesServiceDaoImpl implements IBabyNamesServiceDao {

    /** 宝贝姓名数据持久化mapper */
    @Autowired
    private BabyNamesMapper babyNamesMapper;

    @Override
    public Result countByExample(BabyNamesExample example) {
        try{
            long count = this.babyNamesMapper.countByExample(example);
            return Result.newSuccess(count);
        }catch (Exception e){
            log.error("获取数据count出错");
            e.printStackTrace();
            return Result.newException(StateNumber.DAO_ERR,e);
        }
    }

    @Override
    public Result deleteByExample(BabyNamesExample example) {
        try{
            int state = this.babyNamesMapper.deleteByExample(example);
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
    public Result deleteByPrimaryKey(Integer id) {
        try{
            int state = this.babyNamesMapper.deleteByPrimaryKey(id);
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
    public Result insert(BabyNames record) {
        try{
            int state = this.babyNamesMapper.insert(record);
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
    public Result insertSelective(BabyNames record) {
        try{
            int state = this.babyNamesMapper.insertSelective(record);
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
    public Result<List<BabyNames>> selectByExample(BabyNamesExample example) {
        try{
            List<BabyNames> babyNamesList = this.babyNamesMapper.selectByExample(example);
            return Result.newSuccess(babyNamesList);
        }catch (Exception e){
            log.error("条件获取数据出错！");
            e.printStackTrace();
            return Result.newException(StateNumber.DAO_ERR,e);
        }
    }

    @Override
    public Result<BabyNames> selectByPrimaryKey(Integer id) {
        try{
            BabyNames babyName = this.babyNamesMapper.selectByPrimaryKey(id);
            return Result.newSuccess(babyName);
        }catch (Exception e){
            log.error("条件获取数据出错！");
            e.printStackTrace();
            return Result.newException(StateNumber.DAO_ERR,e);
        }
    }

    @Override
    public Result updateByExampleSelective(BabyNames record, BabyNamesExample example) {
        try{
            int state = this.babyNamesMapper.updateByExampleSelective(record,example);
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
    public Result updateByExample(BabyNames record, BabyNamesExample example) {
        try{
            int state = this.babyNamesMapper.updateByExample(record,example);
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
    public Result updateByPrimaryKeySelective(BabyNames record) {
        try{
            int state = this.babyNamesMapper.updateByPrimaryKeySelective(record);
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
    public Result updateByPrimaryKey(BabyNames record) {
        try{
            int state = this.babyNamesMapper.updateByPrimaryKey(record);
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
    public Result<BabyNames> selectOneByExample(BabyNamesExample example) {
        try{
            BabyNames babyName = this.babyNamesMapper.selectOneByExample(example);
            return Result.newSuccess(babyName);
        }catch (Exception e){
            log.error("条件获取数据出错！");
            e.printStackTrace();
            return Result.newException(StateNumber.DAO_ERR,e);
        }
    }
}
