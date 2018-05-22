package com.entor.hrm.service;


import com.entor.hrm.domain.Notice;
import com.entor.hrm.domain.Notice;

import java.util.List;

public interface NoticeService {

    /**
     * 根据id查询通知
     *
     * @param id
     * @return
     */
    Notice findNoticeById(Integer id);

    /**
     * 分页查询通知
     *
     * @param notice
     * @param pageModel
     * @return {@link List<Notice>}
     */
    List<Notice> findNotice(Notice notice, PageModel<Notice> pageModel);

    /**
     * 根据id删除通知
     *
     * @param id
     */
    void removeNoticeById(Integer id);

    /**
     * 修改通知
     *
     * @param notice
     */
    void modifyNotice(Notice notice);

    /**
     * 添加通知
     *
     * @param notice
     */
    void addNotice(Notice notice);
}
