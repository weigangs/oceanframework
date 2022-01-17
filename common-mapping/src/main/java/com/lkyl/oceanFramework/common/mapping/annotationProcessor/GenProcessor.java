package com.lkyl.oceanFramework.common.mapping.annotationProcessor;

import com.google.auto.service.AutoService;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@AutoService(Processor.class)
@SupportedAnnotationTypes("com.lkyl.oceanFramework.common.mapping.annotation.Gen")
public class GenProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for(TypeElement element : annotations){
            Set<? extends Element> annotationEles = roundEnv.getElementsAnnotatedWith(element);
            List<Element> interfaceEles = annotationEles.stream().filter(e -> ((Element) e).getAnnotationsByType(Interface)));
        }
        return true;
    }
}
