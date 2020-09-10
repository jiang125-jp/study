package com.jp.test.reids;

import com.jp.test.jpa.bean.Sheep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
public class RedisApi {
    private final ValueOperations<String, Object> valueOperations;

    @Autowired
    public RedisApi(ValueOperations<String, Object> valueOperations) {
        this.valueOperations = valueOperations;
    }

    @GetMapping("/testRedisApi")
    public void testRedisApi() {
        testRedis();
    }

    private void testRedis() {
        boolean b;
        List<Object> sheepList = new ArrayList<>();
        Sheep sheep = new Sheep();
        sheep.setName("咩咩");
        sheep.setAge(100);
        Sheep sheep2 = new Sheep();
        sheep2.setName("咩咩2");
        sheep2.setAge(120);
        Sheep sheep3 = new Sheep();
        sheep3.setName("咩咩3");
        sheep3.setAge(130);
        Sheep updateSheep = new Sheep();
        updateSheep.setName("咩咩4");
        updateSheep.setAge(140);

        // 普通存储测试
        valueOperations.set("name", sheep.getName(), 8000, TimeUnit.SECONDS);
        System.out.println("========== 存入String ==========");
        System.out.println(valueOperations.get("name"));
        System.out.println(valueOperations.getOperations().getExpire("name", TimeUnit.SECONDS));
        System.out.println(RedisUtil.getExpire("name"));
        RedisUtil.setExpire("name", 8000);
        System.out.println(valueOperations.getOperations().getExpire("name", TimeUnit.SECONDS));
        System.out.println(RedisUtil.getExpire("name"));

        valueOperations.set("name", sheep.getName());
        valueOperations.set("age", sheep.getAge());
        System.out.println("========== 存入String ==========");
        valueOperations.set("sheep", sheep);
        System.out.println("========== 存入对象 ==========");
        RedisUtil.setByExpire("sheep", sheep, 5);
        System.out.println("========== 存入对象5秒钟 ==========");
        RedisUtil.setExpire("sheep", 10);
        System.out.println("========== 设置有效期为10秒钟 ==========");
        long seconde = RedisUtil.getExpire("sheep");
        System.out.println("========== 查看对象有效期(s) ========== ：" + seconde);
        b = RedisUtil.hasKey("sheep");
        System.out.println("========== 根据key查看对象是否存在 ========== ：" + b);
        Sheep s = (Sheep) RedisUtil.get("sheep");
        System.out.println("========== 查看对象 ========== ：" + s);
        try {
            Thread.sleep(10000);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        s = (Sheep) RedisUtil.get("sheep");
        System.out.println("========== 查看对象是否失效 ========== ：" + s);
        String[] names = {"name", "age"};
        RedisUtil.del(names);
        System.out.println("========== 删除多个对象 ==========");
        b = RedisUtil.hasKey("name");
        System.out.println("========== 根据key查看对象是否存在 ========== ：" + b);
        b = RedisUtil.hasKey("age");
        System.out.println("========== 根据key查看对象是否存在 ========== ：" + b);


        // map存储测试
        RedisUtil.hashSet("sheepMap", "sheep", sheep);
        RedisUtil.hashSet("sheepMap", "sheep2", sheep2);
        System.out.println("========== 存入map对象 ==========");
        Sheep sheepResult = (Sheep) RedisUtil.hashGet("sheepMap", "sheep2");
        System.out.println("========== 获取map指定对象 ========== ：" + sheepResult);
        Map<Object, Object> mapResult = RedisUtil.hashMapGet("sheepMap");
        System.out.println("========== 获取map所有对象 ========== ：" + mapResult);
        RedisUtil.hashDel("sheepMap", "sheep2");
        mapResult = RedisUtil.hashMapGet("sheepMap");
        System.out.println("========== 获取map所有对象 ========== ：" + mapResult);
        b = RedisUtil.hasHashKey("sheepMap", "sheep");
        System.out.println("========== 获取map是否存在对象 ========== ：" + b);

        //list存储测试
        sheepList.add(sheep);
        sheepList.add(sheep2);

        RedisUtil.listSet("sheepList", sheepList);
        System.out.println("========== 存入list对象 ==========");
        List<Object> list2 = RedisUtil.listGet("sheepList", 0, -1);
        System.out.println("========== 获取list对象 ========== ：" + list2);

        RedisUtil.listSetValue("sheepList", sheep3);
        System.out.println("========== 将对象值放入缓存list ==========");
        list2 = RedisUtil.listGet("sheepList", 0, -1);
        System.out.println("========== 获取list对象 ========== ：" + list2);

        long listSize = RedisUtil.getListSize("sheepList");
        System.out.println("========== 获取list对象长度 ========== ：" + listSize);

        Sheep sheepGet2 = (Sheep) RedisUtil.listGetByIndex("sheepList", 1);
        System.out.println("========== 获取list第二个对象 ========== ：" + sheepGet2);

        List<Object> sheepGetList = RedisUtil.listGet("sheepList", 1, 2);
        System.out.println("========== 获取list第二个和第三个对象 ========== ：" + sheepGetList);

        RedisUtil.listUpdateByIndex("sheepList", 0, updateSheep);
        System.out.println("========== 修改list中第一个对象 ==========");
        list2 = RedisUtil.listGet("sheepList", 0, -1);
        System.out.println("========== 获取新的list对象 ========== ：" + list2);

        RedisUtil.listSetValue("sheepList", sheep3);
        System.out.println("========== 将重复的对象值放入缓存list ==========");
        list2 = RedisUtil.listGet("sheepList", 0, -1);
        System.out.println("========== 获取list对象 ========== ：" + list2);
        long remove = RedisUtil.listRemove("sheepList", 2, sheep3);
        System.out.println("========== 移除两个sheep3对象 ========== : " + remove);
        list2 = RedisUtil.listGet("sheepList", 0, -1);
        System.out.println("========== 获取最后的list对象 ========== ：" + list2);

        RedisUtil.del("sheepList");
        System.out.println("========== 删除list缓存 ==========");
        b = RedisUtil.hasKey("sheepList");
        System.out.println("========== 根据key查看对象是否存在 ========== ：" + b);

        List<Object> aList = new ArrayList<>();
        Map<String, Object> mm = new HashMap<>();
        mm.put("sheep", sheep);
        mm.put("sheep2", sheep2);
        aList.add(mm);
        RedisUtil.listSet("sheepMapList", aList);
        System.out.println("已存");
//        List<Object> mapList = RedisUtil.listGet("sheepMapList", 0, -1);
//        if (mapList != null && mapList.size() > 0) {
//            Map mapRs = (Map) mapList.get(0);
//            if (mapRs != null && mapRs.size() > 0) {
//                Sheep s1 = (Sheep) mapRs.get("sheep");
//                Sheep s2 = (Sheep) mapRs.get("sheep2");
//                System.out.println(s1);
//                System.out.println(s2);
//
//            }
//        }
    }
}
