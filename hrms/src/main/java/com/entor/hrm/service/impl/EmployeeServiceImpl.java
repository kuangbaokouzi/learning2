package com.entor.hrm.service.impl;

import com.entor.hrm.dao.EmployeeDAO;
import com.entor.hrm.domain.Employee;
import com.entor.hrm.service.EmployeeService;
import com.entor.hrm.service.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("employeeService")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDAO employeeDAO;

    @Transactional(readOnly = true)
    @Override
    public Employee findEmployeeById(Integer id) {
        return employeeDAO.getById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Employee> findEmployee(Employee employee, PageModel<Employee> pageModel) {
        // 分页参数
        Map<String, Object> params = new HashMap<>();
        params.put("employee", employee);
        int recordCount = employeeDAO.count(params);
        pageModel.setRecordCount(recordCount);
        // 分页
        if (recordCount > 0) {
            params.put("pageModel", pageModel);
        }
        return employeeDAO.selectByPage(params);
    }

    @Override
    public void removeEmployeeById(Integer id) {
        employeeDAO.deleteById(id);
    }

    @Override
    public void modifyEmployee(Employee employee) {
        employeeDAO.update(employee);
    }

    @Override
    public void addEmployee(Employee employee) {
        employeeDAO.save(employee);
    }
}
