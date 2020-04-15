package com.xiongjie.quartz;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.Instant;
import java.util.Date;

/**
 * Created by xiongjie on 2018/10/22.
 */
@Component
public class SchedulerTask {

    private int num=0;

    /**
     * 相隔6秒说1次hello
     */
//    @Scheduled(cron="*/3 * * * * ?")
    private void sayhellonum(){
        System.out.println("hello"+(++num));
        if(num==100){
            System.out.println("rest a while,start new say hello");
        }
    }

    /**
     * 上一次开始执行时间点之后6秒再执行
     */
//    @Scheduled(fixedRate = 3000)
    public void reportCurrentTime() {
        Clock clock = Clock.systemDefaultZone();
        Instant instant = clock.instant();
        Date curDate = Date.from(instant);

        System.out.println("现在时间：" + curDate);
    }

}
