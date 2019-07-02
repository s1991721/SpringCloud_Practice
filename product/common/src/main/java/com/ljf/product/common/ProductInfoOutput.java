package com.ljf.product.common;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by mr.lin on 2019/6/20
 */
@Data
public class ProductInfoOutput {

    private String productId;

    private String productName;

    private BigDecimal productPrice;

    private Integer productStock;

    private String productDescription;

    private String productIcon;

    private Integer productStatus;

    private Integer categoryType;

}
