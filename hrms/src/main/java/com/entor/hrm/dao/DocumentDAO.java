package com.entor.hrm.dao;

import com.entor.hrm.dao.provider.DocumentDynaSQLProvider;
import com.entor.hrm.domain.Document;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

import static com.entor.hrm.uitl.common.HrmConstants.DOCUMENT_TABLE;

public interface DocumentDAO {

    /**
     * 动态查询文档
     *
     * @param params
     * @return {@link List<Document>}
     */
    @SelectProvider(type = DocumentDynaSQLProvider.class, method = "selectWithParams")
    List<Document> selectByPage(Map<String, Object> params);

    /**
     * 动态查询文档总记录数
     *
     * @param params
     * @return {@link Integer}
     */
    @SelectProvider(type = DocumentDynaSQLProvider.class, method = "count")
    Integer count(Map<String, Object> params);

    /**
     * 查询所有文档
     *
     * @return {@link List<Document>}
     */
    @Select("select * from " + DOCUMENT_TABLE + " ")
    List<Document> getAllDocument();

    /**
     * 根据id查询文档
     *
     * @param id
     * @return {@link Document}
     */
    @Select("select * from " + DOCUMENT_TABLE + " where id = #{id} ")
    Document getById(Integer id);

    /**
     * 根据id删除文档
     *
     * @param id
     */
    @Delete("delete from " + DOCUMENT_TABLE + " where id = #{id} ")
    void deleteById(Integer id);

    /**
     * 动态插入文档
     *
     * @param Document
     */
    @SelectProvider(type = DocumentDynaSQLProvider.class, method = "insertDocument")
    void save(Document Document);

    /**
     * 动态修改文档
     *
     * @param Document
     */
    @SelectProvider(type = DocumentDynaSQLProvider.class, method = "updateDocument")
    void update(Document Document);
}
