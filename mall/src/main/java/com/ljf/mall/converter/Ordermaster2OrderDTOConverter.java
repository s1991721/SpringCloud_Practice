package com.ljf.mall.converter;

import com.ljf.mall.dto.OrderDTO;
import com.ljf.mall.po.OrderMaster;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by mr.lin on 2019/6/6
 */
public class Ordermaster2OrderDTOConverter {

    public static OrderDTO convert(OrderMaster orderMaster) {
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        return orderDTO;
    }

    public static List<OrderDTO> convert(List<OrderMaster> orderMasterList) {
        return orderMasterList
                .stream()
                .map(Ordermaster2OrderDTOConverter::convert)
                .collect(Collectors.toList());
    }

}
