package com.study.design_mode.single.staticinnerClass;

import java.lang.reflect.Constructor;

/**
 * 反射破坏单例
 *
 * @author jiangpeng
 */
public class ReflectTest {

    public static void main(String[] args) {
        try {
            Class<?> clazz = LazyStaticInnerClassSingleton.class;
            Constructor constructor = clazz.getDeclaredConstructor((Class<?>) null);
            Object obj = constructor.newInstance();
            System.out.println(obj);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
