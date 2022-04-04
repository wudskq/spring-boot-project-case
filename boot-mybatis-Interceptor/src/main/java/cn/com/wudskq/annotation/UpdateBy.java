package cn.com.wudskq.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author chenfangchao
 * @projectName boot-project
 * @description: TODO 解密result
 * @date 2022/4/5 12:25 AM
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface UpdateBy {
    String value() default "";
}
