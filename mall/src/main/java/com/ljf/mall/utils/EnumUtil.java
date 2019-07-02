package com.ljf.mall.utils;

import com.ljf.mall.enums.CodeEnum;

/**
 * Created by mr.lin on 2019/6/10
 */
public class EnumUtil {

    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass) {
        for (T each : enumClass.getEnumConstants()) {
            if (code.equals(each.getCode())) {
                return each;
            }
        }
        return null;
    }
}
