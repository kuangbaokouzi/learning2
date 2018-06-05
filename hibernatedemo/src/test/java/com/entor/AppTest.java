package com.entor;

import com.entor.po.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AppTest {

    private Session session;

    @Before
    public void before() {
        //获取加载配置管理类
        Configuration configuration = new Configuration();

        //不给参数就默认加载hibernate.cfg.xml文件，
        configuration.configure();

        //创建Session工厂对象
        SessionFactory factory = configuration.buildSessionFactory();

        //得到Session对象
        session = factory.openSession();
    }

    @After
    public void after() {
        //关闭Session
        session.close();
    }

    @Test
    public void testSave() {
        //创建对象
        User user = new User();
        user.setPassword("123");
        user.setCellphone("122222");
        user.setUsername("nihao");

        //使用Hibernate操作数据库，都要开启事务,得到事务对象
        Transaction transaction = session.getTransaction();
        //开启事务
        transaction.begin();
        //把对象添加到数据库中
        session.save(user);
        //提交事务
        transaction.commit();
    }

    @Test
    public void testList(){
        System.out.println(session.createQuery("from User").list());
    }
}
