package cn.com.wudskq.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
 

@Component
@ConfigurationProperties(prefix = "jwt")
public class JWTConfig {
 
    /**
     * 密匙Key
     */
    public static String secret;
 
    /**
     * HeaderKey
     */
    public static String tokenHeader;
 
    /**
     * Token前缀
     */
    public static String tokenPrefix;
 
    /**
     * 过期时间
     */
    public static Integer expiration;
 
    /**
     * 配置白名单
     */
    public static String antMatchers;
 
    /**
     * 将过期时间单位换算成毫秒
     *
     * @param expiration 过期时间，单位秒
     */
    public void setExpiration(Integer expiration) {
        JWTConfig.expiration = expiration * 1000;
    }
 
    public void setSecret(String secret) {
        JWTConfig.secret = secret;
    }
 
    public void setTokenHeader(String tokenHeader) {
        JWTConfig.tokenHeader = tokenHeader;
    }
 
    public void setTokenPrefix(String tokenPrefix) {
        JWTConfig.tokenPrefix = tokenPrefix + " ";
    }
 
    public void setAntMatchers(String antMatchers) {
        JWTConfig.antMatchers = antMatchers;
    }
 
}

