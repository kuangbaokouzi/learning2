package com.entor.hrm.dao;

import com.entor.hrm.dao.provider.JobDynaSQLProvider;
import com.entor.hrm.domain.Job;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

import static com.entor.hrm.uitl.common.HrmConstants.JOB_TABLE;

public interface JobDAO {

    /**
     * 动态查询部门
     *
     * @param params
     * @return {@link List<Job>}
     */
    @SelectProvider(type = JobDynaSQLProvider.class, method = "selectWithParams")
    List<Job> selectByPage(Map<String, Object> params);

    /**
     * 动态查询部门总记录数
     *
     * @param params
     * @return {@link Integer}
     */
    @SelectProvider(type = JobDynaSQLProvider.class, method = "count")
    Integer count(Map<String, Object> params);

    /**
     * 查询所有用户
     *
     * @return {@link List<Job>}
     */
    @Select("select * from " + JOB_TABLE + " ")
    List<Job> getAllJob();

    /**
     * 根据id查询部门
     *
     * @param id
     * @return {@link Job}
     */
    @Select("select * from " + JOB_TABLE + " where id = #{id} ")
    Job getById(Integer id);

    /**
     * 根据id删除部门
     *
     * @param id
     */
    @Delete("delete from " + JOB_TABLE + " where id = #{id} ")
    void deleteById(Integer id);

    /**
     * 动态插入用户
     *
     * @param Job
     */
    @SelectProvider(type = JobDynaSQLProvider.class, method = "insertJob")
    void save(Job Job);

    /**
     * 动态修改用户
     *
     * @param Job
     */
    @SelectProvider(type = JobDynaSQLProvider.class, method = "updateJob")
    void update(Job Job);
}
