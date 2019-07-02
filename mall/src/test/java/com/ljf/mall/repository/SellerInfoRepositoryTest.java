package com.ljf.mall.repository;

import com.ljf.mall.po.SellerInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by mr.lin on 2019/6/13
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerInfoRepositoryTest {

    @Autowired
    SellerInfoRepository sellerInfoRepository;

    @Test
    public void insert() {
        SellerInfo sellerInfo = new SellerInfo();
        sellerInfo.setOpenid("abc");
        sellerInfo.setSellerId("abc");
        sellerInfo.setPassword("abc");
        sellerInfo.setUsername("abc");

        sellerInfoRepository.save(sellerInfo);
    }

    @Test
    public void findByOpenid() {

        sellerInfoRepository.findByOpenid("abc");

    }
}