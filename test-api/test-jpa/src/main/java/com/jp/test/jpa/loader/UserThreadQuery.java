package com.jp.test.jpa.loader;

import com.jp.test.jpa.bean.User;
import com.jp.test.jpa.services.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class UserThreadQuery implements Callable<List<User>> {

    private List<User> userList = new ArrayList<>();//每次分页查出来的数据

    public UserThreadQuery(UserService userService, Integer page, Integer pageSize) {
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Page<User> userPage = userService.getUserList(pageable);
        if (userPage.hasContent()) {
            userList.addAll(userPage.getContent());
        }
    }

    @Override
    public List<User> call() throws Exception {
        return userList;
    }
}
