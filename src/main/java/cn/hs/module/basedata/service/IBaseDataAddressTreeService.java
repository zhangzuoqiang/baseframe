package cn.hs.module.basedata.service;

import java.util.List;

import cn.hs.module.basedata.domain.BaseDataAddressTreeCondition;
import cn.hs.module.basedata.domain.jsonbean.AddressJsonTreeBean;

public interface IBaseDataAddressTreeService {

	/**
	 * 项目参加的部门树
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author XiangBin
	 * @createDate 2012-8-2
	 */
	public List<AddressJsonTreeBean> listBaseDataAddressTree(
			BaseDataAddressTreeCondition condition) throws Exception;
}
