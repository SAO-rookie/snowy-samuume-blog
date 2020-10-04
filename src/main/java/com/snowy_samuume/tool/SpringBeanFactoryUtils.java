package com.snowy_samuume.tool;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author snowy
 * @date 2020/9/22 22:44
 * 解决过滤器无法注入Bean
 *
 */
@Component
public class SpringBeanFactoryUtils implements ApplicationContextAware {
    private static ApplicationContext context = null;

    public static <T> T getBean(Class<T> type) {
        return context.getBean(type);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (SpringBeanFactoryUtils.context == null) {
            SpringBeanFactoryUtils.context = applicationContext;
        }
    }
}
