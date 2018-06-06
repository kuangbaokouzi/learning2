package com.entor.dao.impl;

import com.entor.dao.EmailDao;
import com.entor.po2.Email;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

import static org.springframework.transaction.annotation.Propagation.REQUIRED;

@Repository("emailDao")
@Transactional(propagation = REQUIRED, isolation = Isolation.DEFAULT, readOnly = false)
public class EmailDaoImpl implements EmailDao {

    @Resource
    private SessionFactory sessionFactory;

    @Resource
    private HibernateTemplate hibernateTemplate;

    @Transactional(readOnly = true)
    @Override
    public Email get(Integer id) {
        // 1.JPA Query
//        return getSession().createQuery(
//                "from Email e " +
//                        "where e.id = :id", Email.class
//        ).setParameter("id", id).uniqueResult();

        // 2.HQL Query
//        org.hibernate.query.Query<Email> query = getSession().createQuery(
//                        "from Email e " +
//                        "where e.id = :id"
//        );
//        query.setParameter("id", id);
//        return query.uniqueResult();

        // 3.Criteria
        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery<Email> criteria = builder.createQuery(Email.class);
        Root<Email> root = criteria.from(Email.class);
        criteria.select(root);
        criteria.where(builder.equal(root.get("id"), id));
        return getSession().createQuery(criteria).uniqueResult();
    }

    @Override
    public List<Email> getAll() {
        return hibernateTemplate.findByExample(new Email());
    }

    @Override
    public void update(Email email) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public void save(Email email) {
        getSession().save(email);
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }
}
