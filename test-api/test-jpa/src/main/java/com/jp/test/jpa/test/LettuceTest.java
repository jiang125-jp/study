package com.jp.test.jpa.test;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisStringCommands;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class LettuceTest extends Thread {
    private static RedisStringCommands<String, String> sync;

    public LettuceTest() {
        if (sync == null) {
            RedisClient client = RedisClient.create("redis://localhost");
            StatefulRedisConnection<String, String> connection = client.connect();
            sync = connection.sync();
        }
        sync.set("num", "1");
    }

    public void run() {
        for (int i = 0; i < 100; i++) {
            int num = Integer.parseInt(sync.get("num"));
            num = num + 1;
            sync.set("num", num + "");
            System.out.println(sync.get("num") + " ===== threadName:" + Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) {
        new LettuceTest().start();
        new LettuceTest().start();
    }
}
