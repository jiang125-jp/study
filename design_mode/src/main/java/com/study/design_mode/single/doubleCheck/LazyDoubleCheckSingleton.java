package com.study.design_mode.single.doubleCheck;

/**
 * 单例模式-双重检索
 *
 * @author jiangpeng
 * <p>
 * 优点：提升性能，线程安全
 */
@SuppressWarnings("unused")
public class LazyDoubleCheckSingleton {

    private static volatile LazyDoubleCheckSingleton instance;

    private LazyDoubleCheckSingleton() {
    }

    /**
     * 同步方法，性能低
     */
    public static LazyDoubleCheckSingleton getInstance1() {
        synchronized (LazyDoubleCheckSingleton.class) {
            if (instance == null) {
                instance = new LazyDoubleCheckSingleton();
            }
        }
        return instance;
    }

    /**
     * instance == null 并发时一样都会进入，得到不同对象，单例失败
     */
    public static LazyDoubleCheckSingleton getInstance2() {
        if (instance == null) {
            synchronized (LazyDoubleCheckSingleton.class) {
                instance = new LazyDoubleCheckSingleton();
            }
        }
        return instance;
    }

    /**
     * 双重检查，提醒性能，线程安全
     */
    public static LazyDoubleCheckSingleton getInstance3() {
        if (instance == null) {
            synchronized (LazyDoubleCheckSingleton.class) {
                if (instance == null) {
                    //还有指令重排序的问题 加volatile可解决
                    instance = new LazyDoubleCheckSingleton();
                }
            }
        }
        return instance;
    }
}

class TestLazyInit implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + ":" + LazyDoubleCheckSingleton.getInstance3());
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


