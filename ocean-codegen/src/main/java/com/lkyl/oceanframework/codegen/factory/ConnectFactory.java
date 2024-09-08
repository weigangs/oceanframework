package com.lkyl.oceanframework.codegen.factory;

import com.lkyl.oceanframework.codegen.model.DatabaseConnModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectFactory {

    private ConnectFactory() {

    }

    public static Connection getConnection(DatabaseConnModel databaseConnModel) throws SQLException, ClassNotFoundException {
        Connection connection = null;

        try {
            // 1. 注册 JDBC 驱动程序 (可选, 新版的JDBC会自动加载驱动)
            Class.forName(databaseConnModel.getDriver());

            // 2. 打开连接
            System.out.println("Connecting to database...");
            connection = DriverManager.getConnection(databaseConnModel.getUrl(), databaseConnModel.getUsername(), databaseConnModel.getPassword());

            System.out.println("Connection established successfully!");

            // 3. 在这里你可以开始执行 SQL 查询或更新操作
        } catch (SQLException | ClassNotFoundException e) {
            throw e;
        }
        return connection;
    }
}
