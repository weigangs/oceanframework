package com.lkyl.oceanframework.codegen.database;

import com.lkyl.oceanframework.codegen.context.CodeGenContext;
import com.lkyl.oceanframework.codegen.model.DbTableColumn;
import com.lkyl.oceanframework.codegen.model.DbTableMetadata;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DatabaseIntrospector {

    private final DatabaseMetaData databaseMetaData;
    private final CodeGenContext codeGenContext;
    public DatabaseIntrospector(DatabaseMetaData metaData, CodeGenContext codeGenContext) {
        this.databaseMetaData = metaData;
        this.codeGenContext = codeGenContext;
    }

    public DbTableMetadata extractTableMetadata() throws SQLException {
        List<DbTableColumn> columnList = new ArrayList<>(20);
        ResultSet rs = this.databaseMetaData.getColumns(null, null, codeGenContext.getTableName(), "%");

        while(rs.next()) {
            DbTableColumn introspectedColumn = new DbTableColumn();
            introspectedColumn.setJdbcType(rs.getInt("DATA_TYPE"));
            introspectedColumn.setActualTypeName(rs.getString("TYPE_NAME"));
            introspectedColumn.setLength(rs.getInt("COLUMN_SIZE"));
            introspectedColumn.setActualColumnName(rs.getString("COLUMN_NAME"));
            introspectedColumn.setNullable(rs.getInt("NULLABLE") == 1);
            introspectedColumn.setScale(rs.getInt("DECIMAL_DIGITS"));
            introspectedColumn.setRemarks(rs.getString("REMARKS"));
            introspectedColumn.setDefaultValue(rs.getString("COLUMN_DEF"));

            columnList.add(introspectedColumn);
        }
        return new DbTableMetadata(codeGenContext.getTableName(), Collections.unmodifiableList(columnList));
    }
}
