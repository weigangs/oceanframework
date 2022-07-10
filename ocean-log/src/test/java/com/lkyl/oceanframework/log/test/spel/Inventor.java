package com.lkyl.oceanframework.log.test.spel;

import lombok.Data;

import java.util.Date;

@Data
public class Inventor {

    public Inventor(String id, String name, String nationality, Date birthdate) {
        this.id = id;
        this.name = name;
        this.nationality = nationality;
        this.birthdate = birthdate;
    }

    private String id;
    private String name;
    private String nationality;
    private String[] inventions;
    private Date birthdate;
    private PlaceOfBirth placeOfBirth;
    // 省略其它方法

    public String getContinuationNum() {
        return "28";
    }

    public String getTotalNum() {
        return "500";
    }
}
