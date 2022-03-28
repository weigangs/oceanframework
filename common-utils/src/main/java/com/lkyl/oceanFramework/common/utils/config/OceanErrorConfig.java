package com.lkyl.oceanframework.common.utils.config;

import com.lkyl.oceanframework.common.utils.exception.ErrorCodeConfig;

import javax.annotation.PostConstruct;

public class OceanErrorConfig {

    @PostConstruct
    public void init(){
        ErrorCodeConfig.getInstance().init();
    }
}
