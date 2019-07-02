package com.ljf.mall.dto;

import lombok.Data;

/**
 * Created by mr.lin on 2019/6/6
 */
@Data
public class CartDTO {

    private String productId;

    private Integer productQuantity;


    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
