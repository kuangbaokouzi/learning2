package com.entor.hrm.service;


import com.entor.hrm.domain.User;

import java.util.List;

public interface UserService {

    /**
     * 用户登录
     * @param loginName
     * @param password
     * @return {@link User}
     */
    User login(String loginName, String password);

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    User findUserById(Integer id);

    /**
     * 分页查询用户
     * @param user
     * @param pageModel
     * @return {@link List<User>}
     */
    List<User> findUser(User user, PageModel<User> pageModel);

    /**
     * 根据id删除用户
     * @param id
     */
    void removeUserById(Integer id);

    /**
     * 修改用户
     * @param user
     */
    void modifyUser(User user);

    /**
     * 添加用户
     * @param user
     */
    void addUser(User user);
}
