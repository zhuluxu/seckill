package com.itstyle.seckill.queue.delay.netty;

import io.netty.util.HashedWheelTimer;
import io.netty.util.Timer;
import io.netty.util.TimerTask;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * 基于 netty 的时间轮算法 HashedWheelTimer 实现的延迟任务
 * https://zhuanlan.zhihu.com/p/65835110
 */
public class RedPacketHashedWheelTimer {

    public static void main(String[] args) throws Exception {
        ThreadFactory factory = r -> {
            Thread thread = new Thread(r);
            thread.setDaemon(true);
            thread.setName("RedPacketHashedWheelTimerWorker");
            return thread;
        };
        /**
         * @param tickDuration - 每tick一次的时间间隔
         * @param unit - tickDuration 的时间单位
         * @param ticksPerWheel - 时间轮中的槽数
         * @param leakDetection
         */
        Timer timer = new HashedWheelTimer(factory, 1,
                                           TimeUnit.SECONDS, 100,true);
        for(int i=0;i<100;i++){
            TimerTask timerTask = new RedPacketTimerTask(i);
            timer.newTimeout(timerTask, i, TimeUnit.SECONDS);
        }
        Thread.sleep(Integer.MAX_VALUE);
    }
}
