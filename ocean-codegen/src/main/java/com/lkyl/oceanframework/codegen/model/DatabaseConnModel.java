package com.lkyl.oceanframework.codegen.model;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class DatabaseConnModel {

    private String url;
    private String username;
    private String password;
    private String driver;
    private String dbName;
}
