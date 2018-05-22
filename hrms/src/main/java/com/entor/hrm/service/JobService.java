package com.entor.hrm.service;


import com.entor.hrm.domain.Job;

import java.util.List;

public interface JobService {

    /**
     * 根据id查询工作
     *
     * @param id
     * @return
     */
    Job findJobById(Integer id);

    /**
     * 分页查询工作
     *
     * @param job
     * @param pageModel
     * @return {@link List<Job>}
     */
    List<Job> findJob(Job job, PageModel<Job> pageModel);

    /**
     * 根据id删除工作
     *
     * @param id
     */
    void removeJobById(Integer id);

    /**
     * 修改工作
     *
     * @param job
     */
    void modifyJob(Job job);

    /**
     * 添加工作
     *
     * @param job
     */
    void addJob(Job job);
}
