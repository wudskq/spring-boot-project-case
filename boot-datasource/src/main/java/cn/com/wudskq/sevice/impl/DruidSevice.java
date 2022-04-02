package cn.com.wudskq.sevice.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author chenfangchao
 * @title: DruidSevice
 * @projectName boot-project
 * @description: TODO
 * @date 2022/4/2 4:45 PM
 */
@Service
@Qualifier("testSevice")
public interface DruidSevice {

    String Master();

    String Slave1();

    String Slave2();
}
