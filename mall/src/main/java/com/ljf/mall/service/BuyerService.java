package com.ljf.mall.service;

import com.ljf.mall.dto.OrderDTO;

/**
 * Created by mr.lin on 2019/6/6
 */
public interface BuyerService {

    OrderDTO findOrderOne(String openid, String orderId);

    OrderDTO cancelOrder(String openid, String orderId);

}
