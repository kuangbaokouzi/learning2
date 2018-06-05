package com.entor;

import com.entor.dao.EmailDao;
import com.entor.po2.Email;
import com.entor.po2.User;
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

    @Test
    public void testSave() {
        Email email = new Email();
        email.setAddress("333@qq.com");

        User user = new User();
        user.setUsername("laowu");
        user.setPassword("123");
        user.setCellphone("333 ");

        // 设置一对多关联关系
        user.getEmails().add(email);

        //设置多对一关联关系
        email.setUser(user);
        emailDao.save(email);
    }

    @Test
    public void testGetAll(){
        System.out.println(emailDao.getAll());
    }
}
