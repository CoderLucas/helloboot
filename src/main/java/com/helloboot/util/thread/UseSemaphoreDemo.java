package com.helloboot.util.thread;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author lujianhao
 * @date 2018/12/6
 */
public class UseSemaphoreDemo {
    public static void main(String[] args) {
        // 返回一个可以根据实际情况调整线程个数的线程池，不限制最大的线程数量，若有空闲的线程，则执行任务，若无任务则不创建线程，并且每一个空闲线程会在60秒后自动回收
        ExecutorService executor = Executors.newCachedThreadPool();
        // 同时只能有 5 个线程同时访问
        final Semaphore semaphore = new Semaphore(5);
        // 模拟 20个线程同时访问
        for (int index = 0; index < 20; index++) {
            final int NO = index;
            Runnable run = new Runnable() {
                @Override
                public void run() {
                    try {
                        semaphore.acquire();
                        System.out.println(NO + "已经获取许可");
                        // 模拟实际业务逻辑
                        Thread.sleep(1000 * (new Random()).nextInt(10));
                        // 释放许可
                        semaphore.release();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            executor.submit(run);
        }
    }
}
