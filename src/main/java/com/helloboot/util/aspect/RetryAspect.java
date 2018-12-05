package com.helloboot.util.aspect;

import com.helloboot.util.annotation.Retry;
import com.helloboot.util.thread.ConcurrentUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 注解{@link Retry} 的切面配置
 * 
 * @author lujianhao
 * @date 2018/11/30
 */
@Aspect
@Component
public class RetryAspect {
    @Pointcut("@annotation(retry)")
    public void pointcut(Retry retry) {
    }

    @Around("pointcut(retry)")
    public Object doInvoke(ProceedingJoinPoint pjp, Retry retry) {
        Object object = ConcurrentUtil.retryOnFailure(needRetryFlag -> {
            try {
                return pjp.proceed();
            } catch (Throwable throwable) {
                //.异常时标记为需要重试
                needRetryFlag.set(true);
                return null;
            }
        }, retry.retryTimes(), retry.delayInMillis());
        return object;
    }
}
