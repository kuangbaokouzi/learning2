package com.entor.hrm.dao.provider;

import com.entor.hrm.domain.User;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

import java.util.Map;

import static com.entor.hrm.uitl.common.HrmConstants.USER_TABLE;

public class UserDynaSQLProvider {
    /**
     * 分页动态查询
     *
     * @param params
     * @return
     */
    public String selectWithParam(Map<String, Object> params) {
        String sql = new SQL() {
            {
                SELECT("*");
                FROM(USER_TABLE);
                if (params.get("user") != null) {
                    User user = (User) params.get("user");
                    if (!StringUtils.isEmpty(user.getLoginName())) {
                        WHERE(" username like concat ('%',#{user.username},'%') ");
                    }
                    if (user.getStatus() != null) {
                        WHERE(" status like concat ('%',#{user.status},'%') ");
                    }
                }
            }
        }.toString();
        if (params.get("pageModel") != null) {
            sql += " limit #{pageModel.firstLimitParam}, #{pageModel.pageSize} ";
        }
        return sql;
    }

    /**
     * 动态查询总记录数
     *
     * @param params
     * @return
     */
    public String count(Map<String, Object> params) {
        String sql = new SQL() {
            {
                SELECT("count(*)");
                FROM(USER_TABLE);
                if (params.get("user") != null) {
                    User user = (User) params.get("user");
                    if (!StringUtils.isEmpty(user.getLoginName())) {
                        WHERE(" username like concat ('%',#{user.username},'%') ");
                    }
                    if (user.getStatus() != null) {
                        WHERE(" status like concat ('%',#{user.status},'%') ");
                    }
                }
            }
        }.toString();
        if (params.get("pageModel") != null) {
            sql += " limit #{pageModel.firstLimitParam}, #{pageModel.pageSize} ";
        }
        return sql;
    }

    /**
     * 动态插入
     *
     * @param user
     * @return
     */
    public String insertUser(User user) {
        return new SQL() {{
            INSERT_INTO(USER_TABLE);
            if (!StringUtils.isEmpty(user.getUsername())) {
                VALUES("username", "#{username}");
            }
            if (user.getStatus() != null) {
                VALUES("status", "#{status}");
            }
            if (!StringUtils.isEmpty(user.getLoginName())) {
                VALUES("login_name", "#{loginName}");
            }
            if (!StringUtils.isEmpty(user.getPassword())) {
                VALUES("password", "#{password}");
            }
        }}.toString();
    }

    /**
     * 动态更新
     *
     * @param user
     * @return
     */
    public String updateUser(User user) {
        return new SQL() {{
            UPDATE(USER_TABLE);
            if (!StringUtils.isEmpty(user.getUsername())) {
                SET("username = #{username}");
            }
            if (user.getStatus() != null) {
                SET("status = #{status}");
            }
            if (!StringUtils.isEmpty(user.getLoginName())) {
                SET("login_name = #{loginName}");
            }
            if (!StringUtils.isEmpty(user.getPassword())) {
                SET("password = #{password}");
            }
            if (user.getCreateData() != null) {
                SET("create_date = #{createDate}");
            }
            WHERE(" id = #{id}");
        }}.toString();
    }
}
