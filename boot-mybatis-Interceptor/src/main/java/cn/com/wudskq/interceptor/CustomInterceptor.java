package cn.com.wudskq.interceptor;

import cn.com.wudskq.annotation.CreateBy;
import cn.com.wudskq.annotation.CreateTime;
import cn.com.wudskq.annotation.UpdateBy;
import cn.com.wudskq.annotation.UpdateTime;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.*;

/**
 * 自定义 Mybatis 插件，自动设置 createTime 和 updatTime 的值。
 * 拦截 update 操作（添加和修改）
 */
// 不能使用xml配置文件，因为会和其他mybatis的配置冲突，因此添加 @Component
@Component
@Intercepts({@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})})
public class CustomInterceptor implements Interceptor {

    private static final Logger logger = LoggerFactory.getLogger(CustomInterceptor.class);

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        // 获取 SQL 命令
        SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();
        // 获取参数
        Object parameter = invocation.getArgs()[1];

        /**
         * 处理mapper.xml入参为多个
         * data 约定为DDL映射实体类
         * param1统一标注为@Param("data")
         */
        parameter = ConvertParameter.convertParameter(parameter);
        //存储属性
        ArrayList<Field> declaredFields = new ArrayList<>();
        //当前class私有属性
        declaredFields.addAll(Arrays.asList(parameter.getClass().getDeclaredFields()));
        //当前class父类私有属性
        declaredFields.addAll(Arrays.asList(parameter.getClass().getSuperclass().getDeclaredFields()));

        //抽离的业务逻辑
        for (Field field : declaredFields) {
            // insert 语句插入 createTime
            if (field.getAnnotation(CreateTime.class) != null) {
                if (SqlCommandType.INSERT.equals(sqlCommandType)) {
                    field.setAccessible(true);
                    field.set(parameter,new Date());
                }
            }
            // update 语句插入 updateTime
            if (field.getAnnotation(UpdateTime.class) != null) {
                if (SqlCommandType.UPDATE.equals(sqlCommandType)) {
                    field.setAccessible(true);
                    field.set(parameter,new Date());
                }
            }
            //TODO正常业务下当前操作用户应从token中解析并获取
            // insert 语句插入 createBy
            if (field.getAnnotation(CreateBy.class) != null) {
                if (SqlCommandType.INSERT.equals(sqlCommandType)) {
                    field.setAccessible(true);
                    field.set(parameter, "admin01");
                }
            }
            // update 语句插入 updateBy
            if (field.getAnnotation(UpdateBy.class) != null) {
                if (SqlCommandType.UPDATE.equals(sqlCommandType)) {
                    field.setAccessible(true);
                    field.set(parameter, "admin02");
                }
            }
        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object o) {
        return Plugin.wrap(o, this);
    }

    @Override
    public void setProperties(Properties properties) {
        logger.warn(properties.toString());
    }

}