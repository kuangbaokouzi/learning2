package com.entor.bms.book.service.impl;

import java.util.List;

import com.entor.bms.book.dao.BookDAO;
import com.entor.bms.book.dao.impl.BookDAOImpl;
import com.entor.bms.book.entity.BookInfo;
import com.entor.bms.book.service.BookService;
import com.entor.bms.user.entity.UserInfo;
import com.entor.bms.user.service.UserService;
import com.entor.bms.user.service.impl.UserServiceImpl;

public class BookServiceImpl implements BookService {
	private BookDAO bookDAO = new BookDAOImpl();
	private UserService userService = new UserServiceImpl();

	@Override
	public List<BookInfo> getAllBooks() {
		return bookDAO.getAll();
	}

	@Override
	public BookInfo queryByBookId(Integer bookId) {
		return bookDAO.selectByBookId(bookId);
	}

	@Override
	public void saveBook(BookInfo bookInfo) {
		bookInfo.setStatus(0);
		bookDAO.insertBook(bookInfo);
	}

	@Override
	public void updateBookInfo(BookInfo newBook) {
		bookDAO.updateBookInfo(newBook);
	}

	@Override
	public void updateBookName(BookInfo newBook) {
	}

	@Override
	public void updateBookAmount(BookInfo newBook) {
	}

	@Override
	public void batchDeleteByIds(String[] ids) {
		bookDAO.batchDeleteByIds(ids);
	}

	@Override
	public void clearBooks() {
	}

	@Override
	public void returnBooks(List<Integer> ids) {
	}

	@Override
	public List<BookInfo> searchBooksByName(String bookName) {
		return bookDAO.selectByBookName(bookName);
	}

	@Override
	public void updateStatusById(int bid, int status) {
		bookDAO.updateStatusById(bid, status);
	}

	@Override
	public void removeUserById(int bid) {
		bookDAO.deleteBook(bid);
	}

	@Override
	public BookInfo getBookInfoById(int bid) {
		return bookDAO.selectByBookId(bid);
	}

	@Override
	public void updateThumbnailById(Integer bid, String thumbnail) {
		bookDAO.updateThumbnailById(bid, thumbnail);
	}

	@Override
	public BookInfo getBookByIdAndStatus(int bid, int status) {
		return bookDAO.selectByIdAndStatus(bid, status);
	}

	@Override
	public void rentBook(int bid, UserInfo userInfo) {
		userInfo.getRentBooks().add(bid);
		userService.updateRentBooksById(userInfo);

		bookDAO.reduceBookAmount(bid);
	}

	@Override
	public void returnBook(int bid, UserInfo userInfo) {
		userInfo.getRentBooks().remove(new Integer(bid));
		userService.updateRentBooksById(userInfo);

		bookDAO.increaseBookAmount(bid);
	}

}