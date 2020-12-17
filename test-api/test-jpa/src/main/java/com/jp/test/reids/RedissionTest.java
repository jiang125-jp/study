package com.jp.test.reids;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedissionTest {
    private final ValueOperations<String, Object> valueOperations;
    private final Redisson redisson;

    @Autowired
    public RedissionTest(ValueOperations<String, Object> valueOperations, Redisson redisson) {
        this.valueOperations = valueOperations;
        this.redisson = redisson;
        valueOperations.set("stock", 100);
    }

    @GetMapping(value = "/subtraction")
    public void subtraction() {
        Integer stock = (Integer) valueOperations.get("stock");
        if (null != stock && stock > 0) {
            stock -= 1;
            valueOperations.set("stock", stock);
        }
        System.out.println(stock);
    }

    @GetMapping(value = "/subtractionBySyn")
    public void subtractionBySyn() {
        synchronized (this) {
            Integer stock = (Integer) valueOperations.get("stock");
            if (null != stock && stock > 0) {
                stock -= 1;
                valueOperations.set("stock", stock);
            }
            System.out.println(stock);
        }
    }

    @GetMapping(value = "/subtractionByRedisLock")
    public void subtractionByRedisLock() {
        String lockKey = "jp";
        Boolean bool = valueOperations.setIfAbsent("lockKey", lockKey);
        if (bool != null && bool) {
            Integer stock = (Integer) valueOperations.get("stock");
            if (stock != null && stock > 0) {
                stock -= 1;
                valueOperations.set("stock", stock);
            }
            System.out.println(stock);
            valueOperations.getOperations().delete("lockKey");
        }
    }

    @GetMapping(value = "/subtractionByRedisson")
    public void subtractionByRedisson() {
        String lockKey = "jp";
        RLock rLock = redisson.getLock(lockKey);
        rLock.lock();
        Integer stock = (Integer) valueOperations.get("stock");
        if (stock != null && stock > 0) {
            stock -= 1;
            valueOperations.set("stock", stock);
        }
        System.out.println(stock);
        rLock.unlock();
    }
}
