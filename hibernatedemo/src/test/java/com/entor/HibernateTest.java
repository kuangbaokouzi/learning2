package com.entor;

import com.entor.po.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class HibernateTest {
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
    public void testOneToOne() {
        Notice notice = new Notice();
        notice.setTitle("崔永元怒怼冯小刚");
        notice.setContent("崔永元因《手机》续作怒怼《手机2》制作");

        Transaction tx = session.beginTransaction();
        User user = session.get(User.class, 1);
        notice.setUser(user);
        session.save(notice);
        tx.commit();
    }

    @Test
    public void testGet() {
        Notice notice = session.get(Notice.class, 1);
        System.out.println(notice.getContent());
//        System.out.println(notice.getUser());
    }

    @Test
    public void testOneToMany() {
        User user = new User();
        user.setUsername("laozhang");
        user.setPassword("123123");
        user.setCellphone("1333232323");

        Set<Email> emails = new HashSet<>();
        Email email = new Email();
        email.setAddress("chengdu");
        Email email2 = new Email();
        email2.setAddress("nanning");
        Email email3 = new Email();
        email3.setAddress("beijing");
        emails.add(email);
        emails.add(email2);
        emails.add(email3);

        user.setEmails(emails);

        Transaction tx = session.beginTransaction();
        session.save(user);
        tx.commit();
    }

    @Test
    public void testManyToOne() {
        Email email = new Email();
        email.setAddress("3333@qq.com");

        Transaction tx = session.beginTransaction();
        email.setUser(session.get(User.class, 1));
        session.save(email);
        tx.commit();
    }

    @Test
    public void testDelete() {
        Transaction tx = session.beginTransaction();
        User user = session.get(User.class, 2);
        session.delete(user);
        tx.commit();
    }

    @Test
    public void testUpate() {
        Transaction tx = session.beginTransaction();
        Email email = session.get(Email.class, 7);
        User user = session.get(User.class, 1);
        email.setUser(user);
        session.update(email);
        tx.commit();
    }

    @Test
    public void testAnnotation() {
        Dept dept = new Dept();
        dept.setDname("hr");
        dept.setCreateDate(new Date());

        Emp emp = new Emp();
        emp.setEname("xiaocui");
        emp.setDept(dept);

        dept.getEmps().add(emp);

        Transaction tx = session.beginTransaction();
        session.save(dept);
        tx.commit();
    }
}
