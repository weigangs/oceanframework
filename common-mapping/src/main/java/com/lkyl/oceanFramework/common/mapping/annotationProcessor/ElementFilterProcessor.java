//package com.lkyl.oceanframework.common.mapping.annotationProcessor;
//
//
//import javax.annotation.processing.AbstractProcessor;
//import javax.annotation.processing.RoundEnvironment;
//import javax.annotation.processing.SupportedAnnotationTypes;
//import javax.annotation.processing.SupportedSourceVersion;
//import javax.lang.model.SourceVersion;
//import javax.lang.model.element.Element;
//import javax.lang.model.element.TypeElement;
//import javax.tools.JavaCompiler;
//import javax.tools.ToolProvider;
//import java.lang.annotation.Retention;
//import java.lang.annotation.RetentionPolicy;
//import java.util.*;
//
//@SupportedSourceVersion(SourceVersion.RELEASE_8)
//@SupportedAnnotationTypes("*")
//public class ElementFilterProcessor extends AbstractProcessor {
//
//    @Retention(RetentionPolicy.RUNTIME)
//    public static @interface JsonObject {}
//
//    @Retention(RetentionPolicy.RUNTIME)
//    public static @interface JsonField { String value(); }
//
//    @JsonObject
//    public class Name {
//        @JsonField("last") public  String last;
//        @JsonField("first") public String first;
//    }
//
//    @JsonObject
//    public class User {
//        @JsonField("age") public int age;
//        @JsonField("name") public String name;
//        @JsonField("sex") public boolean sex;
//    }
//
//    public static void main(String[] args) {
//        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
//        final JavaCompiler.CompilationTask task = compiler.getTask(
//                null,
//                null,
//                null,
//                null,
//                Collections.singleton(ElementFilterProcessor.class.getName()),
//                Collections.EMPTY_SET);
//        task.setProcessors(Collections.singleton(new ElementFilterProcessor()));
//        task.call();
//    }
//
//    @Override
//    public boolean process(
//            final Set<? extends TypeElement> annotations, final RoundEnvironment roundEnv) {
//        if (roundEnv.processingOver()) {
//            return true;
//        }
//        final Set<? extends Element> jsonFields =
//                roundEnv.getElementsAnnotatedWith(JsonField.class);
//        final Map<Element, List<Element>> jsonObjects = new HashMap<>();
//        for (final Element element : jsonFields) {
//            final Element classElement = element.getEnclosingElement();
//            if (classElement.getAnnotation(JsonObject.class) != null) {
//                List<Element> list = jsonObjects.get(classElement);
//                if (list == null) {
//                    list = new ArrayList<>();
//                    jsonObjects.put(classElement, list);
//                }
//                list.add(element);
//            }
//        }
//        System.out.println(jsonObjects);
//        return false;
//    }
//}
