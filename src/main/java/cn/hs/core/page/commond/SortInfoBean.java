package cn.hs.core.page.commond;

import java.io.Serializable;

/**
 * 
 * Title: SortInfoBean<br>
 * Description: 排序实体类<br>
 * Company: ORCHIS<br>
 * Copyright @ 2012 ORCHIS .All rights reserved.<br>
 * @author HuangS
 * @createDate Jul 20, 2012
 * @version $Revision:$
 */
public class SortInfoBean implements Serializable {

	/**
	 * @author HuangS
	 * @date Jul 20, 2012
	 */
	private static final long serialVersionUID = 1L;

	private String property; // 排序字段

	private String direction; // 排序方式

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

}
