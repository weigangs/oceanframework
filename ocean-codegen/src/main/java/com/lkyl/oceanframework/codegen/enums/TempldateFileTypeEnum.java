package com.lkyl.oceanframework.codegen.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TempldateFileTypeEnum {
    CREATE_DTO("dto", "TemplateCreateDTO.java.ftl"),
    PAGE_QUERY_DTO("dto", "TemplatePageQueryDTO.java.ftl"),
    UPDATE_DTO("dto", "TemplateUpdateDTO.java.ftl"),
    DETAIL_VO("vo", "TemplateDetailVO.java.ftl"),
    SERVICE("service", "TemplateService.java.ftl"),
    SERVICE_IMPL("serviceImpl", "TemplateServiceImpl.java.ftl"),
    DYNAMIC_SERVICE_IMPL("serviceImpl", "dynamic/TemplateServiceImpl.java.ftl"),
    CONVERTER("converter", "TemplateConverter.java.ftl"),
    CONTROLLER("controller", "TemplateController.java.ftl"),
    QUERY_COMPONENT("queryComponent", "TemplateQueryComponent.java.ftl"),
    MYBATIS_BASE("mybatisBase", "");

    private final String type;

    private final String fileName;
}
