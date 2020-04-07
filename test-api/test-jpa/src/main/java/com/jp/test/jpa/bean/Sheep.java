package com.jp.test.jpa.bean;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Table(name = "sheep")
@Entity
public class Sheep implements Serializable {
    @Id
    private Long id;
    private String name;
    private Integer age;
}
