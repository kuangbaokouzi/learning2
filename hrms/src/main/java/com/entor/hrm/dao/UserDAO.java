package com.entor.hrm.dao;

import com.entor.hrm.dao.provider.UserDynaSQLProvider;
import com.entor.hrm.domain.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

import static com.entor.hrm.uitl.common.HrmConstants.*;

public interface UserDAO {

    /**
     * 根据登录名和密码查询员工
     *
     * @param loginName
     * @param password
     * @return {@link User}
     */
    @Select("select * from " + USER_TABLE + " where login_name = #{loginName} and password = #{password}")
    User selectByLoginNameAndPassword(@Param("loginName") String loginName, @Param("password") String password);

    /**
     * 根据id查询用户
     *
     * @param id
     * @return {@link User}
     */
    @Select("select * from " + USER_TABLE + " where id = #{id}")
    User selectById(Integer id);

    /**
     * 根据id删除用户
     *
     * @param id
     */
    @Delete("delete from " + USER_TABLE + " where id = #{id}")
    void deleteById(Integer id);

    /**
     * 动态修改用户
     *
     * @param user
     */
    @SelectProvider(type = UserDynaSQLProvider.class, method = "updateUser")
    void update(User user);

    /**
     * 动态查询用户
     *
     * @param params
     * @return {@link List<User>}
     */
    @SelectProvider(type = UserDynaSQLProvider.class, method = "selectWithParams")
    List<User> selectByPage(Map<String, Object> params);

    /**
     * 根据参数查询用户总记录数
     *
     * @param params
     * @return {@link Integer}
     */
    @SelectProvider(type = UserDynaSQLProvider.class, method = "count")
    Integer count(Map<String, Object> params);

    /**
     * 动态插入用户
     *
     * @param user
     */
    @SelectProvider(type = UserDynaSQLProvider.class, method = "insertUser")
    void save(User user);
}
