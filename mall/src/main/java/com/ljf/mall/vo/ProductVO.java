package com.ljf.mall.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by mr.lin on 2019/6/5
 */
@Data
public class ProductVO implements Serializable {

    private static final long serialVersionUID = -6318599550648864512L;

    @JsonProperty("name")
    private String categoryName;

    @JsonProperty("type")
    private Integer categoryType;

    @JsonProperty("foods")
    private List<ProductInfoVO> productInfoVOList;

}
