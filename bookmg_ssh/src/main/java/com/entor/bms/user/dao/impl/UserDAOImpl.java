package com.entor.bms.user.dao.impl;

import com.entor.bms.user.dao.UserDAO;
import com.entor.bms.user.entity.UserInfo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Repository("userDAO")
public class UserDAOImpl implements UserDAO {

    @Resource
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void insertUser(UserInfo userInfo) {
        getSession().save(userInfo);
    }

    @Override
    public List<UserInfo> getAll() {
        return getSession().createQuery("from UserInfo", UserInfo.class).list();
    }

    @Override
    public UserInfo selectUserInfoByIdCard(String idCard) {
        return getSession().createQuery("from UserInfo u where u.idCard = :idCard", UserInfo.class)
                .setParameter("idCard", idCard)
                .uniqueResult();
    }

    @Override
    public UserInfo selectByIdCardAndPassword(String idCard, String password) {
        return getSession().createQuery("from UserInfo u where u.idCard = :idCard and u.password = :password", UserInfo.class)
                .setParameter("idCard", idCard)
                .setParameter("password", password)
                .uniqueResult();
    }

    @Override
    public void updateStatusByUId(Integer uid, Integer status) {
        getSession().createQuery("update UserInfo u set u.status = :status where u.uid = :uid")
                .setParameter("status", status)
                .setParameter("uid", uid)
                .executeUpdate();
    }


    @Override
    public UserInfo selectById(Integer uid) {
        return getSession().get(UserInfo.class, uid);
    }

    @Override
    public void updateUserInfo(UserInfo userInfo) {
        getSession().update(userInfo);
    }

    @Override
    public void deleteUser(Integer uid) {
        UserInfo userInfo = getSession().get(UserInfo.class, uid);
        getSession().delete(userInfo);
    }

    @Override
    public void batchDel(String[] uids) {

        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaDelete<UserInfo> query = builder.createCriteriaDelete(UserInfo.class);
        Root<UserInfo> root = query.from(UserInfo.class);
        CriteriaBuilder.In<Integer> in = builder.in(root.get("uid"));
        for (String uid : uids) {
            if (!StringUtils.isEmpty(uid)) {
                in.value(Integer.parseInt(uid));
            }
        }
        getSession().createQuery(query.where(in)).executeUpdate();
    }

    @Override
    public List<UserInfo> selectByName(String name) {
        return getSession().createQuery("from UserInfo u where u.name = :name",UserInfo.class)
                .list();
    }


    @Override
    public UserInfo selectById(int uid, int ex_status) {
        return getSession().createQuery("from UserInfo u where u.uid = :uid and u.status = :status", UserInfo.class)
                .setParameter("uid",uid)
                .setParameter("status", ex_status)
                .uniqueResult();
    }
}
