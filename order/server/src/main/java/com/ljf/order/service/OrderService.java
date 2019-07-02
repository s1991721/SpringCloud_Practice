package com.ljf.order.service;

import com.ljf.order.dto.OrderDTO;

/**
 * Created by mr.lin on 2019/6/20
 */
public interface OrderService {

    OrderDTO create(OrderDTO orderDTO);

    OrderDTO finish(String orderId);

}
