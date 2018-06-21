package com.entor.service.impl;

import com.entor.dao.EmailDao;
import com.entor.po2.Email;
import com.entor.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.transaction.annotation.Propagation.REQUIRED;

@Service("emailService")
@Transactional(propagation = REQUIRED, isolation = Isolation.DEFAULT, readOnly = false)
public class EmailServiceImpl implements EmailService {

    @Autowired
    private EmailDao emailDao;

    @Transactional(readOnly = true)
    @Override
    public Email getEmailById(Integer id) {
        return (Email) emailDao.getById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Email> findByProperty(String name, Object value) {
        return emailDao.findByProperty(name, value);
    }

    @Transactional(readOnly = true)
    @Override
    public PageModel<Email> findByPage(Email email, Integer pageIndex, Integer pageSize) {
        Map<String, Object> params = new HashMap<>();
        params.put("email", email);

        int recordCount = emailDao.count(params);

        PageModel<Email> pageModel = new PageModel<>();
        pageModel.setPageIndex(pageIndex);
        pageModel.setPageSize(pageSize);
        pageModel.setRecordCount(recordCount);

        if (recordCount > 0) {
            params.put("pageModel", pageModel);
            pageModel.setPageList(emailDao.findByPage(params));
        }

        return pageModel;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Email> findByIds(Integer[] ids) {
        return emailDao.batchSelectById(ids);
    }

    @Override
    public void modifyEmail(Email email) {
        Email persistEmail = emailDao.getById(email.getId());
        if(email.getAddress() != null)
            persistEmail.setAddress(email.getAddress());

        if(email.getUser() != null)
            persistEmail.setUser(email.getUser());
        emailDao.update(persistEmail);
    }
}
