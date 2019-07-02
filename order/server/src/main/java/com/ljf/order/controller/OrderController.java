package com.ljf.order.controller;

import com.ljf.order.converter.OrderForm2OrderDTOConverter;
import com.ljf.order.dto.OrderDTO;
import com.ljf.order.enums.ResultEnum;
import com.ljf.order.exception.OrderException;
import com.ljf.order.form.OrderForm;
import com.ljf.order.service.OrderService;
import com.ljf.order.utils.ResultUtil;
import com.ljf.order.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mr.lin on 2019/6/20
 */
@RestController
@Slf4j
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public ResultVO create(@Valid OrderForm orderForm,
                           BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            log.error("【创建订单】 参数不正确，orderForm={}", orderForm);
            throw new OrderException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        OrderDTO orderDTO = OrderForm2OrderDTOConverter.convert(orderForm);

        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("【创建订单】购物车信息为空");
            throw new OrderException(ResultEnum.CART_EMPTY);
        }

        OrderDTO result = orderService.create(orderDTO);

        Map<String, String> map = new HashMap<>();

        map.put("orderId", result.getOrderId());

        return ResultUtil.success(map);
    }

    @PostMapping("/finish")
    public ResultVO finish(@RequestParam("orderId") String orderId) {
        return ResultUtil.success(orderService.finish(orderId));
    }

}
