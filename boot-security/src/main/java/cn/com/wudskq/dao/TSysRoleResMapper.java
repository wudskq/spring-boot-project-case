package cn.com.wudskq.dao;
 
import cn.com.wudskq.model.dto.TSysRoleRes;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.apache.ibatis.annotations.Mapper;
 
import java.util.List;
import java.util.Map;
 

@Mapper
public interface TSysRoleResMapper extends BaseMapper<TSysRoleRes> {
 
    List<String> selectRoleResByMap(Map<String, Object> map);
    
}

