package ${packageName};

<#list importList as importItem>
import ${importItem};
</#list>

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE

)
public interface ${className} extends BaseMapperConverter<${entityModel.entityName}, ${entityModel.entityName}DetailVO> {
    ${className} INSTANCE = Mappers.getMapper(${className}.class);
}