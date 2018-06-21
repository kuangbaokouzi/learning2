package com.entor.service;

import com.entor.po2.Email;
import com.entor.service.impl.PageModel;

import java.util.List;
import java.util.Map;

public interface EmailService {
    Email getEmailById(Integer id);

    List<Email> findByProperty(String name, Object value);

    PageModel<Email> findByPage(Email email, Integer pageIndex, Integer pageSize);

    List<Email> findByIds(Integer[] ids);

    void modifyEmail(Email email);
}
