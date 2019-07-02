package com.ljf.product.vo;

import lombok.Data;

/**
 * Created by mr.lin on 2019/6/19
 */
@Data
public class ResultVO<T> {

    private Integer code;

    private String msg;

    private T data;
}
