//
//package com.baby.babyproject.thread;
//
//import com.alibaba.fastjson.JSONObject;
//import com.baby.babyproject.compoment.JedisUtil;
//import com.baby.babyproject.compoment.ThreadBeanContext;
//import com.baby.babyproject.constants.Constants;
//import com.baby.babyproject.module.dao.entity.BabyProjectLog;
//import com.baby.babyproject.module.dao.server.IBabyProjectLogServiceDao;
//import com.baby.babyproject.util.ObjectHelper;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.io.IOException;
//import java.util.Optional;
//
//
///**
// * @ClassName: LogSaveExcuteThread
// * @Description: 查询日志异步持久化处理线程
// * @author lilinsong
// * @date 2019-6-24 下午6:17:10
// * @version V2.1 * Update Logs: * Name: * Date: * Description: 初始化
// */
//@Slf4j
//public class LogSaveExcuteThread implements Runnable {
//    private static final String LOG_KEY = "log_key:log";
//
//    /** 健康档案浏览日志service */
//    private IBabyProjectLogServiceDao babyProjectLogServiceDaoImpl= ThreadBeanContext
//            .getApplicationContext().getBean(IBabyProjectLogServiceDao.class);
//
//    public LogSaveExcuteThread() {
//        super();
//    }
//
//    public LogSaveExcuteThread(IBabyProjectLogServiceDao babyProjectLogServiceDaoImpl) {
//        super();
//        this.babyProjectLogServiceDaoImpl = babyProjectLogServiceDaoImpl;
//    }
//
//    @Override
//    @Transactional(rollbackFor = IOException.class)
//    public void run() {
//        while (true) {
//            try {
//                listen();
//                if(ObjectHelper.isEmpty(recordStr)) {
//                    continue;
//                }
//                //转换为实体
//                BabyProjectLog record = JSONObject.parseObject(recordStr, BabyProjectLog.class);
//                // 持久化数据
//                this.babyProjectLogServiceDaoImpl.insertSelective(record);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//}
