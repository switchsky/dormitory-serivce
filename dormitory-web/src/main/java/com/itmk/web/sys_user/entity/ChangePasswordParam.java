package com.itmk.web.sys_user.entity;

import io.swagger.models.auth.In;
import lombok.Data;

@Data
public class ChangePasswordParam {

    Integer userId;
    String oldpassword;
    String newpassword;
}
