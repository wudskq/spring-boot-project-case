package cn.com.wudskq.sevice;

import cn.com.wudskq.model.Plan;

/**
 * @author chenfangchao
 * @title: MybatisSevice
 * @projectName boot-project
 * @description: TODO
 * @date 2022/4/4 5:49 AM
 */
public interface MybatisPlusSevice {

    void insert(Plan plan);

    void update(Plan plan);

    Plan getInfo(Long id);
}
