package com.ljf.user.controller;

import com.ljf.user.constant.CookieConstant;
import com.ljf.user.constant.RedisConstant;
import com.ljf.user.dataobject.UserInfo;
import com.ljf.user.enums.ResultEnum;
import com.ljf.user.enums.RoleEnum;
import com.ljf.user.service.UserService;
import com.ljf.user.utils.CookieUtil;
import com.ljf.user.utils.ResultUtil;
import com.ljf.user.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created by mr.lin on 2019/6/27
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("/buyer")
    public ResultVO buyer(@RequestParam("openid") String openid,
                          HttpServletResponse response) {

        UserInfo userInfo = userService.findByOpenid(openid);
        if (userInfo == null) {
            return ResultUtil.error(ResultEnum.LOGIN_FAIL);
        }

        if (RoleEnum.BUYER.getCode() != userInfo.getRole()) {
            return ResultUtil.error(ResultEnum.ROLE_ERROR);
        }

        CookieUtil.set(response, CookieConstant.OPENID, openid, CookieConstant.expire);

        return ResultUtil.success();
    }

    @GetMapping("/seller")
    public ResultVO seller(@RequestParam("openid") String openid,
                           HttpServletRequest request,
                           HttpServletResponse response) {
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);

        if (cookie != null &&
                !StringUtils.isEmpty(stringRedisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_TEMPLATE, cookie.getValue())))) {
            return ResultUtil.success();
        }

        UserInfo userInfo = userService.findByOpenid(openid);
        if (userInfo == null) {
            return ResultUtil.error(ResultEnum.LOGIN_FAIL);
        }

        if (RoleEnum.SELLER.getCode() != userInfo.getRole()) {
            return ResultUtil.error(ResultEnum.ROLE_ERROR);
        }

        String token = UUID.randomUUID().toString();
        Integer expire = CookieConstant.expire;
        stringRedisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_TEMPLATE, token),
                openid,
                expire,
                TimeUnit.SECONDS);

        CookieUtil.set(response, CookieConstant.TOKEN, token, CookieConstant.expire);

        return ResultUtil.success();
    }
}
