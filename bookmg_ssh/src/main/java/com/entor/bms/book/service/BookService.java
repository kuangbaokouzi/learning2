package com.entor.bms.book.service;

import java.util.List;

import com.entor.bms.book.entity.BookInfo;
import com.entor.bms.user.entity.UserInfo;

public interface BookService {

	/**
	 * 根据指定的书籍编号，查询书籍记录
	 *
	 * @param bookId
	 *            指定书籍的编号，这个编号唯一指定一本数
	 * @return 指定书籍对象，如果没有对象，返回null
	 */
	BookInfo queryByBookId(Integer bookId);

	/**
	 * 查询所有的书籍信息
	 *
	 * @return 所有书籍的对象集合，如果没有对象，返回没有元素的集合（size=0）
	 */
	List<BookInfo> getAllBooks();

	/**
	 * 保存书籍对象
	 *
	 * @param bookInfo
	 *            书籍信息封装对象
	 */
	void saveBook(BookInfo bookInfo);

	/**
	 * 更新书籍信息，根据id定位旧的信息，替换成新的信息
	 *
	 * @param newBook
	 *            新的书籍对象
	 */
	void updateBookInfo(BookInfo newBook);

	/**
	 * 更新书籍名称，根据id定位旧书名，替换成新书名
	 *
	 * @param newBook
	 */
	void updateBookName(BookInfo newBook);

	/**
	 * 更新书籍数量，根据id定位旧书，替换成新数量
	 *
	 * @param newBook
	 */
	void updateBookAmount(BookInfo newBook);

	/**
	 * 根据id集合批量删除书籍信息
	 *
	 * @param ids
	 *            id集合
	 */
	void batchDeleteByIds(String[] ids);

	/**
	 * 无差别删除所有书籍信息
	 */
	void clearBooks();

	/**
	 * 根据id归还书籍
	 *
	 * @param ids
	 *            id集合
	 */
	void returnBooks(List<Integer> ids);

	List<BookInfo> searchBooksByName(String name);

	void updateStatusById(int bid, int status);

	void removeUserById(int bid);

	BookInfo getBookInfoById(int bid);

	void updateThumbnailById(Integer bid, String thumbnail);

	BookInfo getBookByIdAndStatus(int bid, int status);

	void rentBook(int bid, UserInfo userInfo);

	void returnBook(int bid, UserInfo userInfo);
}
