package com.ljf.user.service.impl;

import com.ljf.user.dataobject.UserInfo;
import com.ljf.user.repository.UserInfoRepository;
import com.ljf.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by mr.lin on 2019/6/27
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    public UserInfo findByOpenid(String openid) {
        return userInfoRepository.findByOpenid(openid);
    }
}
