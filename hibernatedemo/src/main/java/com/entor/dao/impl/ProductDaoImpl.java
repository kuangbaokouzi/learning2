package com.entor.dao.impl;

import com.entor.dao.ProductDao;
import com.entor.po.Product;
import org.hibernate.SessionFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.transaction.annotation.Isolation.DEFAULT;
import static org.springframework.transaction.annotation.Propagation.REQUIRED;

@Transactional(propagation = REQUIRED, isolation = DEFAULT, readOnly = false)
public class ProductDaoImpl implements ProductDao {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<Product> findAllProducts() {
        return this.sessionFactory.getCurrentSession().createQuery("from Product").list();
    }

    public static void main(String[] args) {
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        ProductDao productDao = (ProductDao) ac.getBean("productDao");
        System.out.println(productDao.findAllProducts());
    }
}
