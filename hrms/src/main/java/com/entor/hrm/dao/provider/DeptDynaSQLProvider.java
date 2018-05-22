package com.entor.hrm.dao.provider;

import com.entor.hrm.domain.Dept;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

import java.util.Map;

import static com.entor.hrm.uitl.common.HrmConstants.DEPT_TABLE;

public class DeptDynaSQLProvider {

    /**
     * 分页动态查询
     *
     * @param params
     * @return
     */
    public String selectWithParams(Map<String, Object> params) {
        String sql = new SQL() {{
            SELECT("*");
            FROM(DEPT_TABLE);
            if (params.get("dept") != null) {
                Dept dept = (Dept) params.get("dept");
                if (!StringUtils.isEmpty(dept.getName())) {
                    WHERE(" name like concat ('%',#{dept.name},'%') ");
                }
            }
        }}.toString();
        if (params.get("pageModel") != null) {
            sql += " limit #{pageModel.firstLimitParam}, #{pageModel.pageSize} ";
        }
        return sql;
    }

    /**
     * 动态查询部门总记录数
     *
     * @param params
     * @return
     */
    public String count(Map<String, Object> params) {
        String sql = new SQL() {{
            SELECT("count(*)");
            FROM(DEPT_TABLE);
            if (params.get("dept") != null) {
                Dept dept = (Dept) params.get("dept");
                if (!StringUtils.isEmpty(dept.getName())) {
                    WHERE(" name like concat ('%',#{dept.name},'%') ");
                }
            }
        }}.toString();
        if (params.get("pageModel") != null) {
            sql += " limit #{pageModel.firstLimitParam}, #{pageModel.pageSize} ";
        }
        return sql;
    }

    /**
     * 动态插入部门
     *
     * @param dept
     * @return
     */
    public String insertDept(Dept dept) {
        return new SQL() {{
            INSERT_INTO(DEPT_TABLE);
            if (!StringUtils.isEmpty(dept.getName())) {
                VALUES("name", "#{dept.name}");
            }
            if (!StringUtils.isEmpty(dept.getRemark())) {
                VALUES("remark", "#{dept.remark}");
            }
        }}.toString();
    }

    /**
     * 动态更新部门
     *
     * @param dept
     * @return
     */
    public String updateDept(Dept dept) {
        return new SQL() {
            {
                UPDATE(DEPT_TABLE);
                if (!StringUtils.isEmpty(dept.getName())) {
                    SET(" name=#{dept.name} ");
                }
                if (!StringUtils.isEmpty(dept.getRemark())) {
                    SET(" remark=#{dept.remark} ");
                }
                WHERE(" id=#{dept.id} ");
            }
        }.toString();
    }
}
