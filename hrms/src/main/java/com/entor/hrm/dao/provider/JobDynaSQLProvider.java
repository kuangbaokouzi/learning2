package com.entor.hrm.dao.provider;

import com.entor.hrm.domain.Job;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

import java.util.Map;

import static com.entor.hrm.uitl.common.HrmConstants.JOB_TABLE;

public class JobDynaSQLProvider {

    /**
     * 分页动态查询工作
     *
     * @param params
     * @return
     */
    public String selectWithParams(Map<String, Object> params) {
        String sql = new SQL() {{
            SELECT("*");
            FROM(JOB_TABLE);
            if (params.get("job") != null) {
                Job job = (Job) params.get("job");
                if (!StringUtils.isEmpty(job.getName())) {
                    WHERE(" name like concat ('%',#{job.name},'%') ");
                }
            }
        }}.toString();
        if (params.get("pageModel") != null) {
            sql += " limit #{pageModel.firstLimitParam}, #{pageModel.pageSize} ";
        }
        return sql;
    }

    /**
     * 动态查询工作记录总数
     *
     * @param params
     * @return
     */
    public String count(Map<String, Object> params) {
        String sql = new SQL() {{
            SELECT("count(*)");
            FROM(JOB_TABLE);
            if (params.get("job") != null) {
                Job job = (Job) params.get("job");
                if (!StringUtils.isEmpty(job.getName())) {
                    WHERE(" name like concat ('%',#{job.name},'%') ");
                }
            }
        }}.toString();
        if (params.get("pageModel") != null) {
            sql += " limit #{pageModel.firstLimitParam}, #{pageModel.pageSize} ";
        }
        return sql;
    }

    /**
     * 动态插入
     *
     * @param job
     * @return
     */
    public String insertJob(Job job) {
        return new SQL() {{
            INSERT_INTO(JOB_TABLE);
            if (!StringUtils.isEmpty(job.getName())) {
                VALUES("name", "#{name}");
            }
            if (!StringUtils.isEmpty(job.getRemark())) {
                VALUES("remark", "#{remark}");
            }
            WHERE(" id=#{id}");
        }}.toString();
    }

    /**
     * 动态更新
     *
     * @param job
     * @return
     */
    public String updateJob(Job job) {
        return new SQL() {{
            UPDATE(JOB_TABLE);
            if (!StringUtils.isEmpty(job.getName())) {
                SET("name = #{name}");
            }
            if (!StringUtils.isEmpty(job.getRemark())) {
                SET("remark = #{remark}");
            }
            WHERE(" id=#{id}");
        }}.toString();
    }
}
