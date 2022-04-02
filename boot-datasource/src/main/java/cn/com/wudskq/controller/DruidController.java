package cn.com.wudskq.controller;

import cn.com.wudskq.sevice.impl.DruidSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenfangchao
 * @title: DruidController
 * @projectName boot-project
 * @description: TODO
 * @date 2022/4/2 4:33 PM
 */
@RestController
@RequestMapping("/duird/")
public class DruidController {


    @Autowired
    @Qualifier("testSeviceImpl")
    private DruidSevice druidSevice;

    @GetMapping("/master")
    public String master(){
        return druidSevice.Master();
    }

    @GetMapping("/slave1")
    public String slave1(){
        return druidSevice.Slave1();
    }

    @GetMapping("/slave2")
    public String slave2(){
        return druidSevice.Slave2();
    }
}
