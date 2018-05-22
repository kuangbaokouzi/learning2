package com.entor.hrm.service;


import com.entor.hrm.domain.Employee;

import java.util.List;

public interface EmployeeService {

    /**
     * 根据id查询员工
     * @param id
     * @return
     */
    Employee findEmployeeById(Integer id);

    /**
     * 分页查询员工
     * @param employee
     * @param pageModel
     * @return {@link List<Employee>}
     */
    List<Employee> findEmployee(Employee employee, PageModel<Employee> pageModel);

    /**
     * 根据id删除员工
     * @param id
     */
    void removeEmployeeById(Integer id);

    /**
     * 修改员工
     * @param employee
     */
    void modifyEmployee(Employee employee);

    /**
     * 添加员工
     * @param employee
     */
    void addEmployee(Employee employee);
}
