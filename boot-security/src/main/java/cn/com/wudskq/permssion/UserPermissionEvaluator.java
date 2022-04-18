package cn.com.wudskq.permssion;

import cn.com.wudskq.expection.MyException;
import cn.com.wudskq.model.SysUserDetails;
import cn.com.wudskq.model.dto.TSysRes;
import cn.com.wudskq.service.TSysResService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Configuration
@Component
public class UserPermissionEvaluator implements PermissionEvaluator {
 
    @Autowired
    private TSysResService tSysResService;

    @Override
    public boolean hasPermission(Authentication authentication, Object targetUrl, Object permission) {
        SysUserDetails sysUserDetails = null;
        try {
            sysUserDetails = (SysUserDetails) authentication.getPrincipal();
        } catch (Exception e){
            throw new MyException(403,"权限不足");
        }
 
        Set<String> permissions = new HashSet<String>(); // 用户权限
 
        List<TSysRes> authList = tSysResService.findResByUserId(sysUserDetails.getId());
 
        for (int i = 0; i < authList.size() ; i++) {
            permissions.add(authList.get(i).getPermission());
        }
 
        // 判断是否拥有权限
        if (permissions.contains(permission.toString())) {
            return true;
        }
        return false;
    }
 
    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType,
                                 Object permission) {
        return false;
    }
 
}