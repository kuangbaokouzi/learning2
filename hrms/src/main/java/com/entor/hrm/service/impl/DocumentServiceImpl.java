package com.entor.hrm.service.impl;

import com.entor.hrm.dao.DocumentDAO;
import com.entor.hrm.domain.Document;
import com.entor.hrm.service.DocumentService;
import com.entor.hrm.service.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("documentService")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    private DocumentDAO documentDAO;

    @Transactional(readOnly = true)
    @Override
    public Document findDocumentById(Integer id) {
        return documentDAO.getById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Document> findDocument(Document document, PageModel<Document> pageModel) {
        // 分页参数
        Map<String, Object> params = new HashMap<>();
        params.put("document", document);
        int recordCount = documentDAO.count(params);
        pageModel.setRecordCount(recordCount);
        // 分页
        if (recordCount > 0) {
            params.put("pageModel", pageModel);
        }
        return documentDAO.selectByPage(params);
    }

    @Override
    public void removeDocumentById(Integer id) {
        documentDAO.deleteById(id);
    }

    @Override
    public void modifyDocument(Document document) {
        documentDAO.update(document);
    }

    @Override
    public void addDocument(Document document) {
        documentDAO.save(document);
    }
}
