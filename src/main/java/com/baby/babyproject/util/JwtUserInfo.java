package com.baby.babyproject.util;

import lombok.Data;

@Data
public class JwtUserInfo {

    private static final long serialVersionUID = 1L;

    private String userName;
    private String uuid;
    private String patientId;
    /** 用户手机号 */
    private String phoneNumber;

}
