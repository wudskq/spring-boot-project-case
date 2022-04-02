package cn.com.wudskq.sevice.impl;

import cn.com.wudskq.mapper.DruidMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author chenfangchao
 * @title: DruidSeviceImpl
 * @projectName boot-project
 * @description: TODO
 * @date 2022/4/2 4:46 PM
 */
@Service
@Qualifier("testSeviceImpl")
public class DruidSeviceImpl implements DruidSevice{

    @Autowired
    private DruidMapper druidMapper;

    @Override
    public String Master() {
        return druidMapper.masterDateSource();
    }

    @Override
    public String Slave1() {
        return druidMapper.salve1DateSource();
    }

    @Override
    public String Slave2() {
        return druidMapper.slave2DateSource();
    }
}
