package com.ljf.mall.enums;

import lombok.Getter;

/**
 * Created by mr.lin on 2019/6/5
 */
@Getter
public enum ProductStatusEnum implements CodeEnum{

    UP(0, "在架"),
    DOWN(1, "下架");

    private Integer code;
    private String message;

    ProductStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
