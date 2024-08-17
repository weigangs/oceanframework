package com.lkyl.oceanframework.codegen.tools;

import com.lkyl.oceanframework.codegen.dom.FullyQualifiedJavaType;
import com.lkyl.oceanframework.codegen.config.YamlConfigProperties;
import com.lkyl.oceanframework.codegen.config.RootConfig;
import com.lkyl.oceanframework.codegen.context.CodeGenContext;
import com.lkyl.oceanframework.codegen.database.DatabaseIntrospector;
import com.lkyl.oceanframework.codegen.factory.ConnectFactory;
import com.lkyl.oceanframework.codegen.factory.FileGeneratorFactory;
import com.lkyl.oceanframework.codegen.factory.JavaTypeResolverFactory;
import com.lkyl.oceanframework.codegen.generator.FileGenerator;
import com.lkyl.oceanframework.codegen.model.DatabaseConnModel;
import com.lkyl.oceanframework.codegen.model.DbTableColumn;
import com.lkyl.oceanframework.codegen.model.DbTableMetadata;
import com.lkyl.oceanframework.codegen.model.freemarker.ConcreteJavaEntityModel;
import com.lkyl.oceanframework.codegen.model.freemarker.ConcreteJavaFieldModel;
import com.lkyl.oceanframework.codegen.utils.StringUtils;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OceanCodeGenerator {

    public static void generateFiles(RootConfig config) throws SQLException, ClassNotFoundException {
        config.parseYamlConfig();
        config.processAfterRamlConfigLoaded();
        CodeGenContext codeGenContext = buildContext();
        FileGeneratorFactory fileGeneratorFactory = FileGeneratorFactory.getFactory();
        HashSet needGenerateTypeSet = new HashSet<>(
                Optional.ofNullable(YamlConfigProperties.getYmlProperty("file.generation.type", List.class))
                        .orElseGet(Collections::emptyList)
        );
        Stream.of(
                fileGeneratorFactory.generateCreateDto(),
                fileGeneratorFactory.generateUpdateDto(),
                fileGeneratorFactory.generatePageQueryDto(),
                fileGeneratorFactory.generateDetailVo(),
                fileGeneratorFactory.generateService(),
                fileGeneratorFactory.generateServiceImpl(),
                fileGeneratorFactory.generateConverter(),
                fileGeneratorFactory.generateController(),
                fileGeneratorFactory.generateQueryComponent(),
                fileGeneratorFactory.generateMybatisBaseFiles()
        ).filter(Objects::nonNull).filter(e -> needGenerateTypeSet.contains(e.getFileType().getType()))
                .forEach(fileGenerator -> fileGenerator.generateFile(codeGenContext));
    }

    private static CodeGenContext buildContext() throws SQLException, ClassNotFoundException {
        CodeGenContext result = new CodeGenContext();

        DatabaseConnModel connModel = new DatabaseConnModel();
        connModel.setUrl(YamlConfigProperties.getStringProperty("database.url"));
        connModel.setDriver(YamlConfigProperties.getStringProperty("database.driverName"));
        connModel.setUsername(YamlConfigProperties.getStringProperty("database.username"));
        connModel.setPassword(YamlConfigProperties.getStringProperty("database.password"));
        result.setDatabaseConnModel(connModel);

        result.setTableName(YamlConfigProperties.getStringProperty("tableName"));
        result.setDbTableMetadata(getDbTableMetadata(result));
        result.setConcreteJavaEntityModel(convertTableMetadata2JavaEntityModel(result.getDbTableMetadata()));
        result.setProjectPath(new File("").getAbsolutePath());
        return result;
    }

    private static DbTableMetadata getDbTableMetadata(CodeGenContext context) throws SQLException, ClassNotFoundException {



        Connection connection = ConnectFactory.getConnection(context.getDatabaseConnModel());
        DbTableMetadata result = new DatabaseIntrospector(connection.getMetaData(), context).extractTableMetadata();
        if (!connection.isClosed()) {
            connection.close();
            System.out.println("Connection closed.");
        }
        return result;
    }

    private static ConcreteJavaEntityModel convertTableMetadata2JavaEntityModel(DbTableMetadata tableMetadata) {
        return new ConcreteJavaEntityModel(StringUtils.getCamelCaseString(tableMetadata.getTableName(), true),
                tableMetadata.getColumnList().stream().map(OceanCodeGenerator::convertTableColumn2JavaField).collect(Collectors.toUnmodifiableList()));
    }

    private static ConcreteJavaFieldModel convertTableColumn2JavaField(DbTableColumn column) {
        FullyQualifiedJavaType fullyQualifiedJavaType = JavaTypeResolverFactory.getJavaTypeResolver().calculateJavaType(column);
        return new ConcreteJavaFieldModel(
                fullyQualifiedJavaType.getShortName(),
                fullyQualifiedJavaType.getFullyQualifiedName(),
                StringUtils.getCamelCaseString(column.getActualColumnName(), false),
                column.isNullable(),
                column.getDefaultValue(),
                column.getRemarks()
        );

    }
}
