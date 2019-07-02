package com.ljf.product.exception;

import com.ljf.product.enums.ResultEnum;

/**
 * Created by mr.lin on 2019/6/20
 */
public class ProductException extends RuntimeException {

    private Integer code;

    public ProductException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public ProductException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }
}
