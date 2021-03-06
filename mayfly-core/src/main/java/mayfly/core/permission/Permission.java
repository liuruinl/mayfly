package mayfly.core.permission;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 权限注解，作用于方法或者类上 <br/><br/>
 * 如果只作用于类上，则该类下的所有方法都需要权限校验，
 * 如果方法没有该注解则方法对应的code为类注解中声明的code。反之为类注解code + 方法注解code <br/>
 * 如果仅作用于方法上且声明类上没有则含有该注解的方法需要权限验证，code即为方法注解code
 *
 * @author meilin.huang
 * @date 2018/6/28 下午3:27
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Permission {
    /**
     * 默认需用用户拥有指定的权限code（也就是需要对用户分配指定的权限code），才可执行。
     */
    boolean requireCode() default true;

    /**
     * 权限code
     */
    String code() default "";

    /**
     * 拥有该权限的角色
     */
    String[] roles() default {};
}
