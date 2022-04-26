package com.study.design_mode.single.enumSingle;

/**
 * 单例模式-枚举
 *
 * @author jiangpeng
 * 优点： 线程安全，防止反射
 * 缺点： 不能生成大量单例实例，会被序列化破坏
 */
public enum EnumSingleton {
    /**
     * 枚举单例
     */
    INSTANCE;

    private Object data;

    public void setObject(Object data){
        this.data = data;
    }

    public Object getObject(){
        return data;
    }


    public EnumSingleton getInstance() {
        return INSTANCE;
    }

    public static void main(String[] args) {

    }
}
