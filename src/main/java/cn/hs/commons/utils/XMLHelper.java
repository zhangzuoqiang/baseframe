package cn.hs.commons.utils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentFactory;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.dom.DOMDocumentFactory;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class XMLHelper {
	public static Element generateDom4jElement(String elementName) {
		return DocumentFactory.getInstance().createElement(elementName);
	}

	public static void addStringValue(Element parentElement, String tagName,
			String value) {
		Element el = parentElement.addElement(tagName);
		el.setText(value);
	}

	public static void addCDATAValue(Element parentElement, String tagName,
			String value) {
		Element el = parentElement.addElement(tagName);
		el.addCDATA(value);
	}

	/**
	 * @author xum
	 * @param element
	 * @param childElementName
	 * @return
	 */
	public static Element addChildElement(Element element,
			String childElementName) {
		Element newElement = element.addElement(childElementName);
		return newElement;
	}

	public static Element getSingleChildElement(Element parentElement) {
		Iterator itea = parentElement.elementIterator();
		if (itea.hasNext()) {
			return (Element) itea.next();
		} else {
			return null;
		}
	}

	/**
	 * 根据父节点和子节点名称获取单一子节点信息
	 * 
	 * @param parentElement
	 * @param childElementName
	 * @return
	 * @author xum
	 */
	public static Element getSingleChildElement(Element parentElement,
			String childElementName) {
		Iterator itea = parentElement.elementIterator();
		while (itea.hasNext()) {
			Element node = (Element) itea.next();
			if (childElementName.equals(node.getName()))
				return node;
		}
		return null;
	}

	/**
	 * 根据父节点和子节点名称获取单一子节点的文本值
	 * 
	 * @param parentElement
	 * @param childElementName
	 * @return
	 * @author xum
	 */
	public static String getSingleChildElementValue(Element parentElement,
			String childElementName) {
		Iterator itea = parentElement.elementIterator();
		while (itea.hasNext()) {
			Element node = (Element) itea.next();
			if (childElementName.equals(node.getName()))
				return node.getText();
		}
		return null;
	}

	/**
	 * 根据父节点和子节点名称获取多子节点信息
	 * 
	 * @param parentElement
	 * @param childElementName
	 * @return
	 * @author xum
	 */
	public static List getChildElementList(Element parentElement,
			String childElementName) {
		if (parentElement == null)
			return null;
		List elements = new LinkedList();
		Iterator itea = parentElement.elementIterator();
		while (itea.hasNext()) {
			Element node = (Element) itea.next();
			if (childElementName.equals(node.getName()))
				elements.add(node);
		}
		return elements;
	}

	/**
	 * 得到节点的值,如果节点的值为null,则返回defaultvalue
	 * 
	 * @param element
	 * @param elemetnName
	 *            节点名称
	 * @param defaultvalue
	 *            默认值
	 * @return
	 */
	public static String elementText(Element element, String elemetnName,
			String defaultvalue) {
		String str = element.elementText(elemetnName);
		if (str == null) {
			str = defaultvalue;
		}
		return str;
	}

	public static Object getElementObject(Element parentElement, Object object)
			throws Exception {

		BeanInfo beanInfo = Introspector.getBeanInfo(object.getClass());
		PropertyDescriptor[] pd = beanInfo.getPropertyDescriptors();
		for (int i = 0; i < pd.length; i++) {

			Class type = pd[i].getPropertyType();
			String name = pd[i].getName();

			for (Iterator itea = parentElement.elementIterator(); itea
					.hasNext();) {
				Element childelement = (Element) itea.next();

				String elName = childelement.getName();
				String value = childelement.getText();

				value = java.net.URLDecoder.decode(value, "UTF-8");

				if (name.equals(elName)) {

					if (value != null && !"".equals(value)) {
						Method method = pd[i].getWriteMethod();

						if ("java.lang.Integer".equals(type.getName())
								|| Integer.TYPE == type) {

							Integer tempValue = new Integer(value);
							method.invoke(object, new Object[] { tempValue });

						} else if ("java.util.Date".equals(type.getName())) {

							Date date = new Date(Long.parseLong(value));
							method.invoke(object, new Object[] { date });

						} else if ("java.lang.String".equals(type.getName())) {
							method.invoke(object, new Object[] { value });
						}
					}
				}
			}
		}
		return object;
	}

	public static Element getObjectElement(Object obj, String elementName) {
		Element el = generateDom4jElement(elementName);
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
			PropertyDescriptor[] pd = beanInfo.getPropertyDescriptors();

			for (int i = 0; i < pd.length; i++) {
				Element child = generateDom4jElement(pd[i].getName());
				Method readMethod = pd[i].getReadMethod();

				Object value = readMethod.invoke(obj, null);

				if (!(value instanceof java.lang.Class)
						&& !(value instanceof java.util.Collection)) {
					if (value == null) {
						value = "";
					}
					if (value instanceof java.util.Date) {
						Date d = (Date) value;
						value = String.valueOf(d.getTime());
					}
					child.setText(java.net.URLEncoder.encode(value.toString(),
							"UTF-8"));
					el.add(child);
				}
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return el;
	}

	public static void SaveElementToFile(String encoding, String fileName,
			Element element, String rootNodeName) throws IOException {
		Document document = DocumentHelper.createDocument();
		if (rootNodeName != null) {
			document.addElement(rootNodeName).add(element);
		} else {
			document.add(element);
		}
		OutputFormat format = new OutputFormat();
		format.setEncoding(encoding);
		format.setLineSeparator("\r\n");
		format.setIndent(true);
		format.setTrimText(true);
		format.setNewlines(true);
		OutputStream os = new FileOutputStream(fileName);
		XMLWriter writer = new XMLWriter(os, format);
		writer.write(document);
		writer.close();
		os.close();
	}

	public static void SaveElementToFile(String encoding, String fileName,
			Element element) throws IOException {
		SaveElementToFile(encoding, fileName, element, null);
	}

	public static Element load_element(String fileName) throws Exception {
		SAXReader reader = new SAXReader(DOMDocumentFactory.getInstance());
		File f = new File(fileName);
		FileInputStream fin = null;
		try {
			fin = new FileInputStream(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw e;
		}
		Element root = null;
		if (f.exists()) {
			Document doc = reader.read(fin);
			root = doc.getRootElement();
		}
		return root;

	}

	/**
	 * 读取xml文件并返回document对象
	 * 
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	public static Document load_Document(String fileName) throws Exception {
		SAXReader reader = new SAXReader(DOMDocumentFactory.getInstance());
		File f = new File(fileName);
		FileInputStream fin = null;
		try {
			fin = new FileInputStream(f);
		} catch (FileNotFoundException e) {
			throw e;
		}
		Document doc = null;
		if (f.exists()) {
			doc = reader.read(fin);
		}
		return doc;

	}

	/**
	 * 以流的方式读出XML内容
	 * 
	 * @param in
	 * @return
	 * @throws Exception
	 * @author xum
	 */
	public static Document load_Document(InputStream in) throws Exception {
		SAXReader reader = new SAXReader(DOMDocumentFactory.getInstance());
		Document doc = null;
		doc = reader.read(in);
		return doc;
	}

	/**
	 * 保存document为xml文件
	 * 
	 * @param encoding
	 * @param fileName
	 * @param document
	 * @throws IOException
	 */
	public static void SaveDocumentToFile(String encoding, String fileName,
			Document document) throws IOException {
		OutputFormat format = new OutputFormat();
		format.setEncoding(encoding);
		format.setLineSeparator("\r\n");
		format.setIndent(true);
		format.setNewlines(true);
		format.setTrimText(true);
		OutputStream os = new FileOutputStream(fileName);
		XMLWriter writer = new XMLWriter(os, format);
		writer.write(document);
		writer.flush();
		writer.close();
		os.close();
	}
}
