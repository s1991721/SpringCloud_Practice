package com.ljf.order.exception;

import com.ljf.order.enums.ResultEnum;

/**
 * Created by mr.lin on 2019/6/20
 */
public class OrderException extends RuntimeException {

    private Integer code;

    public OrderException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public OrderException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }
}
