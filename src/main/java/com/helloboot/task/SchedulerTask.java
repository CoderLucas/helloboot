package com.helloboot.task;

import org.springframework.stereotype.Component;

/**
 * @author lujianhao
 * @date 2019-08-20
 */
@Component
public class SchedulerTask {
    private int count = 0;

//    @Scheduled(cron="*/6 * * * * ?")
//    @Scheduled(fixedRate = 6000)
    public void process1() {
        System.out.println("this is scheduler task runing  " + (count++));
    }

}
