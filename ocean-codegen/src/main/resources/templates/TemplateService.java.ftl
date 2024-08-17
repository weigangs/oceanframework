package ${packageName};

<#list importList as importItem>
import ${importItem};
</#list>

public interface ${className} {
    void create${entityModel.entityName}(${entityModel.entityName}CreateDTO  ${entityModel.entityName?uncap_first}CreateDto);

    void update${entityModel.entityName}(${entityModel.entityName}UpdateDTO  ${entityModel.entityName?uncap_first}UpdateDto);

    ${entityModel.entityName}DetailVO getDetailById(Long id);
}