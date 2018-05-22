package com.entor.hrm.dao.provider;

import com.entor.hrm.domain.Notice;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

import java.util.Map;

import static com.entor.hrm.uitl.common.HrmConstants.NOTICE_TABLE;

public class NoticeDynaSQLProvider {
    /**
     * 分页动态查询通知
     *
     * @param params
     * @return
     */
    public String selectWithParams(Map<String, Object> params) {
        String sql = new SQL() {{
            SELECT("*");
            FROM(NOTICE_TABLE);
            if (params.get("notice") != null) {
                Notice notice = (Notice) params.get("notice");
                if (!StringUtils.isEmpty(notice.getTitle())) {
                    WHERE(" title like concat ('%',#{notice.title},'%') ");
                }
                if (!StringUtils.isEmpty(notice.getContent())) {
                    WHERE(" content like concat ('%',#{notice.content},'%') ");
                }
            }
        }}.toString();
        if (params.get("pageModel") != null) {
            sql += " limit #{pageModel.firstLimitParam}, #{pageModel.pageSize} ";
        }
        return sql;
    }

    /**
     * 动态查询通知总记录数
     *
     * @param params
     * @return
     */
    public String count(Map<String, Object> params) {
        String sql = new SQL() {{
            SELECT("count(*)");
            FROM(NOTICE_TABLE);
            if (params.get("notice") != null) {
                Notice notice = (Notice) params.get("notice");
                if (!StringUtils.isEmpty(notice.getTitle())) {
                    WHERE(" title like concat ('%',#{notice.title},'%') ");
                }
                if (!StringUtils.isEmpty(notice.getContent())) {
                    WHERE(" content like concat ('%',#{notice.content},'%') ");
                }
            }
        }}.toString();
        if (params.get("pageModel") != null) {
            sql += " limit #{pageModel.firstLimitParam}, #{pageModel.pageSize} ";
        }
        return sql;
    }

    /**
     * 动态插入通知
     *
     * @param notice
     * @return
     */
    public String insertNotice(Notice notice) {
        return new SQL() {{
            INSERT_INTO(NOTICE_TABLE);
            if (!StringUtils.isEmpty(notice.getTitle())) {
                VALUES("title", "#{title}");
            }
            if (!StringUtils.isEmpty(notice.getContent())) {
                VALUES("content", "#{content}");
            }
            if (notice.getUser() != null && notice.getUser().getId() != null) {
                VALUES("user_id", "#{user.id}");
            }
        }}.toString();
    }

    /**
     * 动态更新通知
     *
     * @param notice
     * @return
     */
    public String updateNotice(Notice notice) {
        return new SQL() {{
            INSERT_INTO(NOTICE_TABLE);
            if (!StringUtils.isEmpty(notice.getTitle())) {
                SET("title=#{title}");
            }
            if (!StringUtils.isEmpty(notice.getContent())) {
                SET("content=#{content}");
            }
            if (notice.getUser() != null && notice.getUser().getId() != null) {
                SET("user_id=#{user.id}");
            }
            WHERE(" id=#{id} ");
        }}.toString();
    }
}
