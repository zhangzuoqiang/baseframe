package cn.hs.commons.utils.taglib;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class OmitTag extends SimpleTagSupport {
	private String stringValue;//待截取的字符串
	private int showLength;//截取的长度
	private String omitStyle;//替换的字符串

	@Override
	public void doTag() throws JspException, IOException {
		try {
			String output = this.stringOmit(stringValue, showLength, omitStyle);
			Writer out = super.getJspContext().getOut();
			out.write(output);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 截取字符串的方法，当字符串长度超出给定的长度时，用omitStyle替换超出的部分
	 * @param stringValue
	 * @param showLength
	 * @param omitStyle
	 * @return
	 * @author RongLT
	 * @date 2012-5-15
	 */
	public String stringOmit(String stringValue, int showLength,
			String omitStyle) throws Exception {
		if (stringValue != null && !"".equals(stringValue)) {
			StringBuffer resultBuffer = new StringBuffer();
			int checkValue = 0;
			char charValue = ' ';
			boolean stopSing = false;
			int thisLength = 1;
			//modify by RongLT at 2012-02-29 增加一个数值doubleChar,用来记录成对的数字、字母、标点符号---start
			int doubleChar = 0;
			//--end
			for (int index = 0; index < stringValue.length(); index++) {
				checkValue = (int) stringValue.charAt(index);
				charValue = stringValue.charAt(index);
				resultBuffer.append(charValue);
				thisLength++;
				//modify by RongLT at 2012-02-29 将字符过滤的范围扩大到字母、数字和部分标点--start
				if ((checkValue >= 33 && checkValue <= 126)
				//						|| (checkValue >= 64 && checkValue <= 91)
				//						|| (checkValue >= 96 && checkValue <= 123)
				//--end
				) {
					if (stopSing) {
						thisLength--;
						stopSing = false;
						// modify by RongLT at 2012-02-29 当出现一次成对的数字、字母、标点符号时,doubleChar自增1---start
						doubleChar++;
						//--end
					} else {
						stopSing = true;
					}
				}
				//modify by RongLT at 2012-02-29 根据字符串的截取情况 决定是否增加替代字符串 omitStyle ---start
				if (thisLength > showLength) {
					//情况1_1:字符串后面不在包含字符,即字符串到这就结束了
					if (stringValue.length() < thisLength + doubleChar) {
						break;
					}
					//情况2:字符串已经截取的部分中包含奇数个标点、数字、字母等占半个字符位的字符,
					else if (stopSing) {
						//情况2_1:字符串未截取的下一个字符是标点、数字、字母等占半个字符位的字符
						if (stringValue.charAt(thisLength + doubleChar - 1) >= 33
								&& stringValue.charAt(thisLength + doubleChar
										- 1) <= 126) {
							resultBuffer.append(stringValue.charAt(thisLength
									+ doubleChar - 1));
							//如果字符串后面还包含字符,后面的字符用  omitStyle  代替
							if (stringValue.length() > thisLength + doubleChar) {
								resultBuffer.append(omitStyle);
							}
						}
						//情况2_2:字符串未截取的下一个字符不是标点、数字、字母等占半个字符位的字符
						else {
							resultBuffer.append(omitStyle);
						}
						break;
					}
					//情况1_2:字符串已经截取的部分包含偶数个或者不包含标点、数字、字母等占半个字符位的字符,且后面还有字符,后面的字符用omitStyle来替换
					else {
						resultBuffer.append(omitStyle);
						break;
					}
					//---end
				}
			}
			return resultBuffer.toString();
		} else {
			return stringValue;
		}
	}

	public String getStringValue() {
		return stringValue;
	}

	public void setStringValue(String stringValue) {
		this.stringValue = stringValue;
	}

	public int getShowLength() {
		return showLength;
	}

	public void setShowLength(int showLength) {
		this.showLength = showLength;
	}

	public String getOmitStyle() {
		return omitStyle;
	}

	public void setOmitStyle(String omitStyle) {
		this.omitStyle = omitStyle;
	}

}
