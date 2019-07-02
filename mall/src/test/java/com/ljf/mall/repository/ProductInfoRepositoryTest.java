package com.ljf.mall.repository;

import com.ljf.mall.po.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

/**
 * Created by mr.lin on 2019/6/5
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRepositoryTest {

    @Autowired
    private ProductInfoRepository repository;

    @Test
    public void insert() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("2");
        productInfo.setProductName("商品2");
        productInfo.setProductPrice(new BigDecimal(2));
        productInfo.setProductStock(2);
        productInfo.setCategoryType(2);
        Assert.assertNotNull(repository.save(productInfo));
    }

    @Test
    public void select() {
        Assert.assertNotNull(repository.findById("1"));
    }

    @Test
    public void update() {
        ProductInfo productInfo=repository.findById("1").get();
        productInfo.setProductName("商品11");
        productInfo.setProductPrice(new BigDecimal(11));
        productInfo.setProductStock(11);
        productInfo.setCategoryType(11);
        Assert.assertNotNull(repository.save(productInfo));
    }

    @Test
    public void delete() {
        ProductInfo productInfo=repository.findById("1").get();
        repository.delete(productInfo);
    }
}