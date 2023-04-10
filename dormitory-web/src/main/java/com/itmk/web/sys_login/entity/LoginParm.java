package com.itmk.web.sys_login.entity;

import lombok.Data;

/**
 * @Author thf
 * @Version 3501754007
 */
@Data
public class LoginParm {
    private String username;
    private String password;
    private String userType;
    private String token;
    private String captcha;
}