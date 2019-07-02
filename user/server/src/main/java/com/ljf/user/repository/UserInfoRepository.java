package com.ljf.user.repository;

import com.ljf.user.dataobject.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by mr.lin on 2019/6/27
 */
public interface UserInfoRepository extends JpaRepository<UserInfo, String> {

    UserInfo findByOpenid(String openid);

}
