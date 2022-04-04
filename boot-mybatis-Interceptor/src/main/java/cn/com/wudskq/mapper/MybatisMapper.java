package cn.com.wudskq.mapper;

import cn.com.wudskq.model.Plan;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author chenfangchao
 * @title: MybatisMapper
 * @projectName boot-project
 * @description: TODO
 * @date 2022/4/4 5:47 AM
 */
@Mapper
public interface MybatisMapper {

    //单个对象
    void insert(Plan plan);

    //多个参数
    void insert1(@Param("data") Plan plan,@Param("remark") String remark);

    void update(Plan plan);
}
