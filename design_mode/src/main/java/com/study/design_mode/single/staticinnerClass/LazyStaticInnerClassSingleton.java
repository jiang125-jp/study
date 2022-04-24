package com.study.design_mode.single.staticinnerClass;

import java.lang.reflect.Constructor;

/**
 * 单例模式-静态内部类(懒汉式)
 *
 * @author jiangpeng
 * <p>
 * 优点：代码简洁，利用了懒加载的特性，性能高，避免内存浪费，不被反射破坏
 * 缺点：不优雅
 */
@SuppressWarnings("unused")
public class LazyStaticInnerClassSingleton {
    /**
     * 私有构造方法里，静止非法访问，可以防止反射破解
     */
    private LazyStaticInnerClassSingleton() {
        throw new RuntimeException("不允许非法访问");
    }

    public static LazyStaticInnerClassSingleton getInstance() {
        return LazyStaticInnerLoader.LAZY_SINGLETON;
    }

    /**
     * LazyStaticInnerSingleton加载时不会加载，在调用getInstance时，才会加载
     */
    private static class LazyStaticInnerLoader {
        private static final LazyStaticInnerClassSingleton LAZY_SINGLETON = new LazyStaticInnerClassSingleton();
    }

    /**
     * 反射破坏单例
     */
    public static void main(String[] args) {
        try {
            Class<?> clazz = LazyStaticInnerClassSingleton.class;
            Constructor<?> constructor = clazz.getDeclaredConstructor();
            constructor.setAccessible(true);
            Object obj = constructor.newInstance();
            System.out.println(obj);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
