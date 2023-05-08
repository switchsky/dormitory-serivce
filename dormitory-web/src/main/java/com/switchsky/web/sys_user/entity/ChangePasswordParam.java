package com.switchsky.web.sys_user.entity;

import lombok.Data;

@Data
public class ChangePasswordParam {

    Integer userId;
    String oldpassword;
    String newpassword;
}
