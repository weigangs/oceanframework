package ${packageName};

<#list importList as importItem>
import ${importItem};
</#list>

@RequestMapping("/${entityModel.entityName?uncap_first}")
@RestController
public class ${className} {

    @Resource
    private ${entityModel.entityName}Service ${entityModel.entityName?uncap_first}Service;

    @PostMapping("/create")
    public CommonResult<String> create${entityModel.entityName}(@RequestBody @Valid ${entityModel.entityName}CreateDTO ${entityModel.entityName?uncap_first}CreateDto) {
        ${entityModel.entityName?uncap_first}Service.create${entityModel.entityName}(${entityModel.entityName?uncap_first}CreateDto);
        return CommonResult.ok();
    }

    @PostMapping("/update")
    public CommonResult<String> update${entityModel.entityName}(@RequestBody @Valid ${entityModel.entityName}UpdateDTO ${entityModel.entityName?uncap_first}UpdateDto) {
        ${entityModel.entityName?uncap_first}Service.create${entityModel.entityName}(${entityModel.entityName?uncap_first}UpdateDto);
        return CommonResult.ok();
    }
}