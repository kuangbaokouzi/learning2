package com.entor.dao;

import com.entor.po.Email;

import java.util.List;

public interface EmailDao {
    Email get(Integer id);

    List<Email> getAll();

    void update(Email email);

    void delete(Integer id);
}
