package com.ljf.order.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ljf.order.dataobject.OrderDetail;
import com.ljf.order.dto.OrderDTO;
import com.ljf.order.enums.ResultEnum;
import com.ljf.order.exception.OrderException;
import com.ljf.order.form.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * Created by mr.lin on 2019/6/20
 */
@Slf4j
public class OrderForm2OrderDTOConverter {
    public static OrderDTO convert(OrderForm orderForm) {

        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());

        Gson gson = new Gson();
        List<OrderDetail> orderDetailList;
        try {
            orderDetailList = gson.fromJson(orderForm.getItems(), new TypeToken<List<OrderDetail>>() {
            }.getType());
        } catch (Exception e) {
            log.error("【json转换】错误，string={}", orderForm.getItems());
            throw new OrderException(ResultEnum.PARAM_ERROR);
        }
        orderDTO.setOrderDetailList(orderDetailList);

        return orderDTO;
    }
}
