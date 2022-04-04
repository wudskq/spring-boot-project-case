package cn.com.wudskq.annotation;

import java.lang.annotation.*;

/**
 * 注解敏感信息类中敏感字段的注解  
 */  
@Inherited
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface SensitiveField {  
}  