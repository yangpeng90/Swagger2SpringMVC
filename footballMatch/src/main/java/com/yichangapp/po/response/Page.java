package com.yichangapp.po.response;

public class Page {
	private Integer pageNO;
	private Integer pageSize;
	public Integer getStartRecord() {
		return (pageNO - 1) * pageSize;
	}
	public Integer getPageNO() {
		return pageNO;
	}
	public void setPageNO(Integer pageNO) {
		this.pageNO = pageNO;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
}
