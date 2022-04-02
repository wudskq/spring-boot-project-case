package cn.com.wudskq;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author chenfangchao
 * @title: cn.com.wudskq.BootDataSourceApplication
 * @projectName boot-project
 * @description: TODO boot配置多数据源
 * @date 2022/4/2 11:13 AM
 */
//排除数据源自动配置
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@MapperScan("cn.com.wudskq.**.mapper")
public class BootDataSourceApplication {
    public static void main(String[] args) {
        SpringApplication.run(BootDataSourceApplication.class,args);
    }
}
