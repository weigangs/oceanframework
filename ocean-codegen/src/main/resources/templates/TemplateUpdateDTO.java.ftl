package ${packageName};

<#list importList as importItem>
import ${importItem};
</#list>

@ToString
@Data
public class ${className} {
    @NotNull(message = "主键ID不能为空")
    private Long id;
    <#list entityModel.fieldList as field>
    @ApiModelProperty("${field.remark}")
    private ${field.javaFieldType} ${field.fieldName};
    </#list>
}