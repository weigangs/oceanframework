package com.lkyl.oceanframework.codegen.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@ToString
@AllArgsConstructor
@Getter
public class DbTableMetadata {

    private String tableName;

    private List<DbTableColumn> columnList;
}
