package com.ljf.mall.controller;

import com.ljf.mall.config.ProjectUrlConfig;
import com.ljf.mall.constant.CookieConstant;
import com.ljf.mall.constant.RedisConstant;
import com.ljf.mall.enums.ResultEnum;
import com.ljf.mall.po.SellerInfo;
import com.ljf.mall.service.SellerService;
import com.ljf.mall.utils.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created by mr.lin on 2019/6/13
 */
@Controller
@RequestMapping("/seller")
public class SellerUserController {

    @Autowired
    private SellerService sellerService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private ProjectUrlConfig projectUrlConfig;


    @GetMapping("/login")
    public ModelAndView login(Map<String, Object> map) {
        return new ModelAndView("login", map);
    }

    @PostMapping("/login")
    public ModelAndView login(@RequestParam("openid") String openid,
                              HttpServletResponse response,
                              Map<String, Object> map) {

        SellerInfo sellerInfo = sellerService.findSellerInfoByOpenid(openid);

        if (sellerInfo == null) {
            map.put("msg", ResultEnum.LOGIN_FAIL);
            map.put("url", "/sell/seller/order/list");
            return new ModelAndView("common/error", map);
        }

        String token = UUID.randomUUID().toString();

        stringRedisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_PREFIX, token), openid, RedisConstant.EXPIRE, TimeUnit.SECONDS);

        CookieUtil.set(response, CookieConstant.TOKEN, token, RedisConstant.EXPIRE);

        return new ModelAndView("redirect:" + projectUrlConfig.sell + "/sell/seller/order/list");

    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest request,
                               HttpServletResponse response,
                               Map<String, Object> map) {
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);

        if (cookie != null) {
            stringRedisTemplate.opsForValue().getOperations().delete(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));

            CookieUtil.set(response, CookieConstant.TOKEN, null, 0);
        }

        map.put("msg", ResultEnum.LOGOUT_SUCCESS.getMessage());
        map.put("url", "/sell/seller/order/list");

        return new ModelAndView("common/success", map);

    }

}
