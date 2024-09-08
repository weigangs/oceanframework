package com.lkyl.oceanframework.codegen.factory;


import com.lkyl.oceanframework.codegen.resolver.JavaTypeResolver;
import com.lkyl.oceanframework.codegen.resolver.JavaTypeResolverDefaultImpl;

public class JavaTypeResolverFactory {

    private static final JavaTypeResolver JAVA_TYPE_RESOLVER = new JavaTypeResolverDefaultImpl();
    private JavaTypeResolverFactory() {

    }

    public static JavaTypeResolver getJavaTypeResolver() {
        return JAVA_TYPE_RESOLVER;
    }
}
