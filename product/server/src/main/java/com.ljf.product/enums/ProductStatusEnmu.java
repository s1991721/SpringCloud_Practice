package com.ljf.product.enums;

import lombok.Getter;

/**
 * Created by mr.lin on 2019/6/19
 */
@Getter
public enum ProductStatusEnmu {

    UP(0, "在架"),
    DOWN(1, "下架");

    private Integer code;
    private String message;

    ProductStatusEnmu(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
