package cn.com.wudskq.mapper;

import cn.com.wudskq.model.Plan;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
public interface MybatisPlusMapper extends BaseMapper<Plan> {

}
