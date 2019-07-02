package com.ljf.mall.po;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by mr.lin on 2019/6/13
 */
@Data
@Entity
public class SellerInfo {

    @Id
    private String sellerId;

    private String username;

    private String password;

    private String openid;

}
