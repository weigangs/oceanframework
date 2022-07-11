//package com.lkyl.oceanframework.log.test.spel;
//
//import com.lkyl.oceanframework.log.factory.ParseFunctionFactory;
//import com.lkyl.oceanframework.log.function.IParseFunction;
//import com.lkyl.oceanframework.log.spelExt.parser.LogRecordValueParser;
//import com.lkyl.oceanframework.log.test.spel.template.MyTemplateParser;
//import org.apache.commons.lang3.reflect.MethodUtils;
//import org.junit.Before;
//import org.junit.Test;
//import org.springframework.expression.EvaluationContext;
//import org.springframework.expression.Expression;
//import org.springframework.expression.ExpressionParser;
//import org.springframework.expression.common.CompositeStringExpression;
//import org.springframework.expression.common.LiteralExpression;
//import org.springframework.expression.common.TemplateParserContext;
//import org.springframework.expression.spel.ExpressionState;
//import org.springframework.expression.spel.SpelParserConfiguration;
//import org.springframework.expression.spel.ast.CompoundExpression;
//import org.springframework.expression.spel.ast.FunctionReference;
//import org.springframework.expression.spel.ast.MethodReference;
//import org.springframework.expression.spel.standard.SpelExpression;
//import org.springframework.expression.spel.standard.SpelExpressionParser;
//import org.springframework.expression.spel.support.SimpleEvaluationContext;
//import org.springframework.expression.spel.support.StandardEvaluationContext;
//import org.springframework.util.StringUtils;
//
//import java.lang.reflect.Method;
//import java.util.Date;
//import java.util.GregorianCalendar;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * TODO
// *
// * @version 1.0
// * @author: nicholas
// * @createTime: 2022年06月06日 22:24
// */
//public class SpelTest {
//
//    // 解析器
//    SpelExpressionParser parser;
//    // 评估上下文
//    SimpleEvaluationContext context;
//    StandardEvaluationContext standardEvaluationContext;
//    //
//    TemplateParserContext templateParserContext;
//
//    MyTemplateParser myTemplateParser;
//
//    @Before
//    public void before() {
//        parser = new SpelExpressionParser();
//        context = SimpleEvaluationContext.forReadOnlyDataBinding().build();
//        templateParserContext = new TemplateParserContext();
//        standardEvaluationContext = new StandardEvaluationContext();
//        myTemplateParser = new MyTemplateParser(new SpelParserConfiguration());
//    }
//
//    @Test
//    public void test() {
//
//        GregorianCalendar c = new GregorianCalendar();
//        c.set(1856, 7, 9);
//        Inventor tesla = new Inventor("1", "Nikola Tesla",  "Serbian", c.getTime());
//        // 1 定义解析器
//        ExpressionParser parser = new SpelExpressionParser();
//        String str = "T(com.lkyl.oceanframework.log.test.spel.TestUtil).toUpperCase(#tesla.getName())";
//        // 指定表达式
//        Expression exp = parser.parseExpression(str);
//        EvaluationContext context = new StandardEvaluationContext();
//        context.setVariable("tesla", tesla);
//        System.out.println(exp.getValue(context));
//    }
//
//    @Test
//    public void test2() {
//        // 1 定义解析器
//        SpelExpressionParser parser = new SpelExpressionParser();
//        // 2 使用解析器解析表达式
//        Expression exp = parser.parseExpression("'Hello World'.concat('!')");
//        // 3 获取解析结果
//        String value = (String) exp.getValue();
//        System.out.println(value);
//        exp = parser.parseExpression("'Hello World'.bytes");
//        byte[] bytes = (byte[]) exp.getValue();
//        exp = parser.parseExpression("'Hello World'.bytes.length");
//        int length = (Integer) exp.getValue();
//        System.out.println("length: " + length);
//
//        //  调用
//        exp = parser.parseExpression("new String('hello world').toUpperCase()");
//        System.out.println("大写: " + exp.getValue());
//    }
//
//    @Test
//    public void test3() {
//// 创建  Inventor 对象
//        GregorianCalendar c = new GregorianCalendar();
//        c.set(1856, 7, 9);
//        Inventor tesla = new Inventor("1", "Nikola Tesla",  "Serbian", c.getTime());
//        // 1 定义解析器
//        ExpressionParser parser = new SpelExpressionParser();
//        // 指定表达式
//        Expression exp = parser.parseExpression("name");
//        // 在 tesla对象上解析
//        String name = (String) exp.getValue(tesla);
//        System.out.println(name); // Nikola Tesla
//
//        exp = parser.parseExpression("'Nikola Tesla'.equals(name)");
//        // 在 tesla对象上解析并指定返回结果
//        boolean result = exp.getValue(tesla, Boolean.class);
//        System.out.println(result); // true
//    }
//
//    @Test
//    public void test4() {
//        String randomStr = parser.parseExpression("随机数字是： #{T(java.lang.Math).random()}", new TemplateParserContext())
//                .getValue(String.class);
//        System.out.println(randomStr);
//    }
//
//    @Test
//    public void test5() throws NoSuchMethodException {
//        // 注册 org.springframework.util.StringUtils.startsWithIgnoreCase(String str,String prefix)
//        Method method = StringUtils.class.getDeclaredMethod("startsWithIgnoreCase",String.class,String.class);
//
//        // 方式1 变量方式
//        SimpleEvaluationContext simpleEvaluationContext = SimpleEvaluationContext.forReadOnlyDataBinding().build();
//        simpleEvaluationContext.setVariable("startsWithIgnoreCase" ,method);
//        Boolean startWith = parser.parseExpression("#startsWithIgnoreCase('123', '111')").getValue(simpleEvaluationContext,
//                Boolean.class);
//        System.out.println("方式1: " + startWith);
//
//        // 方式2 明确方法方式
//        StandardEvaluationContext standardEvaluationContext = new StandardEvaluationContext();
//        standardEvaluationContext.registerFunction("startsWithIgnoreCase" ,method);
//        Boolean startWit2 =
//                parser.parseExpression("#startsWithIgnoreCase('123', '111')").getValue(simpleEvaluationContext,
//                        Boolean.class);
//        System.out.println("方式2: " + startWit2);
//    }
//
//    @Test
//    public void test6() {
//        String str = "hello world to Upper #{#functionTest('hello world')}";
//        FunctionTest functionTest = new FunctionTestImpl();
//        Expression expression = parser.parseExpression(str, templateParserContext);
//        if(expression instanceof CompositeStringExpression) {
//            Expression [] exprs = ((CompositeStringExpression) expression).getExpressions();
//            StringBuilder stringBuilder = new StringBuilder();
//            for(Expression ex : exprs)
//            {
//                if(ex instanceof LiteralExpression) {
//                    stringBuilder.append(((LiteralExpression) ex).getExpressionString());
//                }else if(ex instanceof SpelExpression) {
//                    if(((SpelExpression) ex).getAST() instanceof FunctionReference) {
//                        ((SpelExpression) ex).setEvaluationContext(standardEvaluationContext);
//                        ((SpelExpression) ex).getAST();
//
////                        stringBuilder.append(functionReference.getValue(state));
//                    }
//                }
//                else {
//                    stringBuilder.append(ex.getClass());
//                }
//            }
//            System.out.println(stringBuilder.toString());
//        }
//    }
//
//    @Test
//    public void test7() {
//        String str = "恭喜！#{@{getName(#entity.getId())}}同学今天已经累计打卡#{#entity.getContinuationNum()}，连续打卡#{#entity.getTotalNum()}";
//
////        templateParserContext = new TemplateParserContext("{", "}");
//        Expression expression = myTemplateParser.parseExpression(str, templateParserContext);
//        Inventor inventor = new Inventor("1", "nicholas", "china", new Date());
//        standardEvaluationContext.setVariable("getName", MethodUtils.getAccessibleMethod(TestUtil.class, "getName", String.class));
//        standardEvaluationContext.setVariable("entity", inventor);
//        standardEvaluationContext.setRootObject(inventor);
//        Map<String, IParseFunction> functionMap = new HashMap<>();
//        IParseFunction name = new IParseFunction() {
//            @Override
//            public String functionName() {
//                return "getName";
//            }
//
//            @Override
//            public String apply(String value) {
//                return "小明";
//            }
//        };
//        functionMap.put(name.functionName(), name);
//        standardEvaluationContext.setVariable("functionMap", functionMap);
////        ExpressionState state = new ExpressionState(standardEvaluationContext);
//        StringBuilder stringBuilder = new StringBuilder();
//        if(expression instanceof CompositeStringExpression) {
//            for(Expression exp : ((CompositeStringExpression) expression).getExpressions()) {
//
//                stringBuilder.append(exp.getValue(standardEvaluationContext));
//            }
//        }
//        System.out.println(stringBuilder);
//
//    }
//
//    @Test
//    public void test8() {
//
//    }
//}
