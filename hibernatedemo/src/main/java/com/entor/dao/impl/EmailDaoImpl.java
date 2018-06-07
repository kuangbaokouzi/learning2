package com.entor.dao.impl;

import com.entor.dao.EmailDao;
import com.entor.po2.Email;
import org.springframework.stereotype.Repository;

@Repository("emailDao")
public class EmailDaoImpl extends BaseDaoImpl<Email> implements EmailDao {

}