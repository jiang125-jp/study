package com.jp.test.git.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProjectVo {

    private Integer id;

    private String name;

    private String nameWithNamespace;

    private String pathWithNamespace;

    private String webUrl;

    private String createdAt;
}
