package com.jp.test.jpa.services;

import com.jp.test.jpa.bean.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    Page<User> getUserList(Pageable pageable);

    List<User> getUserList();

    Long getUserTotalCount();
}
