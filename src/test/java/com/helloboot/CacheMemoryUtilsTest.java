package com.helloboot;

import com.helloboot.util.cache.CacheMemoryUtils;
import org.junit.Test;

/**
 * @author lujianhao
 * @date 2019/5/8
 */
public class CacheMemoryUtilsTest {
    @Test
    public void putCache() throws InterruptedException {
        CacheMemoryUtils.put("Lucas", "Lucas");
        CacheMemoryUtils.put("Lucas1", "Lucas1", 1);
        System.out.println((String) CacheMemoryUtils.get("Lucas"));
        System.out.println((String) CacheMemoryUtils.get("Lucas1"));
        Thread.sleep(2000);
        System.out.println((String) CacheMemoryUtils.get("Lucas"));
        System.out.println((String) CacheMemoryUtils.get("Lucas1"));
        System.out.println((String) CacheMemoryUtils.get("Lucas", "expire"));
        System.out.println((String) CacheMemoryUtils.get("Lucas1", "expire"));
    }

    @Test
    public void remove() throws InterruptedException {
        CacheMemoryUtils.put("Lucas", "Lucas");
        CacheMemoryUtils.put("Joe", "Joe");
        CacheMemoryUtils.put("Lucas1", "Lucas1", 1);
        System.out.println("移除缓存前的数量："+CacheMemoryUtils.getCacheCount());
        System.out.println("移除一个缓存");
        CacheMemoryUtils.remove("Lucas");
        System.out.println("移除缓存后的数量："+CacheMemoryUtils.getCacheCount());
        System.out.println("线程等待两秒");
        Thread.sleep(2000);
        System.out.println("移除过期缓存");
        CacheMemoryUtils.clearExpired();
        System.out.println("移除过期缓存后的数量："+CacheMemoryUtils.getCacheCount());
        System.out.println("移除所有缓存");
        CacheMemoryUtils.clear();
        System.out.println("移除所有缓存后的数量："+CacheMemoryUtils.getCacheCount());
    }
}
