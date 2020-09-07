package com.jp.test.jpa.test;

import redis.clients.jedis.Jedis;

public class JedisTest extends Thread {

    private static Jedis jedis;


    public JedisTest() {
        if(jedis == null){
            jedis = new Jedis();
        }
        jedis.set("num", "1");
    }

    public void run() {
        for (int i = 0; i < 100; i++) {
            int num = Integer.parseInt(jedis.get("num"));
            num = num + 1;
            jedis.set("num", num + "");
            System.out.println(jedis.get("num"));
        }
    }

    public static void main(String[] args) {
        new JedisTest().start();
        new JedisTest().start();
    }
}
