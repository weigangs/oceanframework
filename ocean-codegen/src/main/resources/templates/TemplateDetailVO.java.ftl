package ${packageName};

<#list importList as importItem>
import ${importItem};
</#list>

@ToString
@Data
public class ${className} {
    <#list entityModel.fieldList as field>
    @ApiModelProperty("${field.remark}")
    <#if field.javaFieldType == "Boolean" || field.javaFieldType == "boolean">@JsonSerialize(using = ToStringSerializer.class)<#if>
    private ${field.javaFieldType} ${field.fieldName};
    </#list>
}
