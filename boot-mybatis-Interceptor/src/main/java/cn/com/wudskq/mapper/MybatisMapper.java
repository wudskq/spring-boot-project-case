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

    void insert(Plan plan);


    void update(Plan plan);
}
