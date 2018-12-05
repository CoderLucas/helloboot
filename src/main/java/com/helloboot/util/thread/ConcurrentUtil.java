package com.helloboot.util.thread;

import com.helloboot.util.base.Wrapper;
import com.helloboot.util.system.Server;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * @author lujianhao
 * @date 2018/11/30
 */
public final class ConcurrentUtil {
    public static void sleepQuiet(long timeout) {
        if (timeout > 0) {
            try {
                Thread.sleep(timeout);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean execute(final Runnable runnable, long timeout) {
        if (runnable != null) {
            final CountDownLatch latch = new CountDownLatch(1);
            Thread thread = new Thread(() -> {
                try {
                    runnable.run();
                } catch (Throwable e) {
                    e.printStackTrace();
                } finally {
                    latch.countDown();
                }
            });
            thread.start();
            try {
                latch.await(timeout, TimeUnit.MILLISECONDS);
                if (latch.getCount() > 0) {
                    thread.interrupt();
                    return false;
                }
            } catch (Throwable e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }
        return false;
    }

    public static void execute(Runnable runnable) {
        if (runnable != null) {
            Server.EXECUTOR.execute(runnable);
        }
    }

    public static <T> T retryOnFailure(Callable<T> callable, int retryTimes, int delayInMillis) {
        if (callable != null) {
            if (retryTimes <= 0) {
                retryTimes = Short.MAX_VALUE;
            }

            for (int i = 0; i < retryTimes; i++) {
                try {
                    return callable.call();
                } catch (Throwable e) {
                }
                if (delayInMillis > 0) {
                    ConcurrentUtil.sleepQuiet(delayInMillis);
                }
            }
        }
        return null;
    }

    public static <R> R retryOnFailure(Function<Wrapper<Boolean>, R> function, int retryTimes, Long delayInMillis) {
        if(function == null) {
            return null;
        } else {
            if(retryTimes <= 0) {
                retryTimes = 32767;
            }

            for(int i = 0; i < retryTimes; ++i) {
                try {
                    Wrapper<Boolean> failure = new Wrapper(Boolean.valueOf(false));
                    R result = function.apply(failure);
                    if(failure.get() == null || !((Boolean)failure.get()).booleanValue()) {
                        return result;
                    }
                } catch (Throwable var7) {
                    if(delayInMillis != null && delayInMillis.longValue() > 0L) {
                        sleepQuiet(delayInMillis.longValue());
                    }
                }
            }
            return null;
        }
    }
}
