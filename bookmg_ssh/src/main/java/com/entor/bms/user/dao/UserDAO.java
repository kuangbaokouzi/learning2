package com.entor.bms.user.dao;

import java.util.List;

import com.entor.bms.user.entity.UserInfo;

public interface UserDAO {
    /**
     * 添加注册数据
     *
     * @param userInfo
     */
    void insertUser(UserInfo userInfo);

    List<UserInfo> getAll();

    UserInfo selectUserInfoByIdCard(String idCard);

    UserInfo selectByIdCardAndPassword(String idCard, String password);

    void updateStatusByUId(Integer uid, Integer status);

    UserInfo selectById(Integer uid);

    void updateUserInfo(UserInfo userInfo);

    void deleteUser(Integer uid);

    void batchDel(String[] uids);

    List<UserInfo> selectByName(String name);

    UserInfo selectById(int uid, int ex_status);
}
