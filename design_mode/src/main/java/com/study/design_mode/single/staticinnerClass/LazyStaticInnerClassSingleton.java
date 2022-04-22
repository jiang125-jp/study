package com.study.design_mode.single.staticinnerClass;

import java.lang.reflect.Constructor;

/**
 * 单例模式-静态内部类(懒汉式)
 *
 * @author jiangpeng
 * <p>
 * 优点：代码简洁，利用了懒加载的特性，性能高，避免内存浪费
 * 缺点：被反射破坏
 */
@SuppressWarnings("unused")
public class LazyStaticInnerClassSingleton {

    private LazyStaticInnerClassSingleton() {
    }

    private static LazyStaticInnerClassSingleton getInstance() {
        return LazyStaticInnerLoader.LAZY_SINGLETON;
    }

    /**
     * LazyStaticInnerSingleton加载时不会加载，在调用getInstance时，才会加载
     */
    private static class LazyStaticInnerLoader {
        private static final LazyStaticInnerClassSingleton LAZY_SINGLETON = new LazyStaticInnerClassSingleton();
    }
}
