package com.example.storyapp.dto;

import java.util.List;

public class PageResponseDTO <T>{
	//region 0. Constant
	//endregion
	//region 1. Attribute
	private List<T>items;
	private int currentPage;
	private int pageSize;
	private long totalItems;
	private int totalPages;
	//endregion
	//region 2.Constructors
	public PageResponseDTO(){
	}
	public PageResponseDTO(
			List<T> items,
			int currentPage,
			int pageSize,
			long totalItems,
			int totalPages){
		this.items=items;
		this.currentPage=currentPage;
		this.pageSize=pageSize;
		this.totalItems=totalItems;
		this.totalPages=totalPages;
	}
	//endregion
	//region 3. Getter and Setter
	public List<T> getItems() {
		return items;
	}
	public void setItems(List<T> items) {
		this.items = items;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public long getTotalItems() {
		return totalItems;
	}
	public void setTotalItems(long totalItems) {
		this.totalItems = totalItems;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	//endregion
}
