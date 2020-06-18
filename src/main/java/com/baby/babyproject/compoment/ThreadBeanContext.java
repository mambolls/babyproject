//package com.baby.babyproject.compoment;
//
//import org.springframework.beans.BeansException;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.ApplicationContextAware;
//import org.springframework.stereotype.Component;
//
///**
// *
// * @ClassName: ThreadBeanContext
// * @Description: (解决线程注入service问题)
// * @author Wyh
// * @date 2018年3月5日 上午11:25:56
// * @version V2.1 * Update Logs: * Name: * Date: * Description: 初始化
// */
//@Component
//public class ThreadBeanContext implements ApplicationContextAware {
//
//    private static ApplicationContext applicationContext;
//
//    public void setApplicationContext(ApplicationContext applicationContext)
//            throws BeansException {
//        ThreadBeanContext.applicationContext = applicationContext;
//    }
//
//    public static ApplicationContext getApplicationContext() {
//        return applicationContext;
//    }
//
//    @SuppressWarnings("unchecked")
//    public static <T> T getBean(String name) throws BeansException {
//        return (T) applicationContext.getBean(name);
//    }
//
//    public static <T> T getBean(Class<T> clz) throws BeansException {
//        return (T) applicationContext.getBean(clz);
//    }
//}