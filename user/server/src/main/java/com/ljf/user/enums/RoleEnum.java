package com.ljf.user.enums;

import lombok.Getter;

/**
 * Created by mr.lin on 2019/6/27
 */
@Getter
public enum RoleEnum {

    BUYER(1, "买家"),
    SELLER(2, "卖家"),
    ;

    private Integer code;
    private String message;

    RoleEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
