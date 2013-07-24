package cn.hs.commons;

public class SqlPattern {

	static public void addDateSql(String beginDate, String endDate,
			StringBuffer whereCondition, String dataField) {
		if (beginDate == null && endDate == null) {
		} else if (beginDate.equalsIgnoreCase("")
				&& endDate.equalsIgnoreCase("")) {
		} else {
			if (beginDate != null && !beginDate.equals("")) {
				beginDate = "to_date('" + beginDate
						+ " 00:00','yyyy-mm-dd  HH24:MI') ";
			} else {
				beginDate = "to_date('1900-01-01 00:00','yyyy-mm-dd  HH24:MI') ";
			}
			if (endDate != null && !endDate.equals("")) {
				endDate = "to_date('" + endDate
						+ " 23:59','yyyy-mm-dd  HH24:MI') ";
			} else {
				endDate = "to_date('9999-12-31 23:59','yyyy-mm-dd  HH24:MI') ";
			}
			whereCondition.append(" and " + dataField + " between " + beginDate
					+ " and " + endDate + " ");
		}
	}

	/**
	 * 添加string类型条件 模糊匹配 如果用户输入了两个字符串，并且两个字符串中间是用英文空格间隔的，按照两个关健字匹配
	 * 
	 * @param value
	 * @param whereCondition
	 * @param dataField
	 */
	public static void addLikeStringSql(String value,
			StringBuffer whereCondition, String dataField) {
		value = replaceSqlChar(value);
		if (value != null && !"".equals(value.trim())) {
			String valuearr[] = value.split(" ");
			if (valuearr.length < 2)
				whereCondition.append(" and " + dataField + " like '%"
						+ valuearr[0] + "%' ");
			else {
				whereCondition.append(" and (");
				for (int i = 0; i < valuearr.length; i++) {
					if (!"".equals(valuearr[i]))
						if (i != valuearr.length - 1)
							whereCondition.append(dataField + " like '%"
									+ valuearr[i] + "%' or ");
						else
							whereCondition.append(dataField + " like '%"
									+ valuearr[i] + "%') ");
				}
			}
		}
	}

	/**
	 * 添加string类型条件 模糊匹配 忽略如果用户输入了两个字符串，并且两个字符串中间是用英文空格间隔的，按照两个关健字匹配
	 * 
	 * @param value
	 * @param whereCondition
	 * @param dataField
	 */
	public static void addLikeStringSqlWithSpace(String value,
			StringBuffer whereCondition, String dataField) {
		value = replaceSqlChar(value);
		if (value != null && !"".equals(value.trim())) {
			whereCondition.append(" and " + dataField + " like '%" + value
					+ "%' ");
		}
	}

	/**
	 * 添加string类型条件 模糊匹配 忽略如果用户输入了两个字符串，并且两个字符串中间是用英文空格间隔的，按照两个关健字匹配
	 * 
	 * @param value
	 * @param whereCondition
	 * @param dataField
	 */
	public static void addLikeFuzzyQueryStringSqlWithSpace(String value,
			StringBuffer whereCondition, String dataField) {
		value = replaceSqlChar(value);
		if (value != null && !"".equals(value.trim())) {
			whereCondition.append(" and " + dataField + " like '%"
					+ buildFuzzyQueryCondition(value) + "%' ");
		}
	}

	/**
	 * 把多字符型String每个字符中间加入一个%实现全字符模糊查询
	 * 
	 * @return String
	 */
	public static String buildFuzzyQueryCondition(String preStr) {
		String result = "";
		if (preStr != null && !"".equals(preStr)) {
			if (preStr.length() > 1) {
				for (int i = 0; i < preStr.length(); i++) {
					if (preStr.charAt(i) == ' ') {
						continue;
					}
					result += preStr.charAt(i) + "%";
				}
				if (result.indexOf("%") != -1) {
					result = result.substring(0, result.length() - 1);
				}
			} else {
				result = preStr;
			}
		} else {
			return preStr;
		}
		return result;
	}

	/**
	 * 精确匹配字符串
	 * 
	 * @param value
	 * @param whereCondition
	 * @param dataField
	 */
	public static void addExactStringSearch(String value,
			StringBuffer whereCondition, String dataField) {
		value = replaceSqlChar(value);
		if (value != null && !"".equals(value.trim())) {
			whereCondition.append(" and " + dataField + "= '" + value + "'  ");
		}
	}

	/**
	 * 添加int类型条件
	 * 
	 * @param value
	 * @param whereCondition
	 * @param dataField
	 */
	public static void addintSql(String value, StringBuffer whereCondition,
			String dataField) {
		if (value != null && !"".equals(value)) {
			whereCondition.append(" and " + dataField + "=" + value);
		}
	}

	/**
	 * 添加两个数字段之间的查询 arg0与arg1之间
	 * 
	 * @param value
	 * @param whereCondition
	 * @param dataField
	 */
	public static void addarg0andarg1Sql(String arg0, String arg1,
			StringBuffer whereCondition, String dataField) {
		if (arg0 != null && arg1 != null && !"".equals(arg0)
				&& !"".equals(arg1))
			whereCondition.append(" and " + dataField + ">" + arg0 + " and "
					+ dataField + "<" + arg1);
	}

	/**
	 * 添加 in 类型条件 in 的括号中为int类型
	 * 
	 * @param value
	 * @param whereCondition
	 * @param dataField
	 *            该域在数据库中为integer类型
	 * @param intype
	 *            可以为 "in" 或 "not in" 按需传入
	 */
	public static void addStringInWithInt(String value,
			StringBuffer whereCondition, String dataField, String intype) {
		if (value != null && !"".equals(value)) {
			whereCondition.append(" and " + dataField + " " + intype + " ("
					+ value + ") ");
		}
	}

	/**
	 * 添加 in 类型条件 in 的括号中为String类型
	 * 
	 * @param value
	 * @param whereCondition
	 * @param dataField
	 *            该域在数据库中为String类型
	 * @param intype
	 *            可以为 "in" 或 "not in" 按需传入
	 */
	public static void addStringInWithString(String value,
			StringBuffer whereCondition, String dataField, String intype) {
		if (value != null && !"".equals(value)) {
			String[] tmp = value.split(",");
			String tmpvalue = "";
			for (int i = 0; i < tmp.length; i++) {
				if ("".equals(tmpvalue))
					tmpvalue = "'" + tmp[i] + "'";
				else
					tmpvalue += "," + "'" + tmp[i] + "'";
			}

			whereCondition.append(" and " + dataField + " " + intype + " ("
					+ tmpvalue + ") ");
		}
	}

	public static String replaceSqlChar(String value) {
		return value;
	}
}
