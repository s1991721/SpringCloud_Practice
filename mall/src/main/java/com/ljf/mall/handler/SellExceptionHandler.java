package com.ljf.mall.handler;

import com.ljf.mall.config.ProjectUrlConfig;
import com.ljf.mall.exception.SellException;
import com.ljf.mall.exception.SellerAuthorizeException;
import com.ljf.mall.utils.ResultUtil;
import com.ljf.mall.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by mr.lin on 2019/6/13
 */
@ControllerAdvice
@Slf4j
public class SellExceptionHandler {

    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    @ExceptionHandler(value = SellerAuthorizeException.class)
    public ModelAndView handlerAuthorizeException() {
        return new ModelAndView("redirect:"
                .concat(projectUrlConfig.sell)
                .concat("/sell/seller/login"));
    }

    @ExceptionHandler(value = SellException.class)
    @ResponseBody
    public ResultVO handlerSellException(SellException exception) {
        return ResultUtil.error(exception.getCode(), exception.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResultVO handlerSellException(Exception exception) {
        log.error(exception.getMessage());
        return ResultUtil.error(500, exception.getMessage());
    }
}
