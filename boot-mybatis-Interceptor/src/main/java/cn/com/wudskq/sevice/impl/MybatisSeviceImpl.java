package cn.com.wudskq.sevice.impl;

import cn.com.wudskq.mapper.MybatisMapper;
import cn.com.wudskq.model.Plan;
import cn.com.wudskq.sevice.MybatisSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author chenfangchao
 * @title: MybatisSeviceImpl
 * @projectName boot-project
 * @description: TODO
 * @date 2022/4/4 5:50 AM
 */
@Service
public class MybatisSeviceImpl implements MybatisSevice {

    @Autowired
    private MybatisMapper mybatisMapper;

    @Override
    public void insert(Plan plan) {
        mybatisMapper.insert(plan);
    }

    @Override
    public void insert1(Plan plan) {
        mybatisMapper.insert1(plan,"参数二");
    }


    @Override
    public void update(Plan plan) {
        mybatisMapper.update(plan);
    }
}
