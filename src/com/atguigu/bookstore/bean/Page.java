package com.atguigu.bookstore.bean;

import java.io.Serializable;
import java.util.List;

public class Page<T> implements Serializable{
	private static final long serialVersionUID = 1L;
	private String path;
	private List<T> data;
	private int pageNumber;
	private int totalCount;
	private int totalPage;
	private int size;
	private int index;
	
	public Page() {
		super();
	}
	public Page(List<T> data, int pageNumber, int totalCount, int totalPage, int size, int index) {
		super();
		this.data = data;
		this.pageNumber = pageNumber;
		this.totalCount = totalCount;
		this.totalPage = totalPage;
		this.size = size;
		this.index = index;
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	public int getPageNumber() {
		if(pageNumber<1) {
			pageNumber = 1;
		}else if(pageNumber>getTotalPage()) {
			pageNumber = getTotalPage();
		}
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getTotalPage() {
		if(totalCount%size==0) {
			totalPage=totalCount/size;
		}else {
			totalPage=totalCount/size+1;
		}
		return totalPage;
	}
	/* 不需要手动设值的set方法取消 计算得到
	 * public void setTotalPage(int totalPage) {
	 * this.totalPage = totalPage;
	}*/
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getIndex() {
		index = size*(getPageNumber()-1);
		return index;
	}
	/*public void setIndex(int index) {
		this.index = index;
	}*/
	@Override
	public String toString() {
		return "Page [data=" + data + ", pageNumber=" + pageNumber + ", totalCount=" + totalCount + ", totalPage="
				+ getTotalPage() + ", size=" + size + ", index=" + getIndex() + "]";
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}

}
