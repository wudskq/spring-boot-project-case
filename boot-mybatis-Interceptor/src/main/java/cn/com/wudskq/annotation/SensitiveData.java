package cn.com.wudskq.annotation;

import java.lang.annotation.*;

/**
 * 注解敏感信息类的注解  
 */  
@Inherited
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface SensitiveData {  
}  