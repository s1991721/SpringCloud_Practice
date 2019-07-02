package com.ljf.mall.po;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ljf.mall.enums.ProductStatusEnum;
import com.ljf.mall.utils.EnumUtil;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by mr.lin on 2019/6/5
 */
@Entity
@Data
@DynamicUpdate
@DynamicInsert
public class ProductInfo implements Serializable {

    private static final long serialVersionUID = -5938490712826049812L;

    @Id
    private String productId;

    private String productName;

    private BigDecimal productPrice;

    private Integer productStock;

    private String productDescription;

    private String productIcon;

    private Integer productStatus = ProductStatusEnum.UP.getCode();

    private Integer categoryType;

    private Date createTime;

    private Date updateTime;

    @JsonIgnore
    public ProductStatusEnum getProductStatusEnum() {
        return EnumUtil.getByCode(productStatus, ProductStatusEnum.class);
    }

}
