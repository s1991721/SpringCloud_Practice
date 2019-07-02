package com.ljf.mall.service.impl;

import com.ljf.mall.dto.OrderDTO;
import com.ljf.mall.enums.OrderStatusEnum;
import com.ljf.mall.enums.PayStatusEnum;
import com.ljf.mall.po.OrderDetail;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mr.lin on 2019/6/6
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {

    @Autowired
    private OrderServiceImpl orderService;

    private final String OPENID = "110110";

    @Test
    public void create() {

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName("name");
        orderDTO.setBuyerAddress("address");
        orderDTO.setBuyerPhone("phone");
        orderDTO.setBuyerOpenid(OPENID);

        List<OrderDetail> orderDetailList = new ArrayList<>();

        OrderDetail o1 = new OrderDetail();
        o1.setProductId("1");
        o1.setProductQuantity(1);
        orderDetailList.add(o1);

        orderDTO.setOrderDetailList(orderDetailList);

        OrderDTO result = orderService.create(orderDTO);

        log.info("【创建订单】 result={}", result);
    }

    @Test
    public void findOne() {
        OrderDTO result = orderService.findOne("1559808391396321402");

        log.info("【查找订单】 result={}", result);
    }

    @Test
    public void findList() {
        Page<OrderDTO> orderDTOPage = orderService.findList(OPENID, PageRequest.of(0, 2));

        log.info("【查找订单列表】 result={}", orderDTOPage);
    }

    @Test
    public void cancel() {
        OrderDTO orderDTO = orderService.findOne("1559808391396321402");
        OrderDTO result = orderService.cancel(orderDTO);
        Assert.assertNotEquals(OrderStatusEnum.NEW.getCode(), result.getOrderStatus());
    }

    @Test
    public void finish() {
        OrderDTO orderDTO = orderService.findOne("1559808391396321402");
        OrderDTO result = orderService.finish(orderDTO);
        Assert.assertEquals(OrderStatusEnum.FINISHED.getCode(), result.getOrderStatus());
    }

    @Test
    public void paid() {
        OrderDTO orderDTO = orderService.findOne("1559808391396321402");
        OrderDTO result = orderService.paid(orderDTO);
        Assert.assertEquals(PayStatusEnum.SUCCESS.getCode(), result.getPayStatus());
    }
}