package com.ljf.mall.utils;

import java.util.Random;

/**
 * Created by mr.lin on 2019/6/6
 */
public class KeyUtil {

    public static synchronized String genUniqueKey() {
        Random random = new Random();

        Integer number = random.nextInt(900000) + 100000;

        return System.currentTimeMillis() + String.valueOf(number);
    }

}
