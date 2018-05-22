package com.entor.hrm.dao;

import com.entor.hrm.dao.provider.EmployeeDynaSQLProvider;
import com.entor.hrm.domain.Employee;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;
import java.util.Map;

import static com.entor.hrm.uitl.common.HrmConstants.EMPLOYEE_TABLE;

public interface EmployeeDAO {

    /**
     * 动态查询部门
     *
     * @param params
     * @return {@link List<Employee>}
     */
    @SelectProvider(type = EmployeeDynaSQLProvider.class, method = "selectWithParams")
    @Results({
            @Result(id = true, column = "id", property = "id", javaType = java.lang.Integer.class),
            @Result(column = "card_id", property = "cardId", javaType = java.lang.String.class),
            @Result(column = "post_code", property = "postCode", javaType = java.lang.String.class),
            @Result(column = "qq_num", property = "qqNum", javaType = java.lang.String.class),
            @Result(column = "create_date", property = "createDate", javaType = java.util.Date.class),
            @Result(column = "dept_id", property = "dept", one = @One(select = "om.entor.hrm.dao.DeptDAO.getById", fetchType = FetchType.EAGER)),
            @Result(column = "job_id", property = "job", one = @One(select = "om.entor.hrm.dao.JobDAO.getById", fetchType = FetchType.EAGER))
    })
    List<Employee> selectByPage(Map<String, Object> params);

    /**
     * 动态查询部门总记录数
     *
     * @param params
     * @return {@link Integer}
     */
    @SelectProvider(type = EmployeeDynaSQLProvider.class, method = "count")
    Integer count(Map<String, Object> params);

    /**
     * 查询所有用户
     *
     * @return {@link List<Employee>}
     */
    @Select("select * from " + EMPLOYEE_TABLE + " ")
    @Results({
            @Result(id = true, column = "id", property = "id", javaType = java.lang.Integer.class),
            @Result(column = "card_id", property = "cardId", javaType = java.lang.String.class),
            @Result(column = "post_code", property = "postCode", javaType = java.lang.String.class),
            @Result(column = "qq_num", property = "qqNum", javaType = java.lang.String.class),
            @Result(column = "create_date", property = "createDate", javaType = java.util.Date.class),
            @Result(column = "dept_id", property = "dept", one = @One(select = "om.entor.hrm.dao.DeptDAO.getById", fetchType = FetchType.EAGER)),
            @Result(column = "job_id", property = "job", one = @One(select = "om.entor.hrm.dao.JobDAO.getById", fetchType = FetchType.EAGER))
    })
    List<Employee> getAllEmployee();

    /**
     * 根据id查询部门
     *
     * @param id
     * @return {@link Employee}
     */
    @Select("select * from " + EMPLOYEE_TABLE + " where id = #{id} ")
    @Results({
            @Result(id = true, column = "id", property = "id", javaType = java.lang.Integer.class),
            @Result(column = "card_id", property = "cardId", javaType = java.lang.String.class),
            @Result(column = "post_code", property = "postCode", javaType = java.lang.String.class),
            @Result(column = "qq_num", property = "qqNum", javaType = java.lang.String.class),
            @Result(column = "create_date", property = "createDate", javaType = java.util.Date.class),
            @Result(column = "dept_id", property = "dept", one = @One(select = "om.entor.hrm.dao.DeptDAO.getById", fetchType = FetchType.EAGER)),
            @Result(column = "job_id", property = "job", one = @One(select = "om.entor.hrm.dao.JobDAO.getById", fetchType = FetchType.EAGER))
    })
    Employee getById(Integer id);

    /**
     * 根据id删除部门
     *
     * @param id
     */
    @Delete("delete from " + EMPLOYEE_TABLE + " where id = #{id} ")
    void deleteById(Integer id);

    /**
     * 动态插入用户
     *
     * @param Employee
     */
    @SelectProvider(type = EmployeeDynaSQLProvider.class, method = "insertEmployee")
    void save(Employee Employee);

    /**
     * 动态修改用户
     *
     * @param Employee
     */
    @SelectProvider(type = EmployeeDynaSQLProvider.class, method = "updateEmployee")
    void update(Employee Employee);
}
