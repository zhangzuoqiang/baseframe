/*
 * $Log: PageInfoBean.java,v $
 * Revision 1.1  2012/05/23 09:27:48  guor
 * 初次提交
 *
 * Revision 1.1  2011/06/01 05:46:55  kkaiwen
 * 2011.6.1 create
 *
 */
package cn.hs.core.page;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * Title: PageInfoBean<br>
 * Description: 分页信息<br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author MengW
 * @createDate 2011-5-17
 * @version $Revision: 1.1 $
 */
public class PageInfoBean {
	protected int first;
	protected int last;
	protected int previous;
	protected int next;
	protected int current;
	protected int pageCount;
	protected int rowCount;
	protected int start;
	protected int end;
	protected int count;
	protected Collection<PageBean> pageNumList;
	protected int pageListCount;
	protected int previousBlock;
	protected int nextBlock;

	public PageInfoBean() {
		this.first = 1;
		this.last = 1;
		this.previous = 1;
		this.next = 1;
		this.current = 1;
		this.pageCount = 1;
		this.rowCount = 10;
		this.start = 0;
		this.end = 1;
		this.count = 0;

		this.pageNumList = new ArrayList<PageBean>();
		this.pageListCount = 10;
		this.previousBlock = 1;
		this.nextBlock = 1;
	}

	public int getPageListCount() {
		return this.pageListCount;
	}

	public void setPageListCount(int pageListCount) {
		this.pageListCount = pageListCount;
	}

	public int getPreviousBlock() {
		return this.previousBlock;
	}

	public void setPreviousBlock(int previousBlock) {
		this.previousBlock = previousBlock;
	}

	public int getNextBlock() {
		return this.nextBlock;
	}

	public void setNextBlock(int nextBlock) {
		this.nextBlock = nextBlock;
	}

	public Collection<PageBean> getPageNumList() {
		return this.pageNumList;
	}

	public void setPageNumList(PageBean[] pages) {
		this.pageNumList.addAll(Arrays.asList(pages));
	}

	public int getCount() {
		return this.count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getStart() {
		return this.start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return this.end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public int getCurrent() {
		return this.current;
	}

	public void setCurrent(int current) {
		if (current < 1) {
			current = 1;
		}
		this.current = current;
	}

	public int getPrevious() {
		return this.previous;
	}

	public void setPrevious(int previous) {
		this.previous = previous;
	}

	public int getNext() {
		return this.next;
	}

	public void setNext(int next) {
		this.next = next;
	}

	public int getFirst() {
		return this.first;
	}

	public void setFirst(int first) {
		this.first = first;
	}

	public int getLast() {
		return this.last;
	}

	public void setLast(int last) {
		this.last = last;
	}

	public int getPageCount() {
		return this.pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getRowCount() {
		return this.rowCount;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}
}
