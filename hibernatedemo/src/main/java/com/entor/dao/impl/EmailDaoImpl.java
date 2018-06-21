package com.entor.dao.impl;

import com.entor.dao.EmailDao;
import com.entor.po2.Email;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository("emailDao")
public class EmailDaoImpl extends BaseDaoImpl<Email> implements EmailDao {
    @Override
    public List<Email> batchSelectById(Integer[] ids) {
        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery<Email> query = builder.createQuery(Email.class);
        Root<Email> root = query.from(Email.class);
        CriteriaBuilder.In<Integer> in = builder.in(root.get("id"));
        for (Integer id : ids) {
            if (id != null) {
                in.value(id);
            }
        }
        return getSession().createQuery(query.where(in)).list();
    }
}