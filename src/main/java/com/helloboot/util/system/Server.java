package com.helloboot.util.system;

import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * @author lujianhao
 * @date 2018/12/5
 */
public class Server {
    public static final String CHARSET = "UTF-8";

    public static final ScheduledExecutorService SCHEDULER = new ScheduledThreadPoolExecutor(Runtime.getRuntime().availableProcessors() * 4);
    public static final Executor EXECUTOR = SCHEDULER;
}
