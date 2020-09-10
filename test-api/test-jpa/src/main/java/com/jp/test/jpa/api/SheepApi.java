package com.jp.test.jpa.api;

import com.alibaba.fastjson.JSON;
import com.jp.test.reids.RedisUtil;
import com.jp.test.jpa.api.model.UserListResp;
import com.jp.test.jpa.bean.Sheep;
import com.jp.test.jpa.bean.User;
import com.jp.test.jpa.loader.UserThreadQuery;
import com.jp.test.jpa.services.SheepService;
import com.jp.test.jpa.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@RestController
public class SheepApi {

    private final SheepService sheepService;
    private final UserService userService;

    @Autowired
    public SheepApi(SheepService sheepService, UserService userService) {
        this.sheepService = sheepService;
        this.userService = userService;
    }

    @GetMapping("/getSheepList")
    public String getSheepByName() {
        List<Sheep> sheeps = sheepService.getSheepList();
        return JSON.toJSONString(sheeps);
    }

    /**
     * 待验证，1000W数据的数据加封装耗时如何？
     */
    @GetMapping("/getUserListSize")
    public UserListResp getUserList() throws Exception {
        UserListResp resp = new UserListResp();
        Long count = userService.getUserTotalCount();
        Map<Long, User> map = new HashMap<>();
        int pageSize = 10;
        //需要查询的次数
        int times = count.intValue() / pageSize;
        if (count % pageSize != 0) {
            times = times + 1;
        }
        int page = 1;
        List<Callable<List<User>>> tasks = new ArrayList<>();
        for (int i = 0; i < times; i++) {
            Callable<List<User>> qfe = new UserThreadQuery(userService, page, pageSize);
            tasks.add(qfe);
            page += 1;
        }
        //定义固定长度的线程池  防止线程过多
        ExecutorService executorService = Executors.newFixedThreadPool(15);
        List<Future<List<User>>> futures = executorService.invokeAll(tasks);
        //处理线程返回结果
        if (futures.size() > 0) {
            for (Future<List<User>> future : futures) {
                List<User> list = future.get();
                if (list.size() > 0) {
                    for (User user : list) {
                        map.put(user.getId(), user);
                    }
                }
            }
        }
        executorService.shutdown();//关闭线程池
        resp.setUsersize(map.size());
        return resp;
    }
}
