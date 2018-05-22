package com.entor.hrm.dao.provider;

import com.entor.hrm.domain.Employee;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

import java.util.Map;

import static com.entor.hrm.uitl.common.HrmConstants.EMPLOYEE_TABLE;

public class EmployeeDynaSQLProvider {

    /**
     * 分页动态查询员工
     *
     * @param params
     * @return
     */
    public String selectWithParams(Map<String, Object> params) {
        String sql = new SQL() {{
            SELECT("*");
            FROM(EMPLOYEE_TABLE);
            if (params.get("employee") != null) {
                Employee employee = (Employee) params.get("employee");
                if (employee.getDept() != null && employee.getDept().getId() != null && employee.getDept().getId() != 0) {
                    WHERE(" dept_id like concat ('%',#{employee.dept.id},'%') ");
                }
                if (employee.getJob() != null && employee.getJob().getId() != null && employee.getJob().getId() != 0) {
                    WHERE(" job_id like concat ('%',#{employee.job.id},'%') ");
                }
                if (!StringUtils.isEmpty(employee.getName())) {
                    WHERE(" name like concat ('%',#{employee.name},'%') ");
                }
                if (!StringUtils.isEmpty(employee.getPhone())) {
                    WHERE(" phone like concat ('%',#{employee.phone},'%') ");
                }
                if (!StringUtils.isEmpty(employee.getCardId())) {
                    WHERE(" card_id like concat ('%',#{employee.cardId},'%') ");
                }
                if (employee.getSex() != null) {
                    WHERE(" sex like concat ('%',#{employee.sex},'%') ");
                }
            }
        }}.toString();
        if (params.get("pageModel") != null) {
            sql += " limit #{pageModel.firstLimitParam}, #{pageModel.pageSize} ";
        }
        return sql;
    }

    /**
     * 动态查询员工总记录数
     *
     * @param params
     * @return
     */
    public String count(Map<String, Object> params) {
        String sql = new SQL() {{
            SELECT("count(*)");
            FROM(EMPLOYEE_TABLE);
            if (params.get("employee") != null) {
                Employee employee = (Employee) params.get("employee");
                if (employee.getDept() != null && employee.getDept().getId() != null && employee.getDept().getId() != 0) {
                    WHERE(" dept_id like concat ('%',#{employee.dept.id},'%') ");
                }
                if (employee.getJob() != null && employee.getJob().getId() != null && employee.getJob().getId() != 0) {
                    WHERE(" job_id like concat ('%',#{employee.job.id},'%') ");
                }
                if (!StringUtils.isEmpty(employee.getName())) {
                    WHERE(" name like concat ('%',#{employee.name},'%') ");
                }
                if (!StringUtils.isEmpty(employee.getPhone())) {
                    WHERE(" phone like concat ('%',#{employee.phone},'%') ");
                }
                if (!StringUtils.isEmpty(employee.getCardId())) {
                    WHERE(" card_id like concat ('%',#{employee.cardId},'%') ");
                }
                if (employee.getSex() != null) {
                    WHERE(" sex like concat ('%',#{employee.sex},'%') ");
                }
            }
        }}.toString();
        if (params.get("pageModel") != null) {
            sql += " limit #{pageModel.firstLimitParam}, #{pageModel.pageSize} ";
        }
        return sql;
    }

    /**
     * 动态插入员工
     *
     * @param employee
     * @return
     */
    public String insertEmployee(Employee employee) {
        return new SQL() {{
            INSERT_INTO(EMPLOYEE_TABLE);
            if (!StringUtils.isEmpty(employee.getName())) {
                VALUES("name", "#{name}");
            }
            if (!StringUtils.isEmpty(employee.getCardId())) {
                VALUES("card_id", "#{cardId}");
            }
            if (!StringUtils.isEmpty(employee.getAddress())) {
                VALUES("address", "#{address}");
            }
            if (!StringUtils.isEmpty(employee.getPostCode())) {
                VALUES("post_code", "#{postCode}");
            }
            if (!StringUtils.isEmpty(employee.getTel())) {
                VALUES("tel", "#{tel}");
            }
            if (!StringUtils.isEmpty(employee.getPhone())) {
                VALUES("phone", "#{phone}");
            }
            if (!StringUtils.isEmpty(employee.getQqNum())) {
                VALUES("qq_num", "#{qqNum}");
            }
            if (!StringUtils.isEmpty(employee.getEmail())) {
                VALUES("email", "#{email}");
            }
            if (employee.getSex() != null) {
                VALUES("sex", "#{sex}");
            }
            if (!StringUtils.isEmpty(employee.getParty())) {
                VALUES("party", "#{party}");
            }
            if (employee.getBirthday() != null) {
                VALUES("birthday", "#{birthday}");
            }
            if (!StringUtils.isEmpty(employee.getRace())) {
                VALUES("race", "#{race}");
            }
            if (!StringUtils.isEmpty(employee.getEducation())) {
                VALUES("education", "#{education}");
            }
            if (!StringUtils.isEmpty(employee.getSpeciality())) {
                VALUES("speciality", "#{speciality}");
            }
            if (!StringUtils.isEmpty(employee.getHobby())) {
                VALUES("hobby", "#{hobby}");
            }
            if (!StringUtils.isEmpty(employee.getRemark())) {
                VALUES("remark", "#{remark}");
            }
            if (employee.getCreateDate() != null) {
                VALUES("create_date", "#{createDate}");
            }
            if (employee.getDept() != null) {
                VALUES("dept_id", "#{dept.id}");
            }
            if (employee.getJob() != null) {
                VALUES("job_id", "#{job.id}");
            }

        }}.toString();
    }

    /**
     * 动态更新员工
     *
     * @param employee
     * @return
     */
    public String updateEmployee(Employee employee) {
        return new SQL() {{
            UPDATE(EMPLOYEE_TABLE);
            if (!StringUtils.isEmpty(employee.getName())) {
                SET("name = #{name}");
            }
            if (!StringUtils.isEmpty(employee.getCardId())) {
                SET("card_id = #{cardId}");
            }
            if (!StringUtils.isEmpty(employee.getAddress())) {
                SET("address = #{address}");
            }
            if (!StringUtils.isEmpty(employee.getPostCode())) {
                SET("post_code = #{postCode}");
            }
            if (!StringUtils.isEmpty(employee.getTel())) {
                SET("tel = #{tel}");
            }
            if (!StringUtils.isEmpty(employee.getPhone())) {
                SET("phone = #{phone}");
            }
            if (!StringUtils.isEmpty(employee.getQqNum())) {
                SET("qq_num = #{qqNum}");
            }
            if (!StringUtils.isEmpty(employee.getEmail())) {
                SET("email = #{email}");
            }
            if (employee.getSex() != null) {
                SET("sex = #{sex}");
            }
            if (!StringUtils.isEmpty(employee.getParty())) {
                SET("party = #{party}");
            }
            if (employee.getBirthday() != null) {
                SET("birthday = #{birthday}");
            }
            if (!StringUtils.isEmpty(employee.getRace())) {
                SET("race = #{race}");
            }
            if (!StringUtils.isEmpty(employee.getEducation())) {
                SET("education = #{education}");
            }
            if (!StringUtils.isEmpty(employee.getSpeciality())) {
                SET("speciality = #{speciality}");
            }
            if (!StringUtils.isEmpty(employee.getHobby())) {
                SET("hobby = #{hobby}");
            }
            if (!StringUtils.isEmpty(employee.getRemark())) {
                SET("remark = #{remark}");
            }
            if (employee.getCreateDate() != null) {
                SET("create_date = #{createDate}");
            }
            if (employee.getDept() != null) {
                SET("dept_id = #{dept.id}");
            }
            if (employee.getJob() != null) {
                SET("job_id = #{job.id}");
            }
            WHERE(" id=#{id} ");
        }}.toString();
    }
}
