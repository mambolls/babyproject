package com.baby.babyproject.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.impl.TextCodec;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


@Slf4j
public class RequestUtil {

    /**
     * 追踪id
     */
    public final static String TRACE_ID = "trace_id";
    /**
     * 请求ip
     */
    public final static String REQUEST_IP = "request_ip";
    /**
     * 用户id
     */
    public final static String USER_ID = "user_id";
    /**
     * 用户名
     */
    public final static String USER_NAME = "user_name";
    /**
     * token
     */
    public final static String TOKEN_NAME = "token";

    /**
     * 应用名称
     */
    public final static String APP_NAME = "app_name";
    /**
     * 系统类型
     */
    public final static String APP_ID = "app_id";

    private static final String USER = "user";

    public final static String PRODUCT_KEY = "product_key";


    public final static String APP_KEY = "app_key";


    /**
     * 从请求头部中获取某个key的值
     */
    public static String getHeaderInfo(String key) {
        HttpServletRequest request = getRequest();
        String headerInfo = request.getHeader(key);
        return headerInfo;
    }


    public  static HttpServletRequest getRequest (){
       return  ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    /**
     * 从请求头部中获取user_key
     */
    public static String getUserKey() {
        HttpServletRequest request =  getRequest();
        String userKey = request.getHeader(PRODUCT_KEY);
        return userKey;
    }


    /**
     * 从请求头部中获取追踪id
     */
    public static String getTraceId() {
        HttpServletRequest request =  getRequest();
        String traceId = request.getHeader(TRACE_ID);
//        log.info("==dw33qq==获取msgId: {}", traceId);
        return traceId;
    }
    /**
     * 从请求头部中获取追踪id
     */
    public static String getAppId() {
        HttpServletRequest request =  getRequest();
        String traceId = request.getHeader(APP_ID);
//        log.info("==dw33qq==获取msgId: {}", traceId);
        return traceId;
    }
    /**
     * 生成追踪id
     * @return
     */
//    public static String generateTraceId(){
//        String traceId = StrUtil.genarateUUID();
//       // log.info("====生成追踪id==>traceId：{}",traceId);
//        return traceId;
//    }


    /**
     * 获取appName
     * @return
     */
    public static String getAppName() {
        HttpServletRequest request =  getRequest();
        String appName = request.getHeader(APP_NAME);
        if (StringUtils.isEmpty(appName)) {
            appName = getIpAddr();
        }
        return appName;
    }

    /**
     * 获取当前请求jwtToken(从请求头部中获取)
     *
     * @author ranxiang
     */
    public static String getjwtToken() {
        HttpServletRequest request =  getRequest();
        String token= request.getHeader(TOKEN_NAME);
        return token!=null?token:request.getParameter(TOKEN_NAME);
    }


    /**
     * 获取登录用户IP地址
     *
     * @return
     */
    public static String getIpAddr() {
        HttpServletRequest request =  getRequest();

        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip.equals("0:0:0:0:0:0:0:1")) {
            ip = "127.0.0.1";
        }
        return ip;
    }

    /**
     * 根据头部的token 转换成JwtToken 对象
     * @param charsetName   编码名称 可为空
     * @return  JwtToken 对象
     * @throws Exception
     */

    /**
     * 返回请求参数为映射
     */
    public static Map<String, String> stringMap(HttpServletRequest request) {
        Map<String, String> paramsMap = new HashMap<>();
        // get,post提交的参数都可以取到
        Map<String, String[]> paramMap = request.getParameterMap();
        for (String key : paramMap.keySet()) {
            String value = paramMap.get(key)[0];
            if (ObjectHelper.isNotEmpty(value)) {
                paramsMap.put(key, value);
            }
        }
        return paramsMap;
    }

    /** 
     * @Name getClientIpAddress
     * @Description 获取请求ip
     * @Param [request]
     * @Return java.lang.String
     * @Author lilinsong 
     * @Date 15:37 2020/6/11
     **/
    public static final String getClientIpAddress(HttpServletRequest request) {
        // 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址
        final int a = 15;
        String ip = request.getHeader("X-Forwarded-For");
        if (log.isInfoEnabled()) {
            log.info("getIpAddress(HttpServletRequest) - X-Forwarded-For - String ip="
                    + ip);
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            if (ip == null || ip.length() == 0
                    || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
                if (log.isInfoEnabled()) {
                    log.info("getIpAddress(HttpServletRequest) - Proxy-Client-IP - String ip="
                            + ip);
                }
            }
            if (ip == null || ip.length() == 0
                    || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
                if (log.isInfoEnabled()) {
                    log.info("getIpAddress(HttpServletRequest) - WL-Proxy-Client-IP - String ip="
                            + ip);
                }
            }
            if (ip == null || ip.length() == 0
                    || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
                if (log.isInfoEnabled()) {
                    log.info("getIpAddress(HttpServletRequest) - HTTP_CLIENT_IP - String ip="
                            + ip);
                }
            }
            if (ip == null || ip.length() == 0
                    || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
                if (log.isInfoEnabled()) {
                    log.info("getIpAddress(HttpServletRequest) - HTTP_X_FORWARDED_FOR - String ip="
                            + ip);
                }
            }
            if (ip == null || ip.length() == 0
                    || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
                if (log.isInfoEnabled()) {
                    log.info("getIpAddress(HttpServletRequest) - getRemoteAddr - String ip="
                            + ip);
                }
            }
        } else if (ip.length() > a) {
            String[] ips = ip.split(",");
            for (int index = 0; index < ips.length; index++) {
                String strIp = (String) ips[index];
                if (!("unknown".equalsIgnoreCase(strIp))) {
                    ip = strIp;
                    break;
                }
            }
        }
        return ip;
    }

}
