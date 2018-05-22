package com.entor.hrm.service.impl;

import com.entor.hrm.dao.UserDAO;
import com.entor.hrm.domain.User;
import com.entor.hrm.service.UserService;
import com.entor.hrm.service.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("userService")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;


    @Transactional(readOnly = true)
    @Override
    public User login(String loginName, String password) {
        return userDAO.selectByLoginNameAndPassword(loginName, password);
    }

    @Transactional(readOnly = true)
    @Override
    public User findUserById(Integer id) {
        return userDAO.selectById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> findUser(User user, PageModel<User> pageModel) {
        // 分页参数
        Map<String, Object> params = new HashMap<>();
        params.put("user", user);
        int recordCount = userDAO.count(params);
        pageModel.setRecordCount(recordCount);
        // 分页
        if (recordCount > 0) {
            params.put("pageModel", pageModel);
        }
        return userDAO.selectByPage(params);
    }

    @Override
    public void removeUserById(Integer id) {
        userDAO.deleteById(id);
    }

    @Override
    public void modifyUser(User user) {
        userDAO.update(user);
    }

    @Override
    public void addUser(User user) {
        userDAO.save(user);
    }
}
