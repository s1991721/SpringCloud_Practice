package com.ljf.mall.repository;

import com.ljf.mall.po.OrderMaster;
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
public class OrderMasterRepositoryTest {

    @Autowired
    OrderMasterRepository repository;

    @Test
    public void insert() {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("1");
        orderMaster.setBuyerName("1");
        orderMaster.setBuyerPhone("1");
        orderMaster.setBuyerAddress("1");
        orderMaster.setBuyerOpenid("1");
        orderMaster.setOrderAmount(new BigDecimal(1));

        Assert.assertNotNull(repository.save(orderMaster));
    }

    @Test
    public void select() {
        Assert.assertNotNull(repository.findById("1").get());
    }

    @Test
    public void update() {
        OrderMaster orderMaster = repository.findById("1").get();
        orderMaster.setBuyerName("11");
        orderMaster.setBuyerPhone("11");
        orderMaster.setBuyerAddress("11");
        orderMaster.setBuyerOpenid("11");
        orderMaster.setOrderAmount(new BigDecimal(11));
        Assert.assertNotNull(repository.save(orderMaster));
    }

    @Test
    public void delete() {
        OrderMaster orderMaster = repository.findById("1").get();
        repository.delete(orderMaster);
    }

    @Test
    public void findByBuyerOpenid() {
    }
}