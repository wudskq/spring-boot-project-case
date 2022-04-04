package cn.com.wudskq.controller;

import cn.com.wudskq.model.Plan;
import cn.com.wudskq.sevice.MybatisSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenfangchao
 * @title: MybatisController
 * @projectName boot-project
 * @description: TODO
 * @date 2022/4/4 5:50 AM
 */
@RequestMapping("/mybatis/interceptor")
@RestController
public class MybatisController {

    @Autowired
    private MybatisSevice mybatisSevice;

    @GetMapping("/insert")
    public void insert(){
        Plan plan = new Plan("计划一", "2022年");
        mybatisSevice.insert(plan);
    }

    @GetMapping("/insert1")
    public void insert1(){
        Plan plan = new Plan("计划一", "2022年");
        mybatisSevice.insert1(plan);
    }

    @GetMapping("/update")
    public void update(){
        Plan plan = new Plan("计划一", "2022年");
        plan.setId(1L);
        mybatisSevice.update(plan);
    }

    @GetMapping("/info")
    public Plan getInfo(@RequestParam("id")Long id){
       return mybatisSevice.getInfo(id);
    }
}


