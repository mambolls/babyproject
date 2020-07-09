package com.baby.babyproject.aop;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baby.babyproject.compoment.KafkaSender;
import com.baby.babyproject.constants.Constants;
import com.baby.babyproject.module.dao.entity.BabyProjectLog;
import com.baby.babyproject.util.ObjectHelper;
import com.baby.babyproject.util.RequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Aspect
@Component
public class ApiLogAop {

    @Autowired
    private KafkaSender kafkaSender;

    @Pointcut("execution(public * com.baby.babyproject.module.controller.*.*(..))")
    public void webApiLog() {

    }

    @Around("webApiLog()")
    public Object proceeding(ProceedingJoinPoint point) throws Throwable {
        Object[] args = point.getArgs();
        Object result = null;
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 获取请求参数
        String queryString = request.getQueryString();
        String params = null;
        Map<String, String> map = RequestUtil.stringMap(request);
        Object[] arguments  = new Object[args.length];
        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof ServletRequest
                    || args[i] instanceof ServletResponse
                    || args[i] instanceof MultipartFile) {
                continue;
            }
            arguments[i] = args[i];
        }
        if (ObjectHelper.isNotEmpty(arguments[0])) {
            if ("POST".equals(request.getMethod())) {
                Object object = arguments[0];
                if(!(object instanceof String)) {
                    params = JSON.toJSONString(object);
                }else {
                    params = (String)object;
                }
            } else if ("GET".equals(request.getMethod())) {
                params = queryString;
             }
        }else{
            params = JSON.toJSONString(map);
        }
        // 获得请求ip
        String ip = RequestUtil.getClientIpAddress(request);
        // 获得请求返回参数
        result = point.proceed(args);
        String requestUrl = request.getRequestURL().toString();
        String requestURI = request.getRequestURI();
        // 记录下请求内容
        BabyProjectLog babyProjectLog = new BabyProjectLog();
        babyProjectLog.setId(UUID.randomUUID().toString());
        babyProjectLog.setRequestUrl(requestURI);
        babyProjectLog.setRequestDate(new Date());
        babyProjectLog.setIp(ip);
        babyProjectLog.setRequest(params);
        babyProjectLog.setResponse(JSONObject.toJSONString(result));
        this.kafkaSender.send(Constants.LOG_TOPIC,JSON.toJSONString(babyProjectLog));
        log.info("请求IP : " + ip);
        log.info("请求地址 : " + requestUrl);
        log.info("请求method : " + request.getMethod());
        log.info("请求参数: " + params);
        log.info("响应参数:" + JSONObject.toJSONString(result));
        return result;
    }
}
