package com.study.design_mode.single.container;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 单例模式-容器式创建
 *
 * @author jiangpeng
 * 优点：避免内存消耗，防止反射
 * 缺点：线程不安全
 */
@SuppressWarnings("unused")
public class ContainerSingleton {

    private ContainerSingleton() {
    }

    private static final Map<String, Object> IOC = new ConcurrentHashMap<>();

    public static Object getInstance(String className) {
        Object instance = null;
        //线程不安全
        if (!IOC.containsKey(className)) {
            try {
                instance = Class.forName(className).newInstance();
                IOC.put(className, instance);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            instance = IOC.get(className);
        }
        return instance;
    }

    public static void main(String[] args) {
//        Object instance1 = ContainerSingleton.getInstance("com.study.design_mode.single.container.Container");
//        Object instance2 = ContainerSingleton.getInstance("com.study.design_mode.single.container.Container");
//        System.out.println(instance1 == instance2);

        Thread t1 = new Thread(new ThreadTest());
        Thread t2 = new Thread(new ThreadTest());
        t1.start();
        t2.start();
    }
}

class ThreadTest implements Runnable{
    @Override
    public void run() {
        Object instance = ContainerSingleton.getInstance("com.study.design_mode.single.container.Container");
        System.out.println(instance);
    }
}
