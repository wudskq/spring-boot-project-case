package cn.com.wudskq.mapper;

import cn.com.wudskq.annotation.DataSource;
import cn.com.wudskq.enums.DataSourceType;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author chenfangchao
 * @title: DruidMapper
 * @projectName boot-project
 * @description: TODO
 * @date 2022/4/2 3:38 PM
 */
@Mapper
public interface DruidMapper {

    //测试主数据源
    @DataSource(DataSourceType.MASTER)
    String masterDateSource();

    //测试从数据源
    @DataSource(DataSourceType.SLAVE)
    String salve1DateSource();

    //测试从数据源
    @DataSource(DataSourceType.SLAVE1)
    String slave2DateSource();
}
