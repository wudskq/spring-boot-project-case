package cn.com.wudskq.service;


import cn.com.wudskq.model.dto.TSysUser;

public interface TSysUserService {
 
    TSysUser findByUsername(String username);
 
}

