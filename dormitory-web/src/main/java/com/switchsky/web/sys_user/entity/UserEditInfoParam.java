package com.switchsky.web.sys_user.entity;

import lombok.Data;

@Data
public class UserEditInfoParam {
        //用户id
        private Integer userId;
        //用户类型
        private Integer userType;
        private String img;
        private String username;
        private String nickName;
        private String phone;
        private String sex;
        private int age;
        private String email;
        private String hobby;
        private String signature;
}
