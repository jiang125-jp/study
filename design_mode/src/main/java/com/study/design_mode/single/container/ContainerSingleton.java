package com.study.design_mode.single.container;

import org.springframework.util.StringUtils;

import java.lang.reflect.Constructor;
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
        //线程不安全,需要加同步锁
        synchronized (ContainerSingleton.class) {
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
        }
        return instance;
    }

    public static void main(String[] args) throws Exception {
        //实例1： 是否是单例
//        Object instance1 = ContainerSingleton.getInstance("com.study.design_mode.single.container.Container");
//        Object instance2 = ContainerSingleton.getInstance("com.study.design_mode.single.container.Container");
//        System.out.println(instance1 == instance2);

        //实例1： 线程不安全
//        Thread t1 = new Thread(new ThreadTest());
//        Thread t2 = new Thread(new ThreadTest());
//        t1.start();
//        t2.start();

        //实例3： 反射
        Class<?> clazz = Container.class;
        Constructor c = clazz.getDeclaredConstructor();
        Object obj = c.newInstance();
        System.out.println(obj);
    }
}

class ThreadTest implements Runnable{
    @Override
    public void run() {
        Object instance = ContainerSingleton.getInstance("com.study.design_mode.single.container.Container");
        System.out.println(instance);
    }
}
