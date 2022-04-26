package com.study.design_mode.single.localThread;

/**
 * 单例模式-本地线程单例
 * @author jiangpeng
 */
public class LocalThreadSingleton {

    private static final ThreadLocal<LocalThreadSingleton> INSTANCE = ThreadLocal.withInitial(LocalThreadSingleton::new);

    private LocalThreadSingleton(){}

    public static LocalThreadSingleton getInstance(){
        return INSTANCE.get();
    }

    public static void main(String[] args) {
        //在同一个线程里，获取到的对象是同一个，本地线程单例
        System.out.println(LocalThreadSingleton.getInstance());
        System.out.println(LocalThreadSingleton.getInstance());

        //在不同的线程里，获取到的对象不是同一个了
        Thread t1 = new Thread(new LocalThreadSingletonTest());
        Thread t2 = new Thread(new LocalThreadSingletonTest());
        t1.start();
        t2.start();
    }
}

class LocalThreadSingletonTest implements Runnable{

    @Override
    public void run() {
        System.out.println(LocalThreadSingleton.getInstance());
    }
}