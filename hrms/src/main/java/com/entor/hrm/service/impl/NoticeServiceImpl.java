package com.entor.hrm.service.impl;

import com.entor.hrm.dao.NoticeDAO;
import com.entor.hrm.domain.Notice;
import com.entor.hrm.service.NoticeService;
import com.entor.hrm.service.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("noticeService")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeDAO noticeDAO;

    @Transactional(readOnly = true)
    @Override
    public Notice findNoticeById(Integer id) {
        return noticeDAO.getById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Notice> findNotice(Notice notice, PageModel<Notice> pageModel) {
        // 分页参数
        Map<String, Object> params = new HashMap<>();
        params.put("notice", notice);
        int recordCount = noticeDAO.count(params);
        pageModel.setRecordCount(recordCount);
        // 分页
        if (recordCount > 0) {
            params.put("pageModel", pageModel);
        }
        return noticeDAO.selectByPage(params);
    }

    @Override
    public void removeNoticeById(Integer id) {
        noticeDAO.deleteById(id);
    }

    @Override
    public void modifyNotice(Notice notice) {
        noticeDAO.update(notice);
    }

    @Override
    public void addNotice(Notice notice) {
        noticeDAO.save(notice);
    }
}
