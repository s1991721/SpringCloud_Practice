package com.ljf.user.service;

import com.ljf.user.dataobject.UserInfo;

/**
 * Created by mr.lin on 2019/6/27
 */
public interface UserService {

    UserInfo findByOpenid(String openid);

}
