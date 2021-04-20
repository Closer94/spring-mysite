package com.bitacademy.mysite.vo;

public class ReplyBoardVo {
	private int no;
	private String title;
	private String content;
	private String writerId;
	private int viewCnt;
	private String regDate;
	private int group_no;
	private int order_no;
	private int depth;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriterId() {
		return writerId;
	}
	public void setWriterId(String writerId) {
		this.writerId = writerId;
	}
	public int getViewCnt() {
		return viewCnt;
	}
	public void setViewCnt(int viewCnt) {
		this.viewCnt = viewCnt;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public int getGroup_no() {
		return group_no;
	}
	public void setGroup_no(int group_no) {
		this.group_no = group_no;
	}
	public int getOrder_no() {
		return order_no;
	}
	public void setOrder_no(int order_no) {
		this.order_no = order_no;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	@Override
	public String toString() {
		return "ReplyBoardVo [no=" + no + ", title=" + title + ", content=" + content + ", writerId=" + writerId
				+ ", viewCnt=" + viewCnt + ", regDate=" + regDate + ", group_no=" + group_no + ", order_no=" + order_no
				+ ", depth=" + depth + "]";
	}
	
	
}
