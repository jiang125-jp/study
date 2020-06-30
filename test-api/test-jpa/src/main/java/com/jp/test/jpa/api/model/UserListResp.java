package com.jp.test.jpa.api.model;


import lombok.Data;

import javax.validation.Valid;

@Data
public class UserListResp {

    @Valid
    private Integer usersize;
}
