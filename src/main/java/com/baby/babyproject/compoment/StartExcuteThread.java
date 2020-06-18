//package com.baby.babyproject.compoment;
//
//import com.baby.babyproject.module.dao.server.IBabyProjectLogServiceDao;
//import com.baby.babyproject.thread.LogSaveExcuteThread;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
///**
// * @ClassName: StartExcuteThread
// * @Description: 开机启动执行
// * @author lilinsong
// * @date 2017-11-21 上午9:59:05
// * @version V2.1 * Update Logs: * Name: * Date: * Description: 初始化
// */
//@Slf4j
//@Component
//@Order(value = 2)
//public class StartExcuteThread implements CommandLineRunner {
//
//    @Autowired
//    private IBabyProjectLogServiceDao babyProjectLogServiceDaoImpl;
//
//    @Override
//    public void run(String... arg0) throws Exception {
//
//        try {
//            // 启动线程监视redis各种业务
//            LogSaveExcuteThread logSaveExcuteThread = new LogSaveExcuteThread(babyProjectLogServiceDaoImpl);
//            new Thread(logSaveExcuteThread).start();
//            log.info("<<<<<<<<<<<<<<<<<<Success: Listen thread started successfully!>>>>>>>>>>>>>>>>>>");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
//}
