package com.spittr.tools.page;

import java.io.Serializable;

import com.spittr.config.StaticConfig;


public class Page implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7856383139803646546L;

	private Integer itemPerPage;
	
	private Integer pageNum;
	
	private Long item;
	
	private Integer page;
	
	private Integer ItemInThisPage;
	
	private Integer firstItem;
	
	public static Page newInstance(Long item){
		return new Page(item);
	}
	
	public Page() {
		// TODO Auto-generated constructor stub
	}
	
	public Page(Long item){
		this(item, null);
	}
	
	public Page(Long item, Integer itemPerPage){
		this.item = item;
		this.itemPerPage = itemPerPage == null ? StaticConfig.ITEM_PER_PAGE : itemPerPage;
		this.pageNum = (int)Math.ceil((double)item / this.itemPerPage);
	}
	
	public Integer getFirst(){
		this.firstItem = this.itemPerPage *( this.page == null?0: this.page);
		return firstItem;
	}
	
	public Integer getItemPerPage() {
		return itemPerPage;
	}

	public void setItemPerPage(Integer itemPerPage) {
		this.itemPerPage = itemPerPage;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Long getItem() {
		return item;
	}

	public void setItem(Long item) {
		this.item = item;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getFirstItem() {
		return firstItem;
	}

	public void setFirstItem(Integer firstItem) {
		this.firstItem = firstItem;
	}

	@Override
	public String toString() {
		return "Page [itemPerPage=" + itemPerPage + ", pageNum=" + pageNum + ", item=" + item + ", page=" + page
				+ ", firstItem=" + firstItem + "]";
	}

	public Integer getItemInThisPage() {
		return ItemInThisPage;
	}

	public void setItemInThisPage(Integer itemInThisPage) {
		ItemInThisPage = itemInThisPage;
	}
	
}
