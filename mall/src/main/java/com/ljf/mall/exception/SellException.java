package com.ljf.mall.exception;

import com.ljf.mall.enums.ResultEnum;
import lombok.Getter;

/**
 * Created by mr.lin on 2019/6/6
 */
@Getter
public class SellException extends RuntimeException {

    private Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        code = resultEnum.getCode();
    }

    public SellException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
