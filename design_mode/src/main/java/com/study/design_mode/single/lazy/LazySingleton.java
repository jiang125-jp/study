package com.study.design_mode.single.lazy;

/**
 * 单例模式-懒汉式
 *
 * @author jiangpeng
 * <p>
 * 优点：节省内存
 * 缺点：线程不安全 如果加synchronized，性能低
 */
@SuppressWarnings("unused")
public class LazySingleton {

    private static LazySingleton instance;

    private LazySingleton() {
    }

    public static LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }
}

class TestLazyInit implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + ":" + LazySingleton.getInstance());
    }

    /**
     * 两个线程同时初始化，得到的可能是不同的对象，单例失败
     */
    public static void main(String[] args) {
        Thread t1 = new Thread(new TestLazyInit());
        Thread t2 = new Thread(new TestLazyInit());
        t1.start();
        t2.start();
    }
}


