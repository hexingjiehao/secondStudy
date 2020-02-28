package javaKnowledge.thread.multiThread;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by xiongjie on 2018/11/5.
 */
public class XjTimer extends TimerTask{

    @Override
    public void run() {
        System.out.println("定时器Timer运行");
    }

    public static void main(String[] args){
        System.out.println("当前时间"+new Date());

        Calendar calendar=Calendar.getInstance();
        calendar.add(Calendar.SECOND,1);
        Date date=calendar.getTime();

        XjTimer xjTimer=new XjTimer();
        Timer timer=new Timer(true); //作为守护进程和时间有冲突
        timer.schedule(xjTimer,date,1000);

    }
}