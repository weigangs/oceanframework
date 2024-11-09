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
        ${entityModel.entityName?uncap_first}Service.update${entityModel.entityName}(${entityModel.entityName?uncap_first}UpdateDto);
        return CommonResult.ok();
    }

    @GetMapping("/detail/{id}")
    public CommonResult<${entityModel.entityName}DetailVO> getDetailById(@PathVariable("id") Long id) {
        return CommonResult.ok(${entityModel.entityName?uncap_first}Service.getDetailById(id));
    }

    @GetMapping("/pageQuery")
    public PageResult<${entityModel.entityName}DetailVO> pageQuery${entityModel.entityName}List(${entityModel.entityName}PageQueryDTO ${entityModel.entityName?uncap_first}PageQueryDTO) {
        return PageResult.page(${entityModel.entityName?uncap_first}Service.pageQuery${entityModel.entityName}List(${entityModel.entityName?uncap_first}PageQueryDTO));
    }

    @PostMapping("/delete/{id}")
    public CommonResult<String> delete${entityModel.entityName}(@PathVariable("id") Long id) {
        ${entityModel.entityName?uncap_first}Service.delete${entityModel.entityName}(id);
        return CommonResult.ok();
    }

}