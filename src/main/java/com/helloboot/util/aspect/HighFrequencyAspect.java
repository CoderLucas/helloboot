package com.helloboot.util.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author lujianhao
 * @date 2018/12/5
 */
@Aspect
@Component
public class HighFrequencyAspect {
//    private static final String LOCK_PREFIX = "ASK_HIGH_FREQUENCY1_";
//    private static final String CACHE_PREFIX = "ASK_HIGH_FREQUENCY2_";
//
//    private long maxTimemout = DateUtils.MILLIS_PER_MINUTE * 30;
//
//    @Autowired
//    private MemcachedClient defaultMemcachedClient;
//    @Autowired
//    private CacheEnableSupport cacheEnableSupport;
//
//    private static JsonDataHolder defaultJsonDataHolder = new JsonDataHolder();
//
//    static {
//        defaultJsonDataHolder.error(ErrorCodeEnum.EC_HIGH_FREQUENCY);
//    }
//
//    @Around(value = "@annotation(barrier)")
//    public Object aroundMethod(ProceedingJoinPoint pjp, HighFrequencyBarrier barrier) throws Throwable {
//
//        if (maxTimemout < 1 || barrier.interval() < 0 || TimeUnit.MILLISECONDS.convert(barrier.interval(), barrier.unit()) > maxTimemout) {
//            return pjp.proceed();
//        }
//
//        Method currentMethod = AspectUtil.getJoinPointCurrentMethod(pjp);
//        //memcachedClient允许的key最大长度为250
//        String keyStr = CacheEnableUtil.generateKey(pjp, barrier.group(), barrier.keyDeep(), barrier.keys(), currentMethod, cacheEnableSupport.getMaxKeyLength());
//
//        int cacheTime = (int) TimeUnit.SECONDS.convert(barrier.interval(), barrier.unit());
//        String cacheKey = null;
//
//        if (cacheTime > 0) {
//            cacheKey = CACHE_PREFIX + keyStr;
//            //为了性能，这里先检查一次；也可以没有
//            if (defaultMemcachedClient.get(cacheKey) != null) {
//                return getErrorCode(barrier);
//            }
//        }
//
//        Lock lock = DistributeLockServiceHelper.get(LOCK_PREFIX + keyStr);
//        if (!lock.tryLock()) {
//            return getErrorCode(barrier);
//        }
//        try {
//            if (cacheTime > 0 && defaultMemcachedClient.get(cacheKey) != null) {
//                return getErrorCode(barrier);
//            }
//            Object value = pjp.proceed();
//            if (cacheTime > 0 && !isError(value)) {
//                defaultMemcachedClient.add(cacheKey, cacheTime, true);
//            }
//            return value;
//        } finally {
//            lock.unlock();
//        }
//    }
//
//    private boolean isError(Object value) {
//        if (value == null) {
//            return false;
//        }
//        if (value instanceof JsonDataHolder) {
//            JsonDataHolder jsonDataHolder = (JsonDataHolder) value;
//            if (MapUtils.isNotEmpty(jsonDataHolder.getError())) {
//                return true;
//            }
//        }
//        if (value instanceof com.dxy.commons.json.JsonDataHolder) {
//            com.dxy.commons.json.JsonDataHolder jsonDataHolder = (com.dxy.commons.json.JsonDataHolder) value;
//            if (MapUtils.isNotEmpty(jsonDataHolder.getError())) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    private JsonDataHolder getErrorCode(HighFrequencyBarrier barrier) {
//        if (ErrorCodeEnum.EC_HIGH_FREQUENCY == barrier.errorCode() && StringUtils.isBlank(barrier.message())) {
//            return defaultJsonDataHolder;
//        } else {
//            return new JsonDataHolder().error(barrier.errorCode(), barrier.message());
//        }
//    }
}
