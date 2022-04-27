package com.study.design_mode.single.seriableSingleton;

import java.io.*;

/**
 * 单例模式-反序列化
 *
 * @author jiangpeng
 *
 * 由于类的生成，是在多线程调用类之前，所以加载顺序决定了是线程安全的
 */
public class SerializableSingleton implements Serializable {

    private static final SerializableSingleton INSTANCE = new SerializableSingleton();

    private SerializableSingleton() {
    }

    public static SerializableSingleton getInstance() {
        return INSTANCE;
    }

    /**
     * 重点：加上此方法后，反序列化的对象为同一个对象
     */
    private Object readResolve() {
        return INSTANCE;
    }

    public static void main(String[] args) {
        SerializableSingleton s1;
        //生成s2单例对象
        SerializableSingleton s2 = SerializableSingleton.getInstance();

        FileOutputStream fos;
        try {
            //把s2序列化到磁盘上
            fos = new FileOutputStream("SerializableSingleton.obj");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(s2);
            oos.flush();
            oos.close();

            //再把s2反序列化回来，赋值给s1
            FileInputStream fis = new FileInputStream("SerializableSingleton.obj");
            ObjectInputStream ois = new ObjectInputStream(fis);
            s1 = (SerializableSingleton) ois.readObject();
            ois.close();

            //反序列化之后的对象，不是同一个单例对象了
            System.out.println(s1);
            System.out.println(s2);
            System.out.println(s1 == s2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
