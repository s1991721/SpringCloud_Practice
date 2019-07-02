package com.ljf.order.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by mr.lin on 2019/6/20
 */
@Data
@Entity
@DynamicUpdate
@DynamicInsert
public class OrderMaster {

    @Id
    private String orderId;

    private String buyerName;

    private String buyerPhone;

    private String buyerAddress;

    private String buyerOpenid;

    private BigDecimal orderAmount;

    private Integer orderStatus ;

    private Integer payStatus ;

    private Date createTime;

    private Date updateTime;
}
