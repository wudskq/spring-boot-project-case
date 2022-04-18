package cn.com.wudskq.service.impl;

import cn.com.wudskq.dao.TSysResMapper;
import cn.com.wudskq.dao.TSysRoleMapper;
import cn.com.wudskq.dao.TSysRoleResMapper;
import cn.com.wudskq.model.dto.TSysRes;
import cn.com.wudskq.model.dto.TSysRole;
import cn.com.wudskq.service.TSysResService;
import org.springframework.stereotype.Service;
 
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
 

@Service
public class TSysResServiceImpl implements TSysResService {
 
    @Resource
    private TSysResMapper tSysResMapper;
 
    @Resource
    private TSysRoleMapper tSysRoleMapper;
 
    @Resource
    private TSysRoleResMapper tSysRoleResMapper;


    /**
     * 根据用户id查询用户拥有的资源
     * [userId]
     * @return {@link List< TSysRes>}
     * @throws
     */
    @Override
    public List<TSysRes> findResByUserId(String userId) {
        //获取用户有的角色
        //根据当前登录用户获取角色
        List<TSysRole> roleList = tSysRoleMapper.findRoleByUserId(userId);
        if(roleList == null || roleList.size() == 0){ //如果用户没有角色返回没有权限
            return null;
        }
        //根据角色获取菜单资源id关系集合
        Map<String,Object> map = new HashMap<>();
        map.put("roleList",roleList);
        List<String> tSysRoleResList = tSysRoleResMapper.selectRoleResByMap(map);
        if(tSysRoleResList == null || tSysRoleResList.size() == 0){ //如果用户没有角色返回没有权限
            return null;
        }
        //根据资源id获取菜单资源
        return tSysResMapper.selectBatchIds(tSysRoleResList);
 
    }
 
}

