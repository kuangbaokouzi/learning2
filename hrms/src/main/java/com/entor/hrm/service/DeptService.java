package com.entor.hrm.service;


import com.entor.hrm.domain.Dept;

import java.util.List;

public interface DeptService {

    /**
     * 根据id查询部门
     *
     * @param id
     * @return
     */
    Dept findDeptById(Integer id);

    /**
     * 分页查询部门
     *
     * @param dept
     * @param pageModel
     * @return {@link List<Dept>}
     */
    List<Dept> findDept(Dept dept, PageModel<Dept> pageModel);

    /**
     * 根据id删除部门
     *
     * @param id
     */
    void removeDeptById(Integer id);

    /**
     * 修改部门
     *
     * @param dept
     */
    void modifyDept(Dept dept);

    /**
     * 添加部门
     *
     * @param dept
     */
    void addDept(Dept dept);
}
