package ${packageName};

<#list importList as importItem>
import ${importItem};
</#list>

@Component
public class ${className} {

    @Resource
    private ${entityModel.entityName}Mapper ${entityModel.entityName?uncap_first}Mapper;

    @PageSelector
    public List<${entityModel.entityName}> pageQuery${entityModel.entityName}List(${entityModel.entityName}Example example, ${entityModel.entityName}.Column ... selective) {
        return ${entityModel.entityName?uncap_first}Mapper.selectByExampleSelective(example, selective);
    }
}