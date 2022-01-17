package com.lkyl.oceanFramework.common.utils.test;

import com.lkyl.oceanFramework.common.mapping.annotation.Builder;

public class Person {

    private Integer age;
    private String name;

    public Integer getAge() {
        return age;
    }

    @Builder
    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    @Builder
    public void setName(String name) {
        this.name = name;
    }
}
