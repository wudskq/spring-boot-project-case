package cn.com.wudskq.service.impl;
 
import cn.com.wudskq.dao.TSysUserMapper;
import cn.com.wudskq.model.dto.TSysUser;
import cn.com.wudskq.service.TSysUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
 
import javax.annotation.Resource;

@Service
public class TSysUserServiceImpl implements TSysUserService {

    @Resource
    private TSysUserMapper tSysUserMapper;
 

    public TSysUser findByUsername(String username) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("username",username);
        return tSysUserMapper.selectOne(queryWrapper);
    }
 
 
}

