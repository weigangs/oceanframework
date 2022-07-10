package com.lkyl.oceanframework.log.test.spel;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

@Configuration
@ComponentScan
public class BeanReferencesTest {
    // 注入一个bean
    @Component("myService")
    static class MyService{
        private String text;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(BeanReferencesTest.class);
        SpelExpressionParser parser = new SpelExpressionParser();
        // 使用 StandardEvaluationContext
        StandardEvaluationContext standardEvaluationContext = new StandardEvaluationContext();
        // 需要注入一个BeanResolver来解析bean引用，此处注入 BeanFactoryResolver
        standardEvaluationContext.setBeanResolver(new BeanFactoryResolver(applicationContext));
        // 使用 @ 来引用bean
        MyService myService = parser.parseExpression("@myService").getValue(standardEvaluationContext, MyService.class);
        System.out.println(myService);
        myService.setText("hello");
        System.out.println(parser.parseExpression("@myService.text").getValue(standardEvaluationContext, String.class));
    }
}
