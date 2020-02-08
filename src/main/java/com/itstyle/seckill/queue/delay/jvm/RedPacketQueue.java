package com.itstyle.seckill.queue.delay.jvm;

import com.itstyle.seckill.common.entity.RedPacketMessage;

import java.util.concurrent.DelayQueue;

/**
 * 红包延迟队列
 */
public class RedPacketQueue {

    /** 用于多线程间下单的队列 */
    private static DelayQueue<RedPacketMessage> queue = new DelayQueue<>();

    /**
     * 私有的默认构造子，保证外界无法直接实例化
     */
    private RedPacketQueue(){}
    /**
     * 类级的内部类，也就是静态的成员式内部类，该内部类的实例与外部类的实例
     * 没有绑定关系，而且只有被调用到才会装载，从而实现了延迟加载
     */
    private static class SingletonHolder{
        /**
         * 静态初始化器，由JVM来保证线程安全
         */
        private  static RedPacketQueue queue = new RedPacketQueue();
    }
    //单例队列
    public static RedPacketQueue getQueue(){
        return SingletonHolder.queue;
    }
    /**
     * 生产入队
     */
    public  Boolean  produce(RedPacketMessage message){
        return queue.add(message);
    }
    /**
     * 消费出队
     */
    public  RedPacketMessage consume() throws InterruptedException {
        return queue.take();
    }
}
