package com.helloboot.util.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * 加锁实现串行化方法调用的切面
 * @author lujianhao
 * @date 2018/12/5
 */
@Aspect
@Component
public class SerializabledAspect {
//    @Pointcut("@annotation(serializabledWithLock)")
//    public void pointcut(SerializabledWithLock serializabledWithLock) {
//    }
//
//    @Around("pointcut(serializabledWithLock)")
//    public Object doInvoke(ProceedingJoinPoint pjp, SerializabledWithLock serializabledWithLock) throws Throwable {
//        String key = serializabledWithLock.key();
//
//        Method currentMethod = AspectUtil.getJoinPointCurrentMethod(pjp);
//        Object value = AspectUtil.getByExpress(serializabledWithLock.key(), pjp.getArgs(), currentMethod, pjp.getTarget());
//        if(value == null){
//            throw new RuntimeException("key express error");
//        }
//        //memcachedClient允许的key最大长度为250
//        String valueStr = CacheEnableUtil.toKeyString(1, 100, value);
//        if (StringUtils.isEmpty(valueStr)) {
//            throw new RuntimeException("key express error");
//        }
//
//        Wrapper<Object> resultWrapper = new Wrapper<>(null);
//        Wrapper<Throwable> throwableWrapper = new Wrapper<>(null);
//        DistributeLockServiceHelper.tryLock(valueStr, new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    resultWrapper.set(pjp.proceed());
//                } catch (Throwable throwable) {
//                    throwableWrapper.set(throwable);
//                }
//            }
//        }, serializabledWithLock.timeout(), TimeUnit.MILLISECONDS);
//        if (throwableWrapper.get() != null) {
//            throw throwableWrapper.get();
//        }
//        return resultWrapper.get();
//    }

}
