package com.weicl.autopagingcore.utils;

import javax.annotation.PostConstruct;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.stereotype.Component;

/**
 * Spring ApplicationContext工具，类通过 {@link Component}注解注入到bean容器，
 * 初始化时调用init方法，将ApplicationContext赋值到静态变量instance上。
 * @author weicl
 */
@Component
public class ApplicationContextUtil extends ApplicationObjectSupport {

    private static ApplicationContext instance;

    public static ApplicationContext getContext() {
        return instance;
    }

    @PostConstruct
    private void init() {
        instance = getApplicationContext();
    }

}