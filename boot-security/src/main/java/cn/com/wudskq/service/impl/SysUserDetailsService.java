package cn.com.wudskq.service.impl;
 
import cn.com.wudskq.dao.TSysResMapper;
import cn.com.wudskq.model.SysUserDetails;
import cn.com.wudskq.model.dto.TSysRes;
import cn.com.wudskq.model.dto.TSysUser;
import cn.com.wudskq.service.TSysResService;
import cn.com.wudskq.service.TSysUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.util.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
 
import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
 

@Service
public class SysUserDetailsService  implements UserDetailsService {
 
    @Autowired
    private TSysUserService tSysUserService;
 
    @Resource
    private TSysResMapper tSysResMapper;
 
    @Autowired
    private TSysResService tSysResService;
 
    /**
     * 说明：重写UserDetailsService中的loadUserByUsername，就是查询用户详细信息封装到 UserDetails
     * 业务：
     *      ①如果是admin会拥有全部权限
     *      ②如果不是admin就去查用户信息和用户拥有的权限
     * [username]
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<TSysRes> resList = null;
        if(username.equals("admin")){
            TSysUser tSysUser = new TSysUser();
            tSysUser.setId("admin");
            tSysUser.setUsername("admin");
            tSysUser.setNickName("系统管理员");
            SysUserDetails sysUserDetails = new SysUserDetails();
            BeanUtils.copyProperties(tSysUser, sysUserDetails);
            Set<GrantedAuthority> authorities = new HashSet<>(); // 角色集合
 
            //admin用户有的资源集合
            resList = tSysResMapper.selectList(new QueryWrapper<>());
            for (int i = 0; i < resList.size() ; i++) {
                if(StringUtil.isNotEmpty(resList.get(i).getPermission())){
                    authorities.add(new SimpleGrantedAuthority("ROLE_" + resList.get(i).getPermission()));
                }
            }
            sysUserDetails.setAuthorities(authorities);
            return sysUserDetails;
        }
        TSysUser tSysUser = tSysUserService.findByUsername(username);
        if (tSysUser != null) {
            SysUserDetails sysUserDetails = new SysUserDetails();
            BeanUtils.copyProperties(tSysUser, sysUserDetails);
 
            Set<GrantedAuthority> authorities = new HashSet<>(); // 角色集合
 
            resList = tSysResService.findResByUserId(sysUserDetails.getId());//当前用户有的资源集合
            if(resList != null){
                for (int i = 0; i < resList.size() ; i++) {
                    if(StringUtil.isNotEmpty(resList.get(i).getPermission())){
                        authorities.add(new SimpleGrantedAuthority("ROLE_" + resList.get(i).getPermission()));
                    }
                }
            }
            sysUserDetails.setAuthorities(authorities);
            return sysUserDetails;
        }
        return null;
    }
}

