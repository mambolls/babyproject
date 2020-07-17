package com.baby.babyproject.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class JwtTokenUtil {

    private static final Logger logger = LoggerFactory.getLogger(JwtTokenUtil.class);
    //内部系统过期时间
    public static final long EXPIRATION_TIME = 3600L; // 1小时
    //外部系统过期时间
    public static final long OutSideEXPIRATION_TIME = 300_000; // 7天

    public static final String USER = "user";

    /**
     * 获取
     *
     * @param SECRET
     * @param user
     * @return
     */

    public static String generateToken(String SECRET, JwtUserInfo user) {

        HashMap<String, Object> map = new HashMap<>();

        String uuid = UUID.randomUUID().toString();
        user.setUuid(uuid);
        map.put(USER, JSON.toJSONString(user));

        String jwt = Jwts.builder()

                .setClaims(map)

                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))

                .signWith(SignatureAlgorithm.HS512, SECRET)

                .compact();

        return jwt;
    }

    /** 
     * @Name getPayLoad
     * @Description 校验token
     * @Param [jwtToken]
     * @Return com.baby.babyproject.util.JwtUserInfo
     * @Author lilinsong 
     * @Date 11:57 2020/7/16
     **/
    public static JwtUserInfo getPayLoad(String jwtToken) throws Exception {

        if (StringUtils.isAllEmpty(jwtToken))
            return null;
        String Md5payLoad = URLDecoder.decode(jwtToken, "UTF-8").split("\\.")[1];
       // String payLoad = new String(Base64.decodeBase64(Md5payLoad), "UTF-8");

       // String payLoad = new String( TextCodec.BASE64.decode(Md5payLoad), "UTF-8");
        String payLoad = new String( TextCodec.BASE64URL.decode(Md5payLoad), "UTF-8");
        JSONObject jsonToken = JSON.parseObject(payLoad);
        JSONObject obj = jsonToken.getJSONObject(USER);
        JwtUserInfo token = JSON.toJavaObject(obj, JwtUserInfo.class);
        return token;
    }

    static class TokenValidationException extends RuntimeException {

        /**
         *
         */
        private static final long serialVersionUID = 1L;

        public TokenValidationException(String msg) {

            super(msg);

        }

    }

    public static Object mapToObject(Map<String, Object> map, Class<?> beanClass) throws Exception {
        if (map == null)
            return null;

        Object obj = beanClass.newInstance();

        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            int mod = field.getModifiers();
            if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
                continue;
            }

            field.setAccessible(true);
            field.set(obj, map.get(field.getName()));
        }
        return obj;
    }

    public static void main(String[] args) {
        JwtUserInfo a = new JwtUserInfo();
        //JwtToken(userName=任伟1, uuid=null, userId=479c4b94bb7591a73d3aa59c34e74999, userType=official,
        // appId=1, isAdmin=false, orgCode= 12441624456972609Y, dataType=null, singlePoint=1, roleIds=null)
        a.setUserName("任伟1");
        a.setPatientId("5abbb863-1f95-458b-9091-46a2262a1c09");
        String token = JwtTokenUtil.generateToken(UUID.randomUUID().toString(), a);
        System.out.println(token);
//        JwtToken boo = JwtUtil.validateTokenAndGetClaims("22222", token);
//        System.out.println(JSON.toJSONString(boo));
//		String token2 = token;
       // String token2 = "eyJhbGciOiJIUzUxMiJ9.eyJleHAiOjE1ODk3Njc3MjksInVzZXIiOiJ7XCJhcHBJZFwiOlwiMVwiLFwiaXNBZG1pblwiOmZhbHNlLFwib3JnQ29kZVwiOlwiMTI0NDE2MDA0NTY5NTg5MlgzXCIsXCJzaW5nbGVQb2ludFwiOjEsXCJ1c2VySWRcIjpcImRjYTU4ZGZjYjE5YTljNzI1Y2RlNjMxOWRjYWJjZDZiXCIsXCJ1c2VyTmFtZVwiOlwi5rWL6K-VMVwiLFwidXNlclR5cGVcIjpcIm9mZmljaWFsXCIsXCJ1dWlkXCI6XCIwZDQ2ZjkzMy04OTMxLTQ0YmItYjM1Zi1iZDcyZmU3ZmVjNzBcIn0ifQ.Qkr6WtjxCXxwkKBswN2fu3xqPFWDB0ASCZDRap34Rc6PUx7puEge6RPVpvG8bn_9vr8qAGfTS58qgKU4ZPrCBw";
       String token2="eyJhbGciOiJIUzUxMiJ9.eyJleHAiOjE1ODk3NzMyNTYsInVzZXIiOiJ7XCJhcHBJZFwiOlwiMVwiLFwiaXNBZG1pblwiOmZhbHNlLFwib3JnQ29kZVwiOlwiMTI0NDE2MDA0NTY5NTg5MlgzXCIsXCJzaW5nbGVQb2ludFwiOjEsXCJ1c2VySWRcIjpcImRjYTU4ZGZjYjE5YTljNzI1Y2RlNjMxOWRjYWJjZDZiXCIsXCJ1c2VyTmFtZVwiOlwi5rWL6K-VMVwiLFwidXNlclR5cGVcIjpcIm9mZmljaWFsXCIsXCJ1dWlkXCI6XCI2YjU5MWNmMi02N2ZjLTRiMGUtOGFmYS1kOTMxN2Q4YWYyZjBcIn0ifQ.6CjooPmuvzHSaxoR-fph3QNhVbzGP_GueKoTJqYQmksohyP4E-raqCo7YTKFUn4qJPcj3O_kZEB8CQwDUoT-sQ";
       String token3="eyJhbGciOiJIUzUxMiJ9.eyJleHAiOjE1ODk3NzY2MTksInVzZXIiOiJ7XCJhcHBJZFwiOlwiMVwiLFwiaXNBZG1pblwiOmZhbHNlLFwib3JnQ29kZVwiOlwiIDEyNDQxNjI0NDU2OTcyNjA5WVwiLFwic2luZ2xlUG9pbnRcIjoxLFwidXNlcklkXCI6XCI0NzljNGI5NGJiNzU5MWE3M2QzYWE1OWMzNGU3NDk5OVwiLFwidXNlck5hbWVcIjpcIuS7u-S8nzFcIixcInVzZXJUeXBlXCI6XCJvZmZpY2lhbFwiLFwidXVpZFwiOlwiNjhkYzE3MzctM2M5MC00MmU0LThkN2ItNjY4MDU0NTQyMzMwXCJ9In0.hWZHqbjFeQkToEbeHzWQEF48tYdHZn6r7Gjk5h5QbcMfMV3D9gdpAGZPtiUIIdMbJVtpree2PRUgdDKrdCPTTg";
       String token4="eyJhbGciOiJIUzUxMiJ9.eyJleHAiOjE1ODk3NzY3NzYsInVzZXIiOiJ7XCJhcHBJZFwiOlwiMVwiLFwiaXNBZG1pblwiOmZhbHNlLFwib3JnQ29kZVwiOlwiIDEyNDQxNjI0NDU2OTcyNjA5WVwiLFwic2luZ2xlUG9pbnRcIjoxLFwidXNlcklkXCI6XCI0NzljNGI5NGJiNzU5MWE3M2QzYWE1OWMzNGU3NDk5OVwiLFwidXNlck5hbWVcIjpcIuS7u-S8nzFcIixcInVzZXJUeXBlXCI6XCJvZmZpY2lhbFwiLFwidXVpZFwiOlwiYzdlYzJkZTUtMGViNi00MTM5LTg1YzktZGM5MzBjZjE4ZjRjXCJ9In0.kATCFheoi8_wawGSqcxHc7Wv7CFkyMB88SQL0Qvpfy342971y20duV3N2Gxh8HBBMZZ7DQnMKZX8v6LBR8gmcw";
        JwtUserInfo token22;
        try {

            token22 = JwtTokenUtil.getPayLoad(token);
            System.out.println("用户id=="+token22.getPatientId());
            if (token22 == null) {
                System.out.println("签名校验失败");
            }
            System.out.println("token22:" + JSON.toJSONString(token22));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
