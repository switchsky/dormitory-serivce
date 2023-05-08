package com.switchsky.web.sys_login.entity;

import lombok.Data;

@Data
public class CaptchaResult {
    String img;
    String token;
}
