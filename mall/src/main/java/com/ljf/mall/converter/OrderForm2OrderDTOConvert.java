package com.ljf.mall.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ljf.mall.dto.OrderDTO;
import com.ljf.mall.enums.ResultEnum;
import com.ljf.mall.exception.SellException;
import com.ljf.mall.form.OrderForm;
import com.ljf.mall.po.OrderDetail;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * Created by mr.lin on 2019/6/6
 */
@Slf4j
public class OrderForm2OrderDTOConvert {

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
            log.error("【对象转换】错误，string={}", orderForm.getItems());
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        orderDTO.setOrderDetailList(orderDetailList);

        return orderDTO;
    }

}
