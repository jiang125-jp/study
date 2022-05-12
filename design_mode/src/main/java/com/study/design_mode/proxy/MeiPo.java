package com.study.design_mode.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 代理模式-媒婆
 * @author jiangpeng
 */
public class MeiPo implements InvocationHandler {

    private IPerson person;

    public IPerson getInstance(IPerson person){
        this.person = person;
        Class<?> clazz = person.getClass();
        return (IPerson) Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws InvocationTargetException, IllegalAccessException {
        after();
        Object result = method.invoke(person, args);
        before();
        return result;
    }

    private void after(){
        System.out.println("寻找目标");
    }

    private void before(){
        System.out.println("开始交往");
    }
}
