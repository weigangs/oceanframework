package ${packageName};

<#list importList as importItem>
import ${importItem};
</#list>

@Service
public class ${className} implements ${entityModel.entityName}Service {

    @Resource
    private ${entityModel.entityName}Mapper ${entityModel.entityName?uncap_first}Mapper;

    @Resource
    private ${entityModel.entityName}QueryComponent ${entityModel.entityName?uncap_first}QueryComponent;

    @Override
    public void create${entityModel.entityName}(${entityModel.entityName}CreateDTO  ${entityModel.entityName?uncap_first}CreateDto) {
        ${entityModel.entityName} insertEntity = buildInsertEntity(${entityModel.entityName?uncap_first}CreateDto);
        ${entityModel.entityName?uncap_first}Mapper.insert(insertEntity);
    }

    private ${entityModel.entityName} buildInsertEntity(${entityModel.entityName}CreateDTO ${entityModel.entityName?uncap_first}CreateDto) {
        ${entityModel.entityName} result = new ${entityModel.entityName}();
        <#list entityModel.createFieldList as field>
        result.set${field.fieldName?cap_first}(<#if field.defaultValue?has_content>${field.defaultValue}<#else>${entityModel.entityName?uncap_first}CreateDto.get${field.fieldName?cap_first}()</#if>);
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
        result.set${field.fieldName?cap_first}(${entityModel.entityName?uncap_first}UpdateDto.get${field.fieldName?cap_first}());
        </#list>
        result.setUpdatedTime(LocalDateTime.now());
        result.setUpdatedBy(UserContext.getUser().getUserCode());
        return result;
    }

    @Override
    public ${entityModel.entityName}DetailVO getDetailById(Long paramId) {
        ${entityModel.entityName}Example example = new ${entityModel.entityName}Example();
        example.createCriteria().andIdEqualTo(paramId).andIsDeletedEqualTo(YesOrNoEnum.NO.getCode());

        ${entityModel.entityName} result = ${entityModel.entityName?uncap_first}Mapper.selectOneByExample(example);
        if (Objects.isNull(result)) {
            throw BusinessExceptionFactory.getException(new BusinessException(20001, "record not found!"));
        }
        return ${entityModel.entityName}Converter.INSTANCE.to(result);
    }

    @Override
    public List<${entityModel.entityName}DetailVO> pageQuery${entityModel.entityName}List(${entityModel.entityName}PageQueryDTO ${entityModel.entityName?uncap_first}PageQueryDTO) {
        List<${entityModel.entityName}> pageResultList = buildPageQueryExample(${entityModel.entityName?uncap_first}PageQueryDTO)
            .map(e -> ${entityModel.entityName?uncap_first}QueryComponent.pageQuery${entityModel.entityName}List(e))
            .orElseGet(Collections::emptyList);
        return PageCopyUtils.convertProperties(pageResultList, ${entityModel.entityName}Converter.INSTANCE::to, null);
    }

    private Optional<${entityModel.entityName}Example> buildPageQueryExample(${entityModel.entityName}PageQueryDTO ${entityModel.entityName?uncap_first}PageQueryDTO) {
        ${entityModel.entityName}Example example = new ${entityModel.entityName}Example();
        ${entityModel.entityName}Example.Criteria criteria = example.createCriteria();
        criteria.andIsDeletedEqualTo(YesOrNoEnum.NO.getCode());
        <#list entityModel.updateFieldList as field>
        if (<#if field.javaFieldType == "String">StringUtils.isNotBlank(${entityModel.entityName?uncap_first}PageQueryDTO.get${field.fieldName?cap_first}())<#else>Objects.nonNull(${entityModel.entityName?uncap_first}PageQueryDTO.get${field.fieldName?cap_first}())</#if>) {
            criteria.and${field.fieldName?cap_first}EqualTo(${entityModel.entityName?uncap_first}PageQueryDTO.get${field.fieldName?cap_first}());
        }
        </#list>
        return Optional.of(example);
    }

    @Override
    public void delete${entityModel.entityName}(Long paramId) {
        ${entityModel.entityName} logicDelRecord = new ${entityModel.entityName}();
        logicDelRecord.setUpdatedTime(LocalDateTime.now());
        logicDelRecord.setUpdatedBy(UserContext.getUser().getUserCode());
        logicDelRecord.setIsDeleted(YesOrNoEnum.YES.getCode());

        ${entityModel.entityName}Example example = new ${entityModel.entityName}Example();
        example.createCriteria().andIdEqualTo(paramId).andIsDeletedEqualTo(YesOrNoEnum.NO.getCode());

        ${entityModel.entityName?uncap_first}Mapper.updateByExampleSelective(logicDelRecord, example);
    }

}