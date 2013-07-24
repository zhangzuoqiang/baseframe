/*
 * $Log: JsonTreeBean.java,v $
 * Revision 1.1  2012/05/23 09:27:49  guor
 * 初次提交
 *
 */
package cn.hs.module.basedata.domain.jsonbean;

import java.io.Serializable;
import java.util.List;

/**
 * Title: JsonDepartment<br>
 * Description: 树形结构节点集合JSON格式<br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author WangWB
 * @createDate Aug 11, 2011
 * @version $Revision: 1.1 $
 */
public class AddressJsonTreeBean implements Serializable {

	private static final long serialVersionUID = -5454563479442777102L;

	/**
	 * 节点ID
	 */
	private String id;
	/**
	 * 父节点ID
	 */
	private String parentID;
	/**
	 * 节点名称
	 */
	private String text;

	/**
	 * 编码
	 */
	private String code;
	/**
	 * 下拉复选树使用：是否选中
	 */
	private Boolean checked;

	/**
	 * 复选框：是否可编辑
	 */
	private Boolean isEdit;
	/**
	 * 下拉复选树使用：子节点集合
	 */
	private List<AddressJsonTreeBean> children;

	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParentID() {
		return parentID;
	}

	public void setParentID(String parentID) {
		this.parentID = parentID;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public List<AddressJsonTreeBean> getChildren() {
		return children;
	}

	public void setChildren(List<AddressJsonTreeBean> children) {
		this.children = children;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Boolean getIsEdit() {
		return isEdit;
	}

	public void setIsEdit(Boolean isEdit) {
		this.isEdit = isEdit;
	}

}
