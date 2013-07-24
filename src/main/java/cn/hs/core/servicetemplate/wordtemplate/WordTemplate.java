/*
 * $Log: WordTemplate.java,v $
 * Revision 1.1  2012/05/23 09:27:49  guor
 * 初次提交
 *
 */
package cn.hs.core.servicetemplate.wordtemplate;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import cn.hs.core.basedao.condition.BaseWordCondition;
import cn.hs.core.log4j.ILog4jManager;
import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * Title: WordTemplate<br>
 * Description: word模板<br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author Liy
 * @createDate 2011-12-7
 * @version $Revision: 1.1 $
 */
public abstract class WordTemplate {
    // 初始化日志接口
    @Autowired
    private ILog4jManager log4jManager;

    /**
     * word模板保存路径
     */
    public final static String WORD_TEMPLATE_FILE_DIRECTORY = "/module-config/wordutils/wordftl";

    public final void process(BaseWordCondition condition) throws Exception {
        if (condition.getOut() != null) {
            OutputStream out = condition.getOut();
            OutputStreamWriter write = new OutputStreamWriter(out, "utf-8");
            exportWord(write, condition);
        } else {
            log4jManager.debugCustomLog(getClass().getName(), "process", "没有设置OutputStream");
        }
    }

    /**
     * word导出
     * 
     * @author： HuangS
     * @createTime：2013-5-30
     * @param write
     * @param condition
     * @throws Exception
     */
    private void exportWord(OutputStreamWriter write, BaseWordCondition condition) throws Exception {
        Configuration configuration = new Configuration();
        // 设置字符集
        configuration.setDefaultEncoding("utf-8");
        // 要填入模本的数据文件
        Map<String, Object> dataMap = buildData(condition);
        // 设置模板
        String ftlName = getFtl();
        // 设置模本装置方法和路径,FreeMarker支持多种模板装载方法。可以重servlet，classpath，数据库装载，
        // 这里我们的模板是放在com.havenliu.document.template包下面
        configuration.setClassForTemplateLoading(getClass(), WordTemplate.WORD_TEMPLATE_FILE_DIRECTORY);
        Template t = null;
        try {
            t = configuration.getTemplate(ftlName);
            t.process(dataMap, write);
        } catch (Exception e) {
            e.printStackTrace();
            log4jManager.saveExceptionLog(getClass().getName(), "process", "word导出异常", e);
        } finally {
            write.close();
        }
    }

    /**
     * 组装数据，注意dataMap里存放的数据Key值要与模板中的参数相对应，需要各实现类实现
     * 
     * @author： HuangS
     * @createTime：2013-5-30
     * @param condition
     * @return
     * @throws Exception
     */
    protected abstract Map<String, Object> buildData(BaseWordCondition condition) throws Exception;

    /**
     * 获得模板名称，需要各实现类实现
     * 
     * @param ftl
     * @throws Exception
     * @author Liy
     * @date 2011-12-7
     */
    protected abstract String getFtl() throws Exception;

}
