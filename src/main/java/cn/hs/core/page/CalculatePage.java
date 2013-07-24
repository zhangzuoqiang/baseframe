/*
 * $Log: CalculatePage.java,v $
 * Revision 1.1  2012/05/23 09:27:48  guor
 * 初次提交
 *
 * Revision 1.1  2011/06/01 05:46:55  kkaiwen
 * 2011.6.1 create
 *
 */
package cn.hs.core.page;

/**
 * Title: CalculatePage<br>
 * Description: 分页计算<br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author MengW
 * @createDate 2011-5-17
 * @version $Revision: 1.1 $
 */
public class CalculatePage {
	public static void calculate(PageInfoBean page, int count) {
		page.setCount(count);
		int rowCount = page.getRowCount();
		if (((count % rowCount) == 0) && (count != 0)) {
			page.setPageCount(count / rowCount);
		} else {
			page.setPageCount(count / rowCount + 1);
		}
		int pageCount = page.getPageCount();
		int current = page.getCurrent();

		if (current <= 0) {
			current = 1;
			page.setCurrent(1);
		}

		page.setFirst(1);
		page.setLast(pageCount);
		if (current < pageCount) {
			page.setNext(current + 1);
		} else {
			page.setNext(pageCount);
			current = pageCount;
			page.setCurrent(pageCount);
		}
		if (current > 1) {
			page.setPrevious(current - 1);
		} else {
			page.setPrevious(1);
		}
		// 修改计算起始值方法，不加1 modify by HuangS at 2012-07-25
		int start = (page.getCurrent() - 1) * page.getRowCount();
//		int start = (page.getCurrent() - 1) * page.getRowCount() + 1;
		int end = 0;
		if (page.getCurrent() == page.getNext()) {
			end = page.getCount();
		} else {
			end = (page.getNext() - 1) * page.getRowCount();
		}
		page.setStart(start);
		if (end == 0) {
			end = 1;
		}
		page.setEnd(end);
	}
}
