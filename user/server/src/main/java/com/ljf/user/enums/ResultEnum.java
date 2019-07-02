package com.ljf.user.enums;

import lombok.Getter;

/**
 * Created by mr.lin on 2019/6/20
 */
@Getter
public enum ResultEnum {

    LOGIN_FAIL(1, "登录失败"),
    ROLE_ERROR(2, "角色权限有误"),
    ;

    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
