package com.ljf.mall.repository;

import com.ljf.mall.po.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

/**
 * Created by mr.lin on 2019/6/6
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {

    @Autowired
    OrderDetailRepository repository;

    @Test
    public void insert() {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("1");
        orderDetail.setOrderId("1");
        orderDetail.setProductId("1");
        orderDetail.setProductName("1");
        orderDetail.setProductPrice(new BigDecimal(1));
        orderDetail.setProductQuantity(1);
        orderDetail.setProductIcon("1");

        Assert.assertNotNull(repository.save(orderDetail));
    }

    @Test
    public void select() {
        Assert.assertNotNull(repository.findById("1"));
    }

    @Test
    public void update() {
        OrderDetail orderDetail = repository.findById("1").get();

        orderDetail.setOrderId("11");
        orderDetail.setProductId("11");
        orderDetail.setProductName("11");
        orderDetail.setProductPrice(new BigDecimal(11));
        orderDetail.setProductQuantity(11);
        orderDetail.setProductIcon("11");

        Assert.assertNotNull(repository.save(orderDetail));
    }

    @Test
    public void delete() {
        OrderDetail orderDetail = repository.findById("1").get();
        repository.delete(orderDetail);
    }

    @Test
    public void findByOrderId() {
    }
}