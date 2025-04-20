package ${packageName};

<#list importList as importItem>
import ${importItem};
</#list>

@ToString
@Data
public class ${className} {
    <#list entityModel.fieldList as field>
    @ApiModelProperty("${field.remark}")
    <#if field.javaFieldType == "Long" || field.javaFieldType == "long">
    @JsonSerialize(using = ToStringSerializer.class)
    private ${field.javaFieldType} ${field.fieldName};
    <#else>
    private ${field.javaFieldType} ${field.fieldName};
    </#if>
    </#list>
}
