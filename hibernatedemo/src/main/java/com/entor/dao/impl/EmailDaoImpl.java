package com.entor.dao.impl;

import com.entor.dao.EmailDao;
import com.entor.po.Email;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

import static org.springframework.transaction.annotation.Propagation.REQUIRED;

@Repository("emailDao")
@Transactional(propagation = REQUIRED, isolation = Isolation.DEFAULT, readOnly = false)
public class EmailDaoImpl implements EmailDao {

    @Resource
    private SessionFactory sessionFactory;

    @Transactional(readOnly = true)
    @Override
    public Email get(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Email.class, 1);
    }

    @Override
    public List<Email> getAll() {
        return null;
    }

    @Override
    public void update(Email email) {

    }

    @Override
    public void delete(Integer id) {

    }
}
