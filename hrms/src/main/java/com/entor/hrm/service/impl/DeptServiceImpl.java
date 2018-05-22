package com.entor.hrm.service.impl;

import com.entor.hrm.dao.DeptDAO;
import com.entor.hrm.domain.Dept;
import com.entor.hrm.service.DeptService;
import com.entor.hrm.service.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("deptService")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptDAO deptDAO;

    @Transactional(readOnly = true)
    @Override
    public Dept findDeptById(Integer id) {
        return deptDAO.getById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Dept> findDept(Dept dept, PageModel<Dept> pageModel) {
        // 分页参数
        Map<String, Object> params = new HashMap<>();
        params.put("dept", dept);
        int recordCount = deptDAO.count(params);
        pageModel.setRecordCount(recordCount);
        // 分页操作
        if (recordCount > 0) {
            params.put("pageModel", pageModel);
        }
        return deptDAO.selectByPage(params);
    }

    @Override
    public void removeDeptById(Integer id) {
        deptDAO.deleteById(id);
    }

    @Override
    public void modifyDept(Dept dept) {
        deptDAO.update(dept);
    }

    @Override
    public void addDept(Dept dept) {
        deptDAO.save(dept);
    }
}
