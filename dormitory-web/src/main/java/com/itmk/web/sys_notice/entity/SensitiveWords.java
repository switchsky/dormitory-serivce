package com.itmk.web.sys_notice.entity;

import lombok.Data;

import java.util.List;

@Data
public class SensitiveWords {
    List<String> title;
    List<String> passage;
}
