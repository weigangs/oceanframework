package com.lkyl.oceanframework.codegen.model;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class DbTableColumn {

    protected String actualColumnName;
    protected int jdbcType;
    protected String actualTypeName;
    protected String jdbcTypeName;
    protected boolean nullable;
    protected int length;
    protected int scale;
    protected boolean identity;
    protected boolean isSequenceColumn;
    protected String javaProperty;
    protected String tableAlias;
    protected String typeHandler;
    protected boolean isColumnNameDelimited;
    protected String remarks;
    protected String defaultValue;
    protected boolean isAutoIncrement;
    protected boolean isGeneratedColumn;
    protected boolean isGeneratedAlways;
}
