package com.javaex.vo;

public class BoardVo {
	int no;
	String title;
	String name;
	int count;
	String regDate;
	String content;
	int userNo;
	public BoardVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BoardVo(int no, String title, String name, int count, String regDate, String content, int userNo) {
		super();
		this.no = no;
		this.title = title;
		this.name = name;
		this.count = count;
		this.regDate = regDate;
		this.content = content;
		this.userNo = userNo;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getregDate() {
		return regDate;
	}
	public void setregDate(String regDate) {
		this.regDate = regDate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	@Override
	public String toString() {
		return "BoardVo [no=" + no + ", title=" + title + ", name=" + name + ", count=" + count + ", regDate=" + regDate
				+ ", content=" + content + ", userNo=" + userNo + "]";
	}
	
	
	
	
	

}
