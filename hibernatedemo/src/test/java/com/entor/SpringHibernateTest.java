package com.entor;

import com.entor.dao.EmailDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@ContextConfiguration({"classpath:applicationContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class SpringHibernateTest {
    @Resource
    private EmailDao emailDao;

    @Test
    public void testGet(){
        System.out.println(emailDao.get(1));
    }
}
