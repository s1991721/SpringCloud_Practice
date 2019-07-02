package com.ljf.user.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by mr.lin on 2019/6/27
 */
@Data
@Entity
public class UserInfo {

    @Id
    private String id;
    private String username;
    private String password;
    private String openid;
    private Integer role;


}
