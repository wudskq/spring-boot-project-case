package cn.com.wudskq.sevice;

import cn.com.wudskq.model.Plan;

import javax.xml.ws.Response;

/**
 * @author chenfangchao
 * @title: MybatisSevice
 * @projectName boot-project
 * @description: TODO
 * @date 2022/4/4 5:49 AM
 */
public interface MybatisSevice {

    void insert(Plan plan);

    void insert1(Plan plan);

    void update(Plan plan);

    Plan getInfo(Long id);
}
