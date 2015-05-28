package ase.cts.iulia;

import java.util.Date;

public class LendingSheet {

	public LendingSheet(User user, Book book, Date startDate, Date endDate) {
		super();
		this.user = user;
		this.book = book;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	public User user;
	public Book book;
	public Date startDate;
	public Date endDate;
	public User getUser() {
		return user;
	}
	
	
	public void setUser(User user) {
		this.user = user;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}


	@Override
	public String toString() {
		return "LendingSheet [user=" + user + ", book=" + book + ", startDate="
				+ startDate + ", endDate=" + endDate + "]";
	}
	
	
}
