package com.helloboot.util.thread;

import java.util.concurrent.CountDownLatch;

/**
 * @author lujianhao
 * @date 2018/12/6
 */
public class UserCountDownLatchDemo {
    public static void main(String[] args) {
        final CountDownLatch countDown = new CountDownLatch(2);
        // CountDownLatch(2) 代表一个 countDown.await(); 需要两个countDown.countDown();来唤醒

        Thread t1 = new Thread(() -> {
            try {
                System.out.println("进入线程1：等待其他线程处理完成");
                countDown.await();
                System.out.println("t1 线程继续执行");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            try {
                System.out.println("进入线程2：线程开始初始化");
                Thread.sleep(3000);
                countDown.countDown();
                System.out.println("t2 线程初始化完毕");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t2");

        Thread t3 = new Thread(() -> {
            try {
                System.out.println("进入线程3：线程开始初始化");
                Thread.sleep(3000);
                countDown.countDown();
                System.out.println("t3 线程初始化完毕");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t3");

        t1.start();
        t2.start();
        t3.start();
    }
}
