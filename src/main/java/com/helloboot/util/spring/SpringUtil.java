package com.helloboot.util.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lujianhao
 * @date 2019/5/29
 */
public class SpringUtil implements ApplicationContextAware{
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringUtil.applicationContext = applicationContext;
    }

    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) {
        if (applicationContext != null) {
            try {
                return (T) applicationContext.getBean(name);
            } catch (Throwable e) {
            }
        }
        return null;
    }

    public static <T> T getBean(Class<T> clazz) {
        if (applicationContext != null) {
            try {
                return applicationContext.getBean(clazz);
            } catch (Throwable e) {
            }
        }
        return null;
    }

    public static HttpServletRequest getHttpServletRequest() {
        ServletRequestAttributes sra = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes());
        if (sra != null) {
            return sra.getRequest();
        }
        return null;
    }

    public static void publishEvent(ApplicationEvent event) {
        Assert.notNull(applicationContext, "applicationContext is null");
        applicationContext.publishEvent(event);
    }
}
