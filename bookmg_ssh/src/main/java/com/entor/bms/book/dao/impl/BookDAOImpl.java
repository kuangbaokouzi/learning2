package com.entor.bms.book.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.entor.bms.book.dao.BookDAO;
import com.entor.bms.book.entity.BookInfo;
import com.entor.bms.utils.DBUtil;

public class BookDAOImpl implements BookDAO {
	private static final Logger LOGGER = LogManager.getLogger();

	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;

	@Override
	public List<BookInfo> getAll() {
		List<BookInfo> infoList = new ArrayList<>();
		try {
			// 1.获得数据库连接
			conn = DBUtil.getConnection();
			// 2.创建SQL句
			String sql = "select * from book_info";
			LOGGER.info("JDBC getAll() -> SQL = {}", sql);
			// 3.创建statement
			ps = conn.prepareStatement(sql);
			// 4.编译并执行SQL句，并将返回的数据保存到结果集对象中
			rs = ps.executeQuery();
			// 5.迭代结果集，处理每一条记录
			while (rs.next()) {
				BookInfo info = new BookInfo();
				info.setBid(rs.getInt("b_id"));
				info.setBookName(rs.getString("book_name"));
				info.setAmount(rs.getInt("amount"));
				info.setThumbnail(rs.getString("thumbnail"));
				info.setStatus(rs.getInt("status"));
				infoList.add(info);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.release(conn, ps, rs);
		}
		return infoList;
	}

	@Override
	public BookInfo selectByBookId(Integer bookId) {
		BookInfo info = null;
		try {
			// 1.获得数据库连接
			conn = DBUtil.getConnection();
			// 2.创建SQL句
			String sql = "select * from book_info where b_id = ?";
			LOGGER.info("JDBC selecBookInfoById() -> SQL = {}", sql);
			// 3.创建statement对象
			ps = conn.prepareStatement(sql);
			ps.setInt(1, bookId);
			// 4.编译并执行SQL句，并将返回的数据保存到结果集对象中
			rs = ps.executeQuery();
			// 5.迭代结果集，处理每一条记录
			while (rs.next()) {
				info = new BookInfo();
				info.setBid(rs.getInt("b_id"));
				info.setBookName(rs.getString("book_name"));
				info.setAmount(rs.getInt("amount"));
				info.setThumbnail(rs.getString("thumbnail"));
				info.setStatus(rs.getInt("status"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.release(conn, ps, rs);
		}
		return info;
	}

	@Override
	public BookInfo selectByIdAndStatus(int bid, int status) {
		BookInfo info = null;
		try {
			// 1.获得数据库连接
			conn = DBUtil.getConnection();
			// 2.创建SQL句
			String sql = "select * from book_info where b_id = ? and status=?";
			LOGGER.info("JDBC selecBookInfoById() -> SQL = {}", sql);
			// 3.创建statement对象
			ps = conn.prepareStatement(sql);
			ps.setInt(1, bid);
			ps.setInt(2, status);
			// 4.编译并执行SQL句，并将返回的数据保存到结果集对象中
			rs = ps.executeQuery();
			// 5.迭代结果集，处理每一条记录
			while (rs.next()) {
				info = new BookInfo();
				info.setBid(rs.getInt("b_id"));
				info.setBookName(rs.getString("book_name"));
				info.setAmount(rs.getInt("amount"));
				info.setThumbnail(rs.getString("thumbnail"));
				info.setStatus(rs.getInt("status"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.release(conn, ps, rs);
		}
		return info;
	}

	@Override
	public List<BookInfo> selectByBookName(String bookName) {
		List<BookInfo> infoList = new ArrayList<>();
		try {
			// 1.获得数据库连接
			conn = DBUtil.getConnection();
			// 2.创建SQL句
			String sql = "select * from book_info where book_name like ?";
			LOGGER.info("JDBC selecBookInfoById() -> SQL = {}", sql);
			// 3.创建statement对象
			ps = conn.prepareStatement(sql);
			ps.setString(1, "%" + bookName + "%");
			// 4.编译并执行SQL句，并将返回的数据保存到结果集对象中
			rs = ps.executeQuery();
			// 5.迭代结果集，处理每一条记录
			while (rs.next()) {
				BookInfo info = new BookInfo();
				info.setBid(rs.getInt("b_id"));
				info.setBookName(rs.getString("book_name"));
				info.setAmount(rs.getInt("amount"));
				info.setThumbnail(rs.getString("thumbnail"));
				info.setStatus(rs.getInt("status"));
				infoList.add(info);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.release(conn, ps, rs);
		}
		return infoList;
	}

	@Override
	public Integer getMaxId() {
		return 0;
	}

	@Override
	public void insertBook(BookInfo bookInfo) {
		try {
			// 1.获得数据库连接
			conn = DBUtil.getConnection();
			// 2.创建SQL句
			String sql = "insert into book_info(book_name,amount,thumbnail,status) values(?,?,?,?)";
			LOGGER.info("JDBC insertBook() -> SQL = {}", sql);
			// 3.创建statement对象
			ps = conn.prepareStatement(sql);
			ps.setString(1, bookInfo.getBookName());
			ps.setInt(2, bookInfo.getAmount());
			ps.setString(3, bookInfo.getThumbnail());
			ps.setInt(4, bookInfo.getStatus());
			// 4.编译并执行SQL句，并将返回的数据保存到结果集对象中
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.release(conn, ps, null);
		}
	}

	@Override
	public void deleteBook(Integer bid) {
		try {
			// 1.获得数据库连接
			conn = DBUtil.getConnection();
			// 2.创建SQL句
			String sql = "delete from book_info where b_id=?";
			LOGGER.info("JDBC deleteBook() -> SQL = {}", sql);
			// 3.创建statement对象
			ps = conn.prepareStatement(sql);
			ps.setInt(1, bid);
			// 4.编译并执行SQL句，并将返回的数据保存到结果集对象中
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.release(conn, ps, null);
		}
	}

	@Override
	public void updateStatusById(int bid, int status) {
		try {
			// 1.获得数据库连接
			conn = DBUtil.getConnection();
			// 2.创建SQL句
			String sql = "update book_info set status=? where b_id=?";
			LOGGER.info("JDBC updateStatusById() -> SQL = {}", sql);
			// 3.创建statement对象
			ps = conn.prepareStatement(sql);
			ps.setInt(1, status);
			ps.setInt(2, bid);
			// 4.编译并执行SQL句，并将返回的数据保存到结果集对象中
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.release(conn, ps, null);
		}
	}

	@Override
	public void updateBookInfo(BookInfo newBook) {
		try {
			// 1.获得数据库连接
			conn = DBUtil.getConnection();
			// 2.创建SQL句
			String sql = "update book_info set book_name=?, amount=? status=? where b_id=?";
			LOGGER.info("JDBC updateBookInfo() -> SQL = {}", sql);
			// 3.创建statement对象
			ps = conn.prepareStatement(sql);
			ps.setString(1, newBook.getBookName());
			ps.setInt(2, newBook.getAmount());
			ps.setInt(3, newBook.getStatus());
			ps.setInt(4, newBook.getBid());
			// 4.编译并执行SQL句，并将返回的数据保存到结果集对象中
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.release(conn, ps, null);
		}
	}

	@Override
	public void updateThumbnailById(Integer bid, String thumbnail) {
		try {
			// 1.获得数据库连接
			conn = DBUtil.getConnection();
			// 2.创建SQL句
			String sql = "update book_info set thumbnail=? where b_id=?";
			LOGGER.info("JDBC updateThumbnailById() -> SQL = {}", sql);
			// 3.创建statement对象
			ps = conn.prepareStatement(sql);
			ps.setString(1, thumbnail);
			ps.setInt(2, bid);
			// 4.编译并执行SQL句，并将返回的数据保存到结果集对象中
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.release(conn, ps, null);
		}
	}

	@Override
	public void batchDeleteByIds(String[] ids) {
		try {
			// 1.获得数据库连接
			conn = DBUtil.getConnection();
			// 2.创建SQL句
			String sql = "delete from book_info where b_id=?";
			LOGGER.info("JDBC batchDeleteByIds() -> SQL = {}", sql);
			// 3.创建statement对象
			ps = conn.prepareStatement(sql);
			for (String bid : ids) {
				ps.setInt(1, Integer.parseInt(bid));
				ps.addBatch();
			}
			// 4.编译并执行SQL句，并将返回的数据保存到结果集对象中
			ps.executeBatch();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.release(conn, ps, null);
		}
	}

	@Override
	public void reduceBookAmount(int bid) {
		try {
			// 1.获得数据库连接
			conn = DBUtil.getConnection();
			// 2.创建SQL句
			String sql = "update book_info set amount = amount - 1 where b_id=?";
			LOGGER.info("JDBC updateBookAmoundById() -> SQL = {}", sql);
			// 3.创建statement对象
			ps = conn.prepareStatement(sql);
			ps.setInt(1, bid);
			// 4.编译并执行SQL句，并将返回的数据保存到结果集对象中
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.release(conn, ps, null);
		}
	}

	@Override
	public void increaseBookAmount(int bid) {
		try {
			// 1.获得数据库连接
			conn = DBUtil.getConnection();
			// 2.创建SQL句
			String sql = "update book_info set amount = amount + 1 where b_id=?";
			LOGGER.info("JDBC updateBookAmoundById() -> SQL = {}", sql);
			// 3.创建statement对象
			ps = conn.prepareStatement(sql);
			ps.setInt(1, bid);
			// 4.编译并执行SQL句，并将返回的数据保存到结果集对象中
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.release(conn, ps, null);
		}
	}

}
