package cn.com.wudskq;


import cn.com.wudskq.mapper.DruidMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author chenfangchao
 * @title: DruidSourceTest
 * @projectName boot-project
 * @description: TODO
 * @date 2022/4/2 3:46 PM
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DruidSourceTest {


    //sh start.sh

    @Autowired
    private DruidMapper druidMapper;

    @Test
    public void testMaster(){
        System.out.println(druidMapper.masterDateSource());
    }

    @Test
    public void testSlave1(){
        System.out.println(druidMapper.salve1DateSource());
    }

    @Test
    public void testSlave2(){
        System.out.println(druidMapper.slave2DateSource());
    }
}
