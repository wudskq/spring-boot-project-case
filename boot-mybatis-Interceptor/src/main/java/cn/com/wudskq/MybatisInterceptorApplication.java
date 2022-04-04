package cn.com.wudskq;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author chenfangchao
 * @title: MybatisInterceptorApplicatioon
 * @projectName boot-project
 * @description: TODO mybatis拦截器
 * @date 2022/4/4 5:39 AM
 */
@SpringBootApplication
@MapperScan("cn.com.wudskq.**.mapper")
public class MybatisInterceptorApplication {
    public static void main(String[] args) {
        SpringApplication.run(MybatisInterceptorApplication.class,args);
    }
}
