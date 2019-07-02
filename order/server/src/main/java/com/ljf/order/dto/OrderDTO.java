package com.ljf.order.dto;

import com.ljf.order.dataobject.OrderDetail;
import com.ljf.order.enums.OrderStatusEnum;
import com.ljf.order.enums.PayStatusEnum;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by mr.lin on 2019/6/20
 */
@Data
public class OrderDTO {

    private String orderId;

    private String buyerName;

    private String buyerPhone;

    private String buyerAddress;

    private String buyerOpenid;

    private BigDecimal orderAmount;

    private Integer orderStatus = OrderStatusEnum.NEW.getCode();

    private Integer payStatus = PayStatusEnum.WAIT.getCode();

    private List<OrderDetail> orderDetailList;

}
