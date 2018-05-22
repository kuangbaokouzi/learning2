package com.entor.hrm.service;


import com.entor.hrm.domain.Document;

import java.util.List;

public interface DocumentService {

    /**
     * 根据id查询文件
     *
     * @param id
     * @return
     */
    Document findDocumentById(Integer id);

    /**
     * 分页查询文件
     *
     * @param document
     * @param pageModel
     * @return {@link List<Document>}
     */
    List<Document> findDocument(Document document, PageModel<Document> pageModel);

    /**
     * 根据id删除文件
     *
     * @param id
     */
    void removeDocumentById(Integer id);

    /**
     * 修改文件
     *
     * @param document
     */
    void modifyDocument(Document document);

    /**
     * 添加文件
     *
     * @param document
     */
    void addDocument(Document document);
}
