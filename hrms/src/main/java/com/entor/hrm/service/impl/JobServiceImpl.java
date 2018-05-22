package com.entor.hrm.service.impl;

import com.entor.hrm.dao.JobDAO;
import com.entor.hrm.domain.Job;
import com.entor.hrm.service.JobService;
import com.entor.hrm.service.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("jobService")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
public class JobServiceImpl implements JobService {

    @Autowired
    private JobDAO jobDAO;

    @Transactional(readOnly = true)
    @Override
    public Job findJobById(Integer id) {
        return jobDAO.getById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Job> findJob(Job job, PageModel<Job> pageModel) {
        // 分页参数
        Map<String, Object> params = new HashMap<>();
        params.put("job", job);
        int recordCount = jobDAO.count(params);
        pageModel.setRecordCount(recordCount);
        // 分页
        if (recordCount > 0) {
            params.put("pageModel", pageModel);
        }
        return jobDAO.selectByPage(params);
    }

    @Override
    public void removeJobById(Integer id) {
        jobDAO.deleteById(id);
    }

    @Override
    public void modifyJob(Job job) {
        jobDAO.update(job);
    }

    @Override
    public void addJob(Job job) {
        jobDAO.save(job);
    }
}
