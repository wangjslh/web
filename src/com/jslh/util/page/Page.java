package com.jslh.util.page;

import java.util.ArrayList;
import java.util.List;

public class Page<T> {

	private int pageNo = 1;
	private int pageSize = 10;
	private List<T> results = new ArrayList<T>();
	private int totalCount = 0;
	
	public Page(Integer pageNo, Integer pageSize) {
		if(pageNo != null && pageNo > 0){
			this.pageNo = pageNo;
		}
		if(pageSize != null && pageSize > 0){
			this.pageSize = pageSize;
		}
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public List<T> getResults() {
		return results;
	}

	public void setResults(List<T> results) {
		this.results = results;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	
}
