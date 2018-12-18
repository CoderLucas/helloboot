package com.helloboot.util.thread;

import java.util.concurrent.*;

/**
 * @author lujianhao
 * @date 2018/12/6
 */
public class UseFutureDemo implements Callable<String>{
    private String para;

    public UseFutureDemo(String para) {
        this.para = para;
    }

    /**
     * 这里是真实的业务逻辑，执行起来可能比较慢
     * @return
     * @throws Exception
     */
    @Override
    public String call() throws Exception {
        // 模拟执行耗时
        Thread.sleep(3000);
        return this.para + "处理完成";
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        String queryStr = "query";
        FutureTask future = new FutureTask<>(new UseFutureDemo(queryStr));
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future f = executor.submit(future);
        System.out.println("请求完毕");

        Thread.sleep(1000);

        // future.get() 没得到数据前会阻塞
        System.out.println("数据：" + future.get());
        executor.shutdown();
    }
}
