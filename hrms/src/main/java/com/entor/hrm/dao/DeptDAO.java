package com.entor.hrm.dao;

import com.entor.hrm.dao.provider.DeptDynaSQLProvider;
import com.entor.hrm.domain.Dept;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

import static com.entor.hrm.uitl.common.HrmConstants.DEPT_TABLE;

public interface DeptDAO {

    /**
     * 动态查询部门
     *
     * @param params
     * @return {@link List<Dept>}
     */
    @SelectProvider(type = DeptDynaSQLProvider.class, method = "selectWithParams")
    List<Dept> selectByPage(Map<String, Object> params);

    /**
     * 动态查询部门总记录数
     *
     * @param params
     * @return {@link Integer}
     */
    @SelectProvider(type = DeptDynaSQLProvider.class, method = "count")
    Integer count(Map<String, Object> params);

    /**
     * 查询所有用户
     *
     * @return {@link List<Dept>}
     */
    @Select("select * from " + DEPT_TABLE + " ")
    List<Dept> getAllDept();

    /**
     * 根据id查询部门
     *
     * @param id
     * @return {@link Dept}
     */
    @Select("select * from " + DEPT_TABLE + " where id = #{id} ")
    Dept getById(Integer id);

    /**
     * 根据id删除部门
     *
     * @param id
     */
    @Delete("delete from " + DEPT_TABLE + " where id = #{id} ")
    void deleteById(Integer id);

    /**
     * 动态插入用户
     *
     * @param dept
     */
    @SelectProvider(type = DeptDynaSQLProvider.class, method = "insertUser")
    void save(Dept dept);

    /**
     * 动态修改用户
     *
     * @param dept
     */
    @SelectProvider(type = DeptDynaSQLProvider.class, method = "updateUser")
    void update(Dept dept);
}
