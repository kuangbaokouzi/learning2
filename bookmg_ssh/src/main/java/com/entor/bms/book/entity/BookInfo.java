package com.entor.bms.book.entity;

import java.util.Objects;

public class BookInfo {
	private Integer bid;// 书籍编码
	private String bookName;// 书籍名称
	private Integer amount;// 数量
	private String thumbnail;// 书籍缩略图
	private Integer status;// 0表示下架，1表示上架

	public BookInfo() {
	}

	public Integer getBid() {
		return bid;
	}

	public void setBid(Integer bid) {
		this.bid = bid;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		BookInfo bookInfo = (BookInfo) o;
		return Objects.equals(bid, bookInfo.bid);
	}

	@Override
	public int hashCode() {

		return Objects.hash(bid);
	}

	@Override
	public String toString() {
		return "BookInfo [bid=" + bid + ", bookName=" + bookName + ", amount=" + amount + ", thumbnail=" + thumbnail
				+ ", status=" + status + "]";
	}
}
