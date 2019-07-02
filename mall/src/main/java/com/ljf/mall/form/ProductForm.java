package com.ljf.mall.form;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by mr.lin on 2019/6/11
 */
@Data
public class ProductForm {

    private String productId;

    private String productName;

    private BigDecimal productPrice;

    private Integer productStock;

    private String productDescription;

    private String productIcon;

    private Integer categoryType;

}
