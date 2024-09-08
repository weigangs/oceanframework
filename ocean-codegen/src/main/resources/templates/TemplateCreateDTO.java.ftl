package ${packageName};

<#list importList as importItem>
import ${importItem};
</#list>

@ToString
@Data
public class ${className} {
    <#list entityModel.fieldList as field>
    @ApiModelProperty("${field.remark}")
    private ${field.javaFieldType} ${field.fieldName};
    </#list>
}