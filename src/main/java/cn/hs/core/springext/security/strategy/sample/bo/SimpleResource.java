package cn.hs.core.springext.security.strategy.sample.bo;

import cn.hs.core.springext.security.source.BaseResource;

/**
 * Title: SimpleResource<br>
 * Description: 样例资源<br>
 * Company: EORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * 
 * @author ZhangKW
 * @createDate 2011-5-26
 * @version $Revision: 1.1 $
 */
public class SimpleResource implements BaseResource {

	private String id; // 资源id

	private String name; // 资源名称

	private String url; // 资源url

	@Override
	public String getIdentity() {
		return this.name;
	}

	@Override
	public String getUrl() {
		// TODO Auto-generated method stub
		return this.url;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

}
