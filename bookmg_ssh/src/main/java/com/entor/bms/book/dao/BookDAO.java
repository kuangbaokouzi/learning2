package com.entor.bms.book.dao;

import java.util.List;

import com.entor.bms.book.entity.BookInfo;

public interface BookDAO {
    /**
     * 获得所有的书籍记录
     *
     * @return
     */
    List<BookInfo> getAll();

    /**
     * 获得最大的书籍编号
     *
     * @return 书籍编号对应的整数
     */
    Integer getMaxId();

    /**
     * 插入书籍对象
     *
     * @param bookInfo 从服务层传递过来需要保存的书籍对象
     */
    void insertBook(BookInfo bookInfo);

    /**
     * 根据id查询书籍信息
     *
     * @param bookId
     * @return
     */
    BookInfo selectByBookId(Integer bookId);

    /**
     * 根据书名查询书籍信息
     *
     * @param bookName
     * @return
     */
    List<BookInfo> selectByBookName(String bookName);
    
    /**
     * 根据id删除书籍
     * @param bid
     */
    void deleteBook(Integer bid);

	void updateStatusById(int bid, int status);

	void updateBookInfo(BookInfo newBook);

	void updateThumbnailById(Integer bid, String thumbnail);

	void batchDeleteByIds(String[] ids);

	BookInfo selectByIdAndStatus(int bid, int status);

	void reduceBookAmount(int bid);

	void increaseBookAmount(int bid);
}
