package com.ljf.mall.service;

import com.ljf.mall.po.SellerInfo;

/**
 * Created by mr.lin on 2019/6/13
 */
public interface SellerService {

    SellerInfo findSellerInfoByOpenid(String openid);

}
