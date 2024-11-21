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

    @Override
    public ${entityModel.entityName}DetailVO getDetailById(Long paramId) {
        return ${entityModel.entityName?uncap_first}Mapper.selectOne(
                                            c -> c.where(id, SqlBuilder.isEqualTo(paramId))
                                                    .and(isDeleted, SqlBuilder.isEqualTo(YesOrNoEnum.NO.getCode()))
               ).map(${entityModel.entityName}Converter.INSTANCE::to)
               .orElseThrow(() -> new BusinessException(new BusinessException(20001, "record not found!")));
    }

    @Override
    public List<${entityModel.entityName}DetailVO> pageQuery${entityModel.entityName}List(${entityModel.entityName}PageQueryDTO ${entityModel.entityName?uncap_first}PageQueryDTO, PageArgs pageArgs) {
        List<${entityModel.entityName}> pageResultList = buildSelectProvider(${entityModel.entityName?uncap_first}PageQueryDTO)
            .map(e -> ${entityModel.entityName?uncap_first}QueryComponent.pageQuery${entityModel.entityName}List(e))
            .orElseGet(Collections::emptyList);
        return PageCopyUtils.convertProperties(pageResultList, ${entityModel.entityName}Converter.INSTANCE::to, null);
    }

    private Optional<SelectStatementProvider> buildSelectProvider(${entityModel.entityName}PageQueryDTO ${entityModel.entityName?uncap_first}PageQueryDTO) {
        QueryExpressionDSL<SelectModel>.QueryExpressionWhereBuilder where = SqlBuilder.select(${entityModel.entityName?uncap_first}.allColumns())
                .from(${entityModel.entityName?uncap_first}).where().and(isDeleted, SqlBuilder.isEqualTo(YesOrNoEnum.NO.getCode()));
        <#list entityModel.updateFieldList as field>
        if (<#if field.javaFieldType == "String">StringUtils.isNotBlank(${entityModel.entityName?uncap_first}PageQueryDTO.get${field.fieldName?cap_first}())<#else>Objects.nonNull(${entityModel.entityName?uncap_first}PageQueryDTO.get${field.fieldName?cap_first}())</#if>) {
            where.and(${field.fieldName}, SqlBuilder.isEqualTo(${entityModel.entityName?uncap_first}PageQueryDTO.get${field.fieldName?cap_first}()));
        }
        </#list>
        return Optional.of(where.build().render(RenderingStrategies.MYBATIS3));
    }

    @Override
    public void delete${entityModel.entityName}(Long paramId) {
        UpdateStatementProvider updateStatementProvider = SqlBuilder.update(${entityModel.entityName?uncap_first}).set(isDeleted)
                    .equalTo(YesOrNoEnum.YES.getCode()).set(updatedTime).equalTo(LocalDateTime.now())
                    .where().and(id, SqlBuilder.isEqualTo(0L)).and(isDeleted, SqlBuilder.isEqualTo(YesOrNoEnum.NO.getCode()))
                    .build().render(RenderingStrategies.MYBATIS3);
        ${entityModel.entityName?uncap_first}Mapper.update(updateStatementProvider);
    }
}