package com.ljf.mall.service.impl;

import com.ljf.mall.po.SellerInfo;
import com.ljf.mall.repository.SellerInfoRepository;
import com.ljf.mall.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by mr.lin on 2019/6/13
 */
@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerInfoRepository sellerInfoRepository;

    @Override
    public SellerInfo findSellerInfoByOpenid(String openid) {
        return sellerInfoRepository.findByOpenid(openid);
    }
}
