package cn.com.wudskq.interceptor;

import java.util.Map;

/**
 * @author chenfangchao
 * @title: ConvertParameter
 * @projectName boot-project
 * @description: TODO
 * @date 2022/4/5 12:25 AM
 */
public class ConvertParameter {

    //常量
    public static final String PARAM_DATA = "data";

    /**
     * 处理@Param注解读取参数获取失效问题
     */
    public static Object convertParameter(Object parameter) {
        final Object[] object = new Object[1];
        //判断获取到的mapper入参是否为集合类型
        if (parameter instanceof Map) {
            Map<String, Object> param = (Map<String, Object>) parameter;
            param.forEach((s, o) -> {
                if (s.equals(PARAM_DATA)) {
                    object[0] = o;
                }
            });
            return object[0];
        } else {
            return parameter;
        }
    }
}
