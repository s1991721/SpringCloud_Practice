package com.ljf.mall.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by mr.lin on 2019/6/13
 */
@ConfigurationProperties(prefix = "projecturl")
@Component
@Data
public class ProjectUrlConfig {

    public String sell;

}
