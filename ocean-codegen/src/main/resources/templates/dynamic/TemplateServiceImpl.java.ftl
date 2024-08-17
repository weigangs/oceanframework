package ${packageName};

<#list importList as importItem>
import ${importItem};
</#list>

@Service
public class ${className} implements ${entityModel.entityName}Service {

    @Resource
    private ${entityModel.entityName}Mapper ${entityModel.entityName?uncap_first}Mapper;

    @Override
    public void create${entityModel.entityName}(${entityModel.entityName}CreateDTO  ${entityModel.entityName?uncap_first}CreateDto) {
        ${entityModel.entityName} insertEntity = buildInsertEntity(${entityModel.entityName?uncap_first}CreateDto);
        ${entityModel.entityName?uncap_first}Mapper.insert(insertEntity);
    }

    private ${entityModel.entityName} buildInsertEntity(${entityModel.entityName}CreateDTO ${entityModel.entityName?uncap_first}CreateDto) {
        ${entityModel.entityName} result = new ${entityModel.entityName}();
        <#list entityModel.createFieldList as field>
        result.set<#if field.javaFieldType == "Boolean" || field.javaFieldType == "boolean">Is</#if>${field.fieldName?cap_first}(<#if field.defaultValue?has_content>${field.defaultValue}<#else>${entityModel.entityName?uncap_first}CreateDto.get${field.fieldName?cap_first}()</#if>);
        </#list>
        return result;
    }

    @Override
    public void update${entityModel.entityName}(${entityModel.entityName}UpdateDTO  ${entityModel.entityName?uncap_first}UpdateDto) {
        ${entityModel.entityName} updateRecord = buildUpdateRecord(${entityModel.entityName?uncap_first}UpdateDto);
        ${entityModel.entityName?uncap_first}Mapper.updateByPrimaryKeySelective(updateRecord);
    }

        private ${entityModel.entityName} buildUpdateRecord(${entityModel.entityName}UpdateDTO ${entityModel.entityName?uncap_first}UpdateDto) {
            ${entityModel.entityName} result = new ${entityModel.entityName}();
            result.setId(${entityModel.entityName?uncap_first}UpdateDto.getId());
            <#list entityModel.updateFieldList as field>
            result.set<#if field.javaFieldType == "Boolean" || field.javaFieldType == "boolean">Is</#if>${field.fieldName?cap_first}(${entityModel.entityName?uncap_first}UpdateDto.get${field.fieldName?cap_first}());
            </#list>
            result.setUpdatedTime(LocalDateTime.now());
            result.setUpdatedBy(UserContext.getUser().getUserCode());
            return result;
        }
}