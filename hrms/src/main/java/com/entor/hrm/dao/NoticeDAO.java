package com.entor.hrm.dao;

import com.entor.hrm.dao.provider.NoticeDynaSQLProvider;
import com.entor.hrm.domain.Notice;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

import static com.entor.hrm.uitl.common.HrmConstants.NOTICE_TABLE;

public interface NoticeDAO {

    /**
     * 动态查询通知
     *
     * @param params
     * @return {@link List<Notice>}
     */
    @SelectProvider(type = NoticeDynaSQLProvider.class, method = "selectWithParams")
    List<Notice> selectByPage(Map<String, Object> params);

    /**
     * 动态查询通知总记录数
     *
     * @param params
     * @return {@link Integer}
     */
    @SelectProvider(type = NoticeDynaSQLProvider.class, method = "count")
    Integer count(Map<String, Object> params);

    /**
     * 查询所有用户
     *
     * @return {@link List<Notice>}
     */
    @Select("select * from " + NOTICE_TABLE + " ")
    List<Notice> getAllNotice();

    /**
     * 根据id查询通知
     *
     * @param id
     * @return {@link Notice}
     */
    @Select("select * from " + NOTICE_TABLE + " where id = #{id} ")
    Notice getById(Integer id);

    /**
     * 根据id删除通知
     *
     * @param id
     */
    @Delete("delete from " + NOTICE_TABLE + " where id = #{id} ")
    void deleteById(Integer id);

    /**
     * 动态插入用户
     *
     * @param Notice
     */
    @SelectProvider(type = NoticeDynaSQLProvider.class, method = "insertNotice")
    void save(Notice Notice);

    /**
     * 动态修改用户
     *
     * @param Notice
     */
    @SelectProvider(type = NoticeDynaSQLProvider.class, method = "updateNotice")
    void update(Notice Notice);
}
