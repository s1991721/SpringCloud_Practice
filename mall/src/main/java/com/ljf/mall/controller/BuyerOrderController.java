package com.ljf.mall.controller;

import com.ljf.mall.converter.OrderForm2OrderDTOConvert;
import com.ljf.mall.dto.OrderDTO;
import com.ljf.mall.enums.ResultEnum;
import com.ljf.mall.exception.SellException;
import com.ljf.mall.form.OrderForm;
import com.ljf.mall.service.BuyerService;
import com.ljf.mall.service.OrderService;
import com.ljf.mall.utils.ResultUtil;
import com.ljf.mall.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mr.lin on 2019/6/6
 */
@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private BuyerService buyerService;

    @PostMapping("/create")
    public ResultVO<Map<String, String>> create(@Valid OrderForm orderForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("【创建订单】参数不正确，orderForm={}", orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }

        OrderDTO orderDTO = OrderForm2OrderDTOConvert.convert(orderForm);

        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("【创建订单】购物车不能为空");
            throw new SellException(ResultEnum.CART_EMPTY);
        }

        OrderDTO createResult = orderService.create(orderDTO);

        Map<String, String> map = new HashMap<>();
        map.put("orderId", createResult.getOrderId());

        return ResultUtil.success(map);

    }

    @GetMapping("/list")
    public ResultVO<List<OrderDTO>> list(@RequestParam("openid") String openid,
                                         @RequestParam(value = "page", defaultValue = "0") Integer page,
                                         @RequestParam(value = "size", defaultValue = "10") Integer size) {

        if (StringUtils.isEmpty(openid)) {
            log.error("【查询订单列表】openid为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }

        Page<OrderDTO> orderDTOPage = orderService.findList(openid, PageRequest.of(page, size));

        List<OrderDTO> orderDTOList = orderDTOPage.getContent();

        return ResultUtil.success(orderDTOList);

    }

    @GetMapping("/detail")
    public ResultVO detail(@RequestParam("openid") String openid,
                           @RequestParam("orderId") String orderId) {

        OrderDTO orderDTO = buyerService.findOrderOne(openid, orderId);

        return ResultUtil.success(orderDTO);
    }

    @PostMapping("/cancel")
    public ResultVO cancel(@RequestParam("openid") String openid,
                           @RequestParam("orderId") String orderId) {

        buyerService.cancelOrder(openid, orderId);

        return ResultUtil.success();
    }

}

