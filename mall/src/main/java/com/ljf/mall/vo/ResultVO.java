package com.ljf.mall.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by mr.lin on 2019/6/5
 */
@Data
public class ResultVO<T> implements Serializable {

    private static final long serialVersionUID = -8481658366982316829L;

    private Integer code;

    private String msg;

    private T data;

}
