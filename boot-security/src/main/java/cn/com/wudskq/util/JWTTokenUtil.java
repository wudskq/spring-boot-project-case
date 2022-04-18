package cn.com.wudskq.util;
 
import cn.com.wudskq.conf.JWTConfig;
import cn.com.wudskq.model.SysUserDetails;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
 
import java.util.*;
 

@Slf4j
public class JWTTokenUtil {
 
    /**
     * 创建Token
     *
     * @param sysUserDetails 用户信息
     * @return
     */
    public static String createAccessToken(SysUserDetails sysUserDetails) {
        String token = Jwts.builder().setId(// 设置JWT
                sysUserDetails.getId()) // 用户Id
                .setSubject(sysUserDetails.getUsername()) // 主题
                .setIssuedAt(new Date()) // 签发时间
                .setIssuer("limoumou") // 签发者
                .setExpiration(new Date(System.currentTimeMillis() + JWTConfig.expiration)) // 过期时间
                .signWith(SignatureAlgorithm.HS512, JWTConfig.secret) // 签名算法、密钥
                .claim("authorities", JSON.toJSONString(sysUserDetails.getAuthorities())).compact(); // 自定义其他属性，如用户组织机构ID，用户所拥有的角色，用户权限信息等
        return JWTConfig.tokenPrefix + token;
    }
 
    /**
     * 解析Token
     *
     * @param token Token信息
     * @return
     */
    public static SysUserDetails parseAccessToken(String token) {
        SysUserDetails sysUserDetails = null;
        if (StringUtils.isNotEmpty(token)) {
            try {
                // 去除JWT前缀
                token = token.substring(JWTConfig.tokenPrefix.length());
 
                // 解析Token
                Claims claims = Jwts.parser().setSigningKey(JWTConfig.secret).parseClaimsJws(token).getBody();
 
                // 获取用户信息
                sysUserDetails = new SysUserDetails();
                sysUserDetails.setId(claims.getId());
                sysUserDetails.setUsername(claims.getSubject());
                // 获取角色
                Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
                String authority = claims.get("authorities").toString();
                if (StringUtils.isNotEmpty(authority)) {
                    List<Map<String, String>> authorityList = JSON.parseObject(authority,
                            new TypeReference<List<Map<String, String>>>() {
                            });
                    for (Map<String, String> role : authorityList) {
                        if (!role.isEmpty()) {
                            authorities.add(new SimpleGrantedAuthority(role.get("authority")));
                        }
                    }
                }
                sysUserDetails.setAuthorities(authorities);
            } catch (Exception e) {
                log.error("解析Token异常：" + e);
            }
        }
        return sysUserDetails;
    }
 
}

