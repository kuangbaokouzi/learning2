package com.entor.dao;

import com.entor.po2.Email;

import java.util.List;
import java.util.Map;

public interface EmailDao extends BaseDao<Email> {
    List<Email> batchSelectById(Integer[] ids);
}
