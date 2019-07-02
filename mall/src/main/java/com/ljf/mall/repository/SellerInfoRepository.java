package com.ljf.mall.repository;

import com.ljf.mall.po.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by mr.lin on 2019/6/13
 */
public interface SellerInfoRepository extends JpaRepository<SellerInfo, String> {

    SellerInfo findByOpenid(String openid);

}
