package com.jp.test.jpa.bean;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "user")
public class User implements Serializable {

    /**
     * 账户ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * 不带+86的手机号
     */
    @Column(name = "short_phone")
    private String shortPhone;
    /**
     * 手机号
     */
    @Column(name = "phone_number")
    private String phoneNumber;

    /**
     * 电子邮箱
     */
    @Column(name = "email_address")
    private String emailAddress;

}
