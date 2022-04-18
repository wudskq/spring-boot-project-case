package cn.com.wudskq.controller;

import cn.com.wudskq.model.vo.Result;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
 

@Slf4j
@RequestMapping("tSysUser")
@RestController
@Api(value = "系统用户服务", tags = "系统用户服务")
public class TSysUserController {
 
    @PreAuthorize("hasRole('ROLE_/tSysUser/findByPage')")
    @PostMapping("findByPage")
    @ApiOperation(value = "添加系统用户", notes = "添加系统用户", produces = "application/json")
    public Result findByPage(){
        log.info("测试用户分页查询权限");
        return Result.success("测试用户分页查询权限");
    }
 
    @PreAuthorize("hasRole('ROLE_/tSysUser/add')")
    @PostMapping("add")
    @ApiOperation(value = "添加系统用户", notes = "添加系统用户", produces = "application/json")
    public Result add(){
        log.info("测试用户添加权限");
        return Result.success("测试用户添加权限");
    }
 
    @PreAuthorize("hasRole('ROLE_/tSysUser/edit')")
    @PostMapping("edit")
    @ApiOperation(value = "修改系统用户", notes = "修改系统用户", produces = "application/json")
    public Result edit(){
        log.info("测试用户编辑权限");
        return Result.success("测试用户编辑权限");
    }
 
    @PreAuthorize("hasRole('ROLE_/tSysUser/delete')")
    @PostMapping("delete")
    @ApiOperation(value = "删除系统用户", notes = "删除系统用户", produces = "application/json")
    public Result delete(){
        log.info("测试用户删除权限");
        return Result.success("测试用户删除权限");
    }
}

