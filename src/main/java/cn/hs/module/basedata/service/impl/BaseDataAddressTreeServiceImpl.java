package cn.hs.module.basedata.service.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hs.module.Constants;
import cn.hs.module.basedata.dao.IBaseDataTypeDao;
import cn.hs.module.basedata.domain.BaseData;
import cn.hs.module.basedata.domain.BaseDataAddressTreeCondition;
import cn.hs.module.basedata.domain.BaseDataCondition;
import cn.hs.module.basedata.domain.BaseDataType;
import cn.hs.module.basedata.domain.jsonbean.AddressJsonTreeBean;
import cn.hs.module.basedata.service.IBaseDataAddressTreeService;
import cn.hs.module.basedata.service.IBaseDataService;

/**
 * Title: BaseDataAddressTreeServiceImpl<br>
 * Description: 行政区的树形菜单<br>
 * Company: ORCHIS<br>
 * Copyright @ 2012 ORCHIS .All rights reserved.<br>
 * @author XiangBin
 * @createDate Aug 31, 2012
 * @version $Revision:$
 */
@Service(value = "cn.hs.module.basedata.service.impl.BaseDataAddressTreeServiceImpl")
public class BaseDataAddressTreeServiceImpl implements IBaseDataAddressTreeService {

	// 基础数据类别DAO
	@Autowired
	private IBaseDataTypeDao baseDataTypeDaoImpl;

	@Autowired
	private IBaseDataService baseDataService;

	@Override
	public List<AddressJsonTreeBean> listBaseDataAddressTree(BaseDataAddressTreeCondition condition) throws Exception {

		// 获得所有的行政区
		BaseDataAddressTreeCondition bdatCondition = new BaseDataAddressTreeCondition();
		bdatCondition.setSearchTypeCode(Constants.REGIONALISM_CODE);
		List<BaseDataType> baseDataTypeList = baseDataTypeDaoImpl.listBaseDataTypeByParenCode(bdatCondition);
		LinkedList<BaseDataType> baseDataTypeLink = new LinkedList<BaseDataType>();
		for (int i = 0; i < baseDataTypeList.size(); i++) {
			baseDataTypeLink.add(i, baseDataTypeList.get(i));
			if (baseDataTypeList.get(i).getTypeCode().equals(Constants.DIRECT_CITY)) {
				baseDataTypeLink.addFirst(baseDataTypeList.get(i));
				baseDataTypeLink.remove(i + 1);
			}
		}
		String[] typeIds = new String[baseDataTypeLink.size()];
		for (int i = 0; i < baseDataTypeLink.size(); i++) {
			typeIds[i] = baseDataTypeLink.get(i).getTypeID();
		}

		// 查询所有的地址
		BaseDataCondition bdCondition = new BaseDataCondition();
		bdCondition.setSearchDataTypeIDs(typeIds);
		bdCondition.setSearchActiveState(BaseData.IS_ACTIVE_Y.toString());
		List<BaseData> baseDataList = baseDataService.getBaseDataList(bdCondition);
		List<AddressJsonTreeBean> resultList = new ArrayList<AddressJsonTreeBean>();
		for (BaseDataType baseDataType : baseDataTypeLink) {
			AddressJsonTreeBean jtBean = new AddressJsonTreeBean();
			jtBean.setChecked(false);
			jtBean.setIsEdit(true);
			jtBean.setText(baseDataType.getTypeName());
			jtBean.setId(baseDataType.getTypeID().toString());
			jtBean.setCode(baseDataType.getTypeCode());
			List<AddressJsonTreeBean> sonList = new ArrayList<AddressJsonTreeBean>();
			for (BaseData baseData : baseDataList) {
				if (baseData.getBaseDataType().getTypeID().equals(baseDataType.getTypeID())) {
					AddressJsonTreeBean sonBean = new AddressJsonTreeBean();
					sonBean.setText(baseData.getDataName());
					sonBean.setId(baseData.getDataID().toString());
					sonBean.setParentID(baseData.getBaseDataType().getTypeID().toString());
					sonBean.setCode(baseData.getDataCode());
					sonBean.setChecked(false);
					sonBean.setIsEdit(true);
					// FIXME 设置行政区的树形默认选中状态 需要自定义实现逻辑

					if (condition.getExistAddressList() != null && condition.getExistAddressList().size() > 0) {
						for (BaseData exitsBaseData : condition.getExistAddressList()) { // 修改为使用equals比较
							if (baseData.getDataCode().equals(exitsBaseData.getDataCode())) {
								sonBean.setChecked(true);
								jtBean.setChecked(true);
								// if (condition.getSearchStatus() != null &&
								// condition.getSearchStatus() != 0) { //
								// 设置项目期望地址树的修改地址
								// if
								// (Constants.TREE_PROJECT_EXPECT_PLACE.equals(condition.getSearchTreeType()))
								// {
								// if
								// (condition.getSearchStatus().equals(ProjectInfo.PUBLISH_PROJECTINFO_STATUS)
								// ||
								// condition.getSearchStatus().equals(ProjectInfo.CONFIG_PROJECTINFO_STATUS)
								// ||
								// condition.getSearchStatus().equals(ProjectInfo.DELETE_PROJECTINFO_STATUS))
								// {
								// sonBean.setIsEdit(false);
								// jtBean.setIsEdit(false);
								// }
								// } // 设置岗位已处于发布状态则只可以增加工作地点
								// if
								// (Constants.TREE_STATION_WORK_PLACE.equals(condition.getSearchTreeType()))
								// {
								// if
								// (condition.getSearchStatus().equals(StationInfo.STATUS_PUBLISHED))
								// {
								// sonBean.setIsEdit(false);
								// jtBean.setIsEdit(false);
								// }
								// }
								// }
							}
						}
					}

					sonList.add(sonBean);
				}
			}
			jtBean.setChildren(sonList);
			resultList.add(jtBean);
		}
		return resultList;
	}

	public static void main(String[] args) {
		List<String> a = new ArrayList<String>();
		LinkedList<String> b = new LinkedList<String>();
		a.add("a1");
		a.add("a2");
		a.add("a3");
		a.add("a4");
		for (int i = 0; i < a.size(); i++) {
			b.add(a.get(i));
			if (a.get(i).equals("a4")) {
				// b.add(0, a.get(i));
				b.addFirst(a.get(i));
				b.remove(i + 1);
			}
		}

		for (int i = 0; i < b.size(); i++) {
			System.out.println(b.get(i));
		}
	}

}
