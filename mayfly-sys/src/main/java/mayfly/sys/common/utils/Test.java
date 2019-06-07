package mayfly.sys.common.utils;

import mayfly.common.log.MethodLog;
import mayfly.common.utils.annotation.Alias;
import mayfly.common.utils.annotation.AnnotationUtils;
import mayfly.common.validation.annotation.DateRange;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author meilin.huang
 * @version 1.0
 * @date 2019-05-09 11:19
 */
@MethodLog
@Test5Ann(test4 = "测试5蛤")
public class Test {

    public static void main(String[] args) {
//        long start = System.currentTimeMillis();
//        for (int i = 0; i < 1000000; i++) {
////            Test4Ann anno = AnnotationUtils.getAnnotation(Test.class, Test4Ann.class);
////            TestAnn anno = AnnotatedElementUtils.getMergedAnnotation(Test.class, TestAnn.class);
////            AnnotatedElementUtils.getMergedAnnotation(Test.class, Test3Ann.class);
//            AnnotationUtils.getAnnotation(Test.class, Test3Ann.class);
//        }
//        long end = System.currentTimeMillis();
//        System.out.println(end - start);
//        Test2Ann anno2 = AnnotationUtils.getAnnotation(Test.class, Test2Ann.class);
//        Test3Ann anno3 = AnnotationUtils.getAnnotation(Test.class, Test3Ann.class);
//        Test4Ann anno4 = AnnotationUtils.getAnnotation(Test.class, Test4Ann.class);
//        Test5Ann anno5 = AnnotationUtils.getAnnotation(Test.class, Test5Ann.class);
//        System.out.println(AnnotationUtils.getValue(anno5, "test5"));
//        System.out.println(pattern.regexp());
//        System.out.println(AnnotatedElementUtils.getMergedAnnotation(Test.class, Test3Ann.class).test3());
        System.out.println(AnnotationUtils.getAnnotation(Test.class, Test4Ann.class).test4());
    }
}

@DateRange
@Documented
@Target({ ANNOTATION_TYPE, FIELD, TYPE })
@Retention(RUNTIME)
@interface TestAnn {
    String test1() default "test1";

    String test() default "test";
}

@DateRange
@Documented
@Target({ ANNOTATION_TYPE, FIELD, TYPE })
@Retention(RUNTIME)
@TestAnn
@interface Test2Ann {
    String test2() default "test2";
}

@DateRange
@MethodLog
@Documented
@Target({ ANNOTATION_TYPE, FIELD, TYPE })
@Retention(RUNTIME)
@Test2Ann
@interface Test3Ann {
    String test3() default "test3";

    String test32() default "test32";

    String test33() default "test33";
}

@DateRange
@MethodLog
@Documented
@Target({ ANNOTATION_TYPE, FIELD, TYPE })
@Retention(RUNTIME)
@Test3Ann
@interface Test4Ann {
    @Alias(attribute = "test3", annotation = Test3Ann.class)
    @AliasFor(attribute = "test3", annotation = Test3Ann.class)
    String test4() default "test4";


    @Alias(annotation = Test3Ann.class, attribute = "test32")
    String test42() default "test42";
}

@DateRange
@MethodLog
@Documented
@Target({ ANNOTATION_TYPE, FIELD, TYPE })
@Retention(RUNTIME)
@Test4Ann(test42 = "hahahah42")
@interface Test5Ann {
    @Alias(annotation = Test4Ann.class)
    String test4() default "test5";
}