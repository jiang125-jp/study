package com.study.design_mode.single.hungry;

/**
 * 单例模式-饿汉式
 *
 * @author jiangpeng
 * <p>
 * 优点：执行效率高、性能高、没有锁
 * 缺点：内存浪费，初始化的时候就加载了
 */
@SuppressWarnings("unused")
public class HungrySingleton {

    public static final HungrySingleton HUNGRY_SINGLETON = new HungrySingleton();

    private HungrySingleton() {
    }

    public static HungrySingleton getInstance() {
        return HUNGRY_SINGLETON;
    }
}
