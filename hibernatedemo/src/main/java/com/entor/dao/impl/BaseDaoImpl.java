package com.entor.dao.impl;

import com.entor.dao.BaseDao;
import com.entor.service.impl.PageModel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.HibernateTemplate;

import javax.annotation.Resource;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class BaseDaoImpl<T> implements BaseDao<T> {
    @Resource
    private SessionFactory sessionFactory;

    @Resource
    private HibernateTemplate hibernateTemplate;

    protected Class<T> entityClazz;

    public BaseDaoImpl() {
        Type type = getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            this.entityClazz = (Class<T>) ((ParameterizedType) type).getActualTypeArguments()[0];
        }
    }

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public T getById(Serializable id) {
        if (id == null)
            return null;
        return getSession().get(entityClazz, id);
    }

    @Override
    public T insert(T o) {
        getSession().save(o);
        return o;
    }

    @Override
    public void insert(List<T> list) {
        for (T o : list) {
            getSession().save(o);
        }
    }

    @Override
    public void delete(List<T> list) {
        for (T o : list) {
            getSession().delete(o);
        }
    }

    @Override
    public void update(T t) {
        getSession().update(t);
    }

    @Override
    public void update(List<T> list) {
        for (T o : list) {
            getSession().update(o);
        }
    }

    @Override
    public List<T> findByProperty(String name, Object value) {
        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(entityClazz);
        Root<T> root = query.from(entityClazz);
        query.select(root);
        query.where(builder.equal(root.get(name), value));
        return (List<T>) getSession().createQuery(query).getResultList();
    }

    @Override
    public List<T> findByProperty(Map<String, Object> conditionMap) {
        return null;
    }


    @Override
    public List<T> findByPage(Map<String, Object> params) {
        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = builder.createQuery(entityClazz);
        Root<T> root = criteriaQuery.from(entityClazz);
        String simpleName = entityClazz.getSimpleName();
        simpleName = simpleName.substring(0, 1).toLowerCase() + simpleName.substring(1);
        if (params.get(simpleName) != null) {
            T t = (T) params.get(simpleName);
            Field[] fields = entityClazz.getDeclaredFields();
            Predicate predicate = builder.and();
            for (Field field : fields) {
                String fieldName = field.getName();
                Method method = null;
                try {
                    method = entityClazz.getMethod("get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1), null);
                    if (method.invoke(t, null) != null) {
                        criteriaQuery.where(builder.and(predicate, builder.equal(root.get(fieldName), method.invoke(t, null))));
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        Query query = getSession().createQuery(criteriaQuery);
        if (params.get("pageModel") != null) {
            PageModel<T> pageModel = (PageModel<T>) params.get("pageModel");
            query.setFirstResult(pageModel.getFirstLimitParam()).setMaxResults(pageModel.getPageSize());
        }
        return query.getResultList();
    }

    @Override
    public Integer count(Map<String, Object> params) {
        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = builder.createQuery(entityClazz);
        Root<T> root = criteriaQuery.from(entityClazz);
        String simpleName = entityClazz.getSimpleName();
        simpleName = simpleName.substring(0, 1).toLowerCase() + simpleName.substring(1);
        if (params.get(simpleName) != null) {
            T t = (T) params.get(simpleName);
            Field[] fields = entityClazz.getDeclaredFields();
            Predicate predicate = builder.and();
            for (Field field : fields) {
                String fieldName = field.getName();
                Method method = null;
                try {
                    method = entityClazz.getMethod("get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1), null);
                    if (method.invoke(t, null) != null) {
                        criteriaQuery.where(builder.and(predicate, builder.equal(root.get(fieldName), method.invoke(t, null))));
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        return getSession().createQuery(criteriaQuery).getResultList().size();
    }

    @Override
    public <T> T get(CharSequence queryString, Map<String, Object> params) {
        return null;
    }
}
