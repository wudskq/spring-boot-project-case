package cn.com.wudskq.sevice.impl;

import cn.com.wudskq.mapper.MybatisPlusMapper;
import cn.com.wudskq.model.Plan;
import cn.com.wudskq.sevice.MybatisPlusSevice;
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
public class MybatisPlusSeviceImpl implements MybatisPlusSevice {

    @Autowired
    private MybatisPlusMapper mybatisMapper;

    @Override
    public void insert(Plan plan) {
        mybatisMapper.insert(plan);
    }

    @Override
    public void update(Plan plan) {
        mybatisMapper.updateById(plan);
    }

    @Override
    public Plan getInfo(Long id) {
        return  mybatisMapper.selectById(id);
    }
}
