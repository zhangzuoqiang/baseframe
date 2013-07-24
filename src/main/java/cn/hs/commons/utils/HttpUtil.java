package cn.hs.commons.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class HttpUtil {

    private static Log log = LogFactory.getLog(HttpUtil.class);

    private static final String URL_PARAM_CONNECT_FLAG = "&";

    private static String acturl = "";

    private static String kgecompressurl = "";

    private static int NOTICE_SOCKET_CONNECT_TIMEOUT = 360;

    private static int NOTICE_SOCKET_READ_TIMEOUT;

    /**
     * get Url Content
     * 
     * @param urlStr
     * @return
     */
    public static String getHttpUrlContent(String urlStr, String encoding) {
        return getHttpUrlContent(urlStr, encoding, false, null);
    }

    public static String getHttpUrlContentWithFlag(String urlStr, String encoding, String logFlag) {
        return getHttpUrlContent(urlStr, encoding, false, null, null, logFlag);
    }

    public static String getHttpUrlContent(String urlStr, String encoding, boolean isPost, String postBody, Map requestProperty) {
        return getHttpUrlContent(urlStr, encoding, isPost, postBody, requestProperty, null);
    }

    public static String getHttpUrlContent(String urlStr, String encoding, boolean isPost, String postBody, Map requestProperty, String logFlag) {
        String urlPattern = "";
        // 取urlpattern
        int end = urlStr.indexOf("?");
        if (end > 0) {
            urlPattern = urlStr.substring(0, end);
        } else {
            urlPattern = urlStr;
        }
        // 给kge压缩服务的请求设置时间长一点，压缩服务超过3秒是正常的，设置成6秒
        boolean isKgeCompress = false;
        if (!kgecompressurl.equals("") && urlStr.indexOf(kgecompressurl) >= 0) {
            isKgeCompress = true;
        }

        URL url = null;
        HttpURLConnection conn = null;
        long startTime = System.currentTimeMillis();
        long t1 = 0l;
        long t2 = 0l;
        log.debug("start " + startTime + " " + urlStr);
        try {
            url = new URL(urlStr);
            conn = (HttpURLConnection) url.openConnection();
            // 设置request header
            if (requestProperty != null) {
                for (Iterator it = requestProperty.keySet().iterator(); it.hasNext();) {
                    String key = (String) it.next();
                    conn.setRequestProperty(key, (String) requestProperty.get(key));
                }
            }
            // conn.setInstanceFollowRedirects(false);
            // post方法
            if (isPost) {
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
            }
            // 设置连接超时和读取超时两个时间，防止被后台憋死
            conn.setConnectTimeout(NOTICE_SOCKET_CONNECT_TIMEOUT);
            conn.setReadTimeout(NOTICE_SOCKET_READ_TIMEOUT);
            // 建立连接
            conn.connect();
            if (isPost) {
                OutputStream out = conn.getOutputStream();
                out.write(postBody.getBytes(encoding));
                out.flush();
                // BufferedWriter writer = new BufferedWriter(new
                // OutputStreamWriter(out,encoding));
                // writer.write(postBody);
                // writer.close();
            }
            // System.out.println(conn.getResponseCode());
            // System.out.println(conn.getURL());
            InputStream is = null;
            try {
                is = conn.getInputStream();
            } catch (IOException ex) {
                log.error("conn getInputStream error : ", ex);
                is = conn.getErrorStream();
            }
            t1 = System.currentTimeMillis() - startTime;
            BufferedReader breader = new BufferedReader(new InputStreamReader(is, encoding));
            char[] c_buf = new char[8192];
            StringBuffer buf = new StringBuffer("");
            int len = breader.read(c_buf, 0, 8192);
            while (len > 0) {
                buf.append(c_buf, 0, len);
                c_buf = new char[8192];
                len = breader.read(c_buf, 0, 8192);
            }
            breader.close();
            return buf.toString();
        } catch (Exception e) {
            log.error("error in getHttpUrlContent " + urlStr, e);
            // ServiceLevelService.logError("bkservice", "http error", "error in
            // getHttpUrlContent");
        } finally {
            url = null;
            // if(connState!=null){//减去并发连接数
            // synchronized (connState) {
            // if(connState.maxConns<=0){
            // log.error("error maxConns="+connState.maxConns+" for
            // url="+urlPattern);
            // }else {
            // connState.maxConns--;
            // }
            // }
            // }
            // totalCount = (Integer)map.get("total");
            // totalCount--;
            // if(totalCount!=null){
            // map.put("total", totalCount);
            // }
            t2 = System.currentTimeMillis() - startTime;
            if (conn != null) {
                conn.disconnect();
            }
            long t = System.currentTimeMillis() - startTime;
            if (logFlag != null) {
                log.debug(logFlag + " " + t2 + " " + urlStr);
            } else {
                log.debug(t2 + " " + urlStr);
            }
            if (t > 300)
                log.warn(urlStr + " cost " + t);
        }
        return "";
    }

    public static void sendHttpRequestForLog(String urlStr, String encoding, boolean isPost, String postBody, Map requestProperty) {
        URL url = null;
        HttpURLConnection conn = null;
        try {
            url = new URL(urlStr);
            conn = (HttpURLConnection) url.openConnection();
            // 设置request header
            if (requestProperty != null) {
                for (Iterator it = requestProperty.keySet().iterator(); it.hasNext();) {
                    String key = (String) it.next();
                    conn.setRequestProperty(key, (String) requestProperty.get(key));
                }
            }
            // conn.setInstanceFollowRedirects(false);
            // post方法
            if (isPost) {
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
            }
            // 设置连接超时和读取超时两个时间，防止被后台憋死
            conn.setConnectTimeout(NOTICE_SOCKET_CONNECT_TIMEOUT);
            conn.setReadTimeout(NOTICE_SOCKET_READ_TIMEOUT);
            // 建立连接
            conn.connect();
            if (isPost) {
                OutputStream out = conn.getOutputStream();
                out.write(postBody.getBytes(encoding));
                out.flush();
            }
            return;
        } catch (Exception e) {
        } finally {
            url = null;
            if (conn != null) {
                conn.disconnect();
            }

        }
        return;
    }

    public static String getHttpUrlContent(String urlStr, String encoding, boolean isPost, String postBody) {
        return getHttpUrlContent(urlStr, encoding, isPost, postBody, null);
    }

    /**
     * 发送的是字节码，返回的也是字节码,URL不做处理
     * 
     * @param urlStr
     * @param isPost
     * @param postBody
     * @return
     */
    public static byte[] getHttpUrlContentNoQue(String urlStr, boolean isPost, byte[] postBody, Map requestProperty) {
        String urlPattern = urlStr;

        // //判断同一个pattern并发连接数
        // ConnectionState connState = null;
        // if(urlPattern.length()>0){
        // //限制同一类url的并发个数
        // connState = (ConnectionState)map.get(urlPattern);
        // if(connState==null){
        // connState = new ConnectionState();
        // map.put(urlPattern, connState);
        // log.debug("urlPattern="+urlPattern);
        // }else {
        // synchronized (connState) {
        // if (connState.maxConns>=Config.BK_SERVICE_MAX_CONNECTIONS){
        // log.error("the same url has max connection
        // "+Config.BK_SERVICE_MAX_CONNECTIONS+": "+urlStr);
        // return new byte[0];
        // }
        // connState.maxConns++;
        // connState.total++;
        // if(connState.total>=100){
        // connState.total=0;
        // log.warn(urlPattern+" has 100");
        // }
        // }
        // }
        // }
        boolean isActivity = false;
        if (!acturl.equals("") && urlStr.indexOf(acturl) >= 0) {
            isActivity = true;
        }
        URL url = null;
        HttpURLConnection conn = null;
        long startTime = System.currentTimeMillis();
        log.debug("get " + urlStr + "");
        try {
            url = new URL(urlStr);
            conn = (HttpURLConnection) url.openConnection();
            // 设置request header
            if (requestProperty != null) {
                for (Iterator it = requestProperty.keySet().iterator(); it.hasNext();) {
                    String key = (String) it.next();
                    conn.setRequestProperty(key, (String) requestProperty.get(key));
                }
            }
            // conn.setInstanceFollowRedirects(false);
            // post方法
            if (isPost) {
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
            }
            // 设置连接超时和读取超时两个时间，防止被后台憋死
            conn.setConnectTimeout(NOTICE_SOCKET_CONNECT_TIMEOUT);
            conn.setReadTimeout(NOTICE_SOCKET_READ_TIMEOUT);
            // 建立连接
            conn.connect();
            if (isPost) {
                OutputStream out = conn.getOutputStream();
                out.write(postBody);
            }
            InputStream is = conn.getInputStream();

            byte[] c_buf = new byte[8192];
            int len = is.read(c_buf, 0, 8192);
            byte[] result = new byte[0];
            int total = 0;
            while (len > 0) {
                total += len;
                byte[] old_result = result;
                result = new byte[total];
                if (old_result.length > 0) {
                    System.arraycopy(old_result, 0, result, 0, total - len);
                }
                System.arraycopy(c_buf, 0, result, total - len, len);
                len = is.read(c_buf, 0, 8192);
            }
            is.close();
            return result;
        } catch (Exception e) {
            log.error("error in getHttpUrlContent " + urlStr, e);
        } finally {
            url = null;
            // if(connState!=null){//减去并发连接数
            // synchronized (connState) {
            // if(connState.maxConns<=0){
            // log.error("error maxConns="+connState.maxConns+" for
            // url="+urlPattern);
            // }else {
            // connState.maxConns--;
            // }
            // }
            // }
            // if(conn!=null){
            // conn.disconnect();
            // }
            long t = System.currentTimeMillis() - startTime;
            if (t > 300)
                log.warn(urlStr + " cost " + t);
        }
        return new byte[0];
    }

    /**
     * 发送的是字节码，返回的也是字节码
     * 
     * @param urlStr
     * @param isPost
     * @param postBody
     * @return
     */
    public static byte[] getHttpUrlContent(String urlStr, boolean isPost, byte[] postBody, Map requestProperty) {
        String urlPattern = "";
        // //取urlpattern
        int end = urlStr.indexOf("?");
        if (end > 0) {
            urlPattern = urlStr.substring(0, end);
        } else {
            urlPattern = urlStr;
        }

        // //判断同一个pattern并发连接数
        // ConnectionState connState = null;
        // if(urlPattern.length()>0){
        // //限制同一类url的并发个数
        // connState = (ConnectionState)map.get(urlPattern);
        // if(connState==null){
        // connState = new ConnectionState();
        // map.put(urlPattern, connState);
        // log.debug("urlPattern="+urlPattern);
        // }else {
        // synchronized (connState) {
        // if (connState.maxConns>=Config.BK_SERVICE_MAX_CONNECTIONS){
        // log.error("the same url has max connection
        // "+Config.BK_SERVICE_MAX_CONNECTIONS+": "+urlStr);
        // return new byte[0];
        // }
        // connState.maxConns++;
        // connState.total++;
        // if(connState.total>=100){
        // connState.total=0;
        // log.warn(urlPattern+" has 100");
        // }
        // }
        // }
        // }
        boolean isActivity = false;
        if (!acturl.equals("") && urlStr.indexOf(acturl) >= 0) {
            isActivity = true;
        }
        URL url = null;
        HttpURLConnection conn = null;
        long startTime = System.currentTimeMillis();
        log.debug("get " + urlStr + "");
        try {
            url = new URL(urlStr);
            conn = (HttpURLConnection) url.openConnection();
            // 设置request header
            if (requestProperty != null) {
                for (Iterator it = requestProperty.keySet().iterator(); it.hasNext();) {
                    String key = (String) it.next();
                    conn.setRequestProperty(key, (String) requestProperty.get(key));
                }
            }
            // conn.setInstanceFollowRedirects(false);
            // post方法
            if (isPost) {
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
            }
            // 设置连接超时和读取超时两个时间，防止被后台憋死
            conn.setConnectTimeout(NOTICE_SOCKET_CONNECT_TIMEOUT);
            conn.setReadTimeout(NOTICE_SOCKET_READ_TIMEOUT);
            // 建立连接
            conn.connect();
            if (isPost) {
                OutputStream out = conn.getOutputStream();
                out.write(postBody);
            }
            InputStream is = conn.getInputStream();

            byte[] c_buf = new byte[8192];
            int len = is.read(c_buf, 0, 8192);
            byte[] result = new byte[0];
            int total = 0;
            while (len > 0) {
                total += len;
                byte[] old_result = result;
                result = new byte[total];
                if (old_result.length > 0) {
                    System.arraycopy(old_result, 0, result, 0, total - len);
                }
                System.arraycopy(c_buf, 0, result, total - len, len);
                len = is.read(c_buf, 0, 8192);
            }
            is.close();
            return result;
        } catch (Exception e) {
            log.error("error in getHttpUrlContent " + urlStr, e);
        } finally {
            url = null;
            // if(connState!=null){//减去并发连接数
            // synchronized (connState) {
            // if(connState.maxConns<=0){
            // log.error("error maxConns="+connState.maxConns+" for
            // url="+urlPattern);
            // }else {
            // connState.maxConns--;
            // }
            // }
            // }
            // if(conn!=null){
            // conn.disconnect();
            // }
            long t = System.currentTimeMillis() - startTime;
            if (t > 300)
                log.warn(urlStr + " cost " + t);
        }
        return new byte[0];
    }

    /**
     * 获取url返回的字节码
     * 
     * @param urlStr
     * @return
     */
    public static byte[] getHttpUrlContent(String urlStr) {
        return getHttpUrlContent(urlStr, false, null, null);
    }

    /**
     * 通过文件对应的页面的 内容
     * 
     * @param path
     *            文件地址
     * @param charSet
     *            页面的字符集
     * @return
     */
    public static String getFileContent(String path, String charSet) {
        FileInputStream fileInputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader br = null;
        try {
            StringBuffer sb = new StringBuffer("");
            fileInputStream = new FileInputStream(path);
            inputStreamReader = new InputStreamReader(fileInputStream, charSet);
            br = new BufferedReader(inputStreamReader);
            String temp = br.readLine();
            while (temp != null) {
                if (!(temp.equals("") || temp.equals("\r\n") || temp.equals("\n"))) {
                    sb.append(temp + "\n");
                }
                temp = br.readLine();
            }
            return sb.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    /**
     * 读http请求中的参数，如果为null则转为空字符串
     */
    public static String readParameterSafe(HttpServletRequest request, String param) {
        String tmp = request.getParameter(param);
        tmp = tmp == null ? "" : tmp.trim();
        // try {
        // tmp = new String(tmp.getBytes("ISO-8859-1"), "UTF-8");
        // } catch (Exception e) {
        // }
        return tmp;
    }

    /**
     * 把一个URL保存成静态的HTML，要检查是否读到文件尾
     * 
     * @param urlStr
     *            要保存的URL
     * @param fileName
     *            要保存到的文件名
     * @return
     */
    public static boolean saveToFileSafe(String urlStr, String fileName) {
        URL url = null;
        try {
            url = new URL(urlStr);
        } catch (Exception e) {
            return false;
        }

        HttpURLConnection httpUrl = null;
        String ctt = "";
        try {
            httpUrl = (HttpURLConnection) url.openConnection();
            httpUrl.connect();
            InputStream is = httpUrl.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line = null;
            while ((line = br.readLine()) != null) {
                ctt += line + "\r\n";
            }
            br.close();
            isr.close();
            is.close();
            if (ctt.indexOf("<!--htm finished-->") > 0) {
                FileWriter fw = new FileWriter(fileName);
                fw.write(ctt);
                fw.flush();
                fw.close();
                return true;
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        } finally {
            if (httpUrl != null) {
                httpUrl.disconnect();
                httpUrl = null;
            }
        }
        return false;
    }

    public static boolean saveToFileSafeSmall(String urlStr, String fileName) {
        URL url = null;
        try {
            url = new URL(urlStr);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        HttpURLConnection httpUrl = null;
        String ctt = "";
        try {
            httpUrl = (HttpURLConnection) url.openConnection();
            httpUrl.connect();
            InputStream is = httpUrl.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line = null;
            boolean inscript = false;
            while ((line = br.readLine()) != null) {
                line = line.replaceAll("^\\s+<", "<");
                line = line.replaceAll(">\\s+<", "><");
                line = line.replaceAll(">\\s+$", ">");
                line = line.replaceAll("^\\s+$", "");
                if (!line.equals("")) {
                    if (line.indexOf("<script") >= 0 && line.indexOf("</script") < 0) {
                        inscript = true;
                        line = "\r\n" + line;
                    } else if (line.indexOf("</script") >= 0) {
                        inscript = false;
                        line += "\r\n";
                    }
                    if (inscript) {
                        ctt += line + "\r\n";
                    } else {
                        ctt += line;
                    }
                }
            }
            br.close();
            isr.close();
            is.close();
            if (ctt.indexOf("<!--htm finished-->") > 0) {
                FileWriter fw = new FileWriter(fileName);
                fw.write(ctt);
                fw.flush();
                fw.close();
                return true;
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        } finally {
            if (httpUrl != null) {
                httpUrl.disconnect();
                httpUrl = null;
            }
        }
        return false;
    }

    /**
     * 把一个URL保存为二进制的文件
     */
    public static boolean saveToBinFile(String urlStr, String fileName) {
        URL url = null;
        try {
            url = new URL(urlStr);
        } catch (Exception e) {
            return false;
        }

        HttpURLConnection httpUrl = null;
        String ctt = "";
        try {
            int len = 0;
            byte[] b = new byte[4096];
            File f = new File(fileName);
            if (!f.exists()) {
                f.createNewFile();
            }
            httpUrl = (HttpURLConnection) url.openConnection();
            httpUrl.connect();
            InputStream is = httpUrl.getInputStream();
            FileOutputStream fos = new FileOutputStream(f);
            while ((len = is.read(b)) > 0) {
                fos.write(b, 0, len);
            }
            fos.flush();
            fos.close();
            is.close();
            return true;
        } catch (IOException e1) {
            e1.printStackTrace();
        } finally {
            if (httpUrl != null) {
                httpUrl.disconnect();
                httpUrl = null;
            }
        }
        return false;
    }

    private static class TrustAnyTrustManager implements X509TrustManager {

        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[] {};
        }
    }

    private static class TrustAnyHostnameVerifier implements HostnameVerifier {
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }

    public static String getUrl(Map map, String charset) {
        if (null == map || map.keySet().size() == 0) {
            return ("");
        }
        StringBuffer url = new StringBuffer();
        Set keys = map.keySet();
        for (Iterator i = keys.iterator(); i.hasNext();) {
            String key = String.valueOf(i.next());
            if (map.containsKey(key)) {
                Object val = map.get(key);
                String str = val != null ? val.toString() : "";
                try {
                    str = URLEncoder.encode(str, charset);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                url.append(key).append("=").append(str).append(URL_PARAM_CONNECT_FLAG);
            }
        }
        String strURL = "";
        strURL = url.toString();
        if (URL_PARAM_CONNECT_FLAG.equals("" + strURL.charAt(strURL.length() - 1))) {
            strURL = strURL.substring(0, strURL.length() - 1);
        }
        return (strURL);
    }

    public static String getUrl(Map map) {
        if (null == map || map.keySet().size() == 0) {
            return ("");
        }
        StringBuffer url = new StringBuffer();
        Set keys = map.keySet();
        for (Iterator i = keys.iterator(); i.hasNext();) {
            String key = String.valueOf(i.next());
            if (map.containsKey(key)) {
                Object val = map.get(key);
                String str = val != null ? val.toString() : "";
                try {
                    str = URLEncoder.encode(str, "GBK");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                url.append(key).append("=").append(str).append(URL_PARAM_CONNECT_FLAG);
            }
        }
        String strURL = "";
        strURL = url.toString();
        if (URL_PARAM_CONNECT_FLAG.equals("" + strURL.charAt(strURL.length() - 1))) {
            strURL = strURL.substring(0, strURL.length() - 1);
        }
        return (strURL);
    }

    /**
     * 获得https的url返回内容
     * 
     * @param urlStr
     * @param encode
     * @return
     */
    public static String getHttpsContent(String urlStr, String encode, boolean isPost, String postBody) {
        String html = "";
        try {

            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, new TrustManager[] { new TrustAnyTrustManager() }, new java.security.SecureRandom());
            URL url = new URL(urlStr);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setSSLSocketFactory(sc.getSocketFactory());
            conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
            if (isPost) {
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
            } else {
                conn.setDoOutput(false);
            }
            conn.connect();

            // URL url = new URL(urlStr);
            // HttpsURLConnection conn =
            // (HttpsURLConnection)url.openConnection();
            // conn.setDoOutput(false);
            // conn.connect();
            if (isPost) {
                OutputStream out = conn.getOutputStream();
                out.write(postBody.getBytes(encode));
                out.flush();
                // BufferedWriter writer = new BufferedWriter(new
                // OutputStreamWriter(out,encoding));
                // writer.write(postBody);
                // writer.close();
            }
            InputStream is = null;
            try {
                is = conn.getInputStream();
            } catch (IOException ex) {
                is = conn.getErrorStream();
                ex.printStackTrace();
            }
            BufferedReader breader = new BufferedReader(new InputStreamReader(is, encode));
            char[] c_buf = new char[8192];
            StringBuffer buf = new StringBuffer("");
            int len = breader.read(c_buf, 0, 8192);
            while (len > 0) {
                buf.append(c_buf, 0, len);
                c_buf = new char[8192];
                len = breader.read(c_buf, 0, 8192);
            }
            html = buf.toString();
            breader.close();
            is.close();
            conn.disconnect();
            conn = null;
            url = null;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return html;
    }

    /**
     * 获得https的url返回内容
     * 
     * @param urlStr
     * @param encode
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String getHttpsContent(String urlStr, String encode, boolean isPost, Map<String, String> postValueMap, String fileKey, byte[] fileData) throws UnsupportedEncodingException {
        String html = "";
        String BOUNDARY = "---------------------------7d4a6d158c9"; // 分隔符
        StringBuffer sb = new StringBuffer();
        // 要发送的文本内容key_value对
        for (Iterator iterator = postValueMap.entrySet().iterator(); iterator.hasNext();) {
            Entry<String, String> entry = (Entry<String, String>) iterator.next();
            String key = entry.getKey();
            String value = entry.getValue();
            sb = sb.append("--");
            sb = sb.append(BOUNDARY);
            sb = sb.append("\r\n");
            sb = sb.append("Content-Disposition: form-data; name=\"" + key + "\"\r\n\r\n");
            sb = sb.append(URLEncoder.encode(value, "utf8"));
            sb = sb.append("\r\n");
        }
        // 要发送的二进制文件
        sb = sb.append("--");
        sb = sb.append(BOUNDARY);
        sb = sb.append("\r\n");
        sb = sb.append("Content-Disposition: form-data; name=\"" + fileKey + "\"; filename=\"" + fileKey + ".jpg\"\r\n");
        sb = sb.append("Content-Type: application/octet-stream\r\n\r\n");
        byte[] data = sb.toString().getBytes(encode);
        byte[] end_data = ("\r\n--" + BOUNDARY + "--\r\n").getBytes();
        // 设置HTTP头:
        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, new TrustManager[] { new TrustAnyTrustManager() }, new java.security.SecureRandom());
            URL url = new URL(urlStr);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setSSLSocketFactory(sc.getSocketFactory());
            conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
            if (isPost) {
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
            } else {
                conn.setDoOutput(false);
            }
            conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
            conn.setRequestProperty("Content-Length", String.valueOf(data.length + fileData.length + end_data.length));
            conn.connect();

            if (isPost) {
                OutputStream out = conn.getOutputStream();
                out.write(data);
                out.write(fileData);
                out.write(end_data);
                out.flush();
            }
            InputStream is = conn.getInputStream();
            BufferedReader breader = new BufferedReader(new InputStreamReader(is, encode));
            char[] c_buf = new char[8192];
            StringBuffer buf = new StringBuffer("");
            int len = breader.read(c_buf, 0, 8192);
            while (len > 0) {
                buf.append(c_buf, 0, len);
                c_buf = new char[8192];
                len = breader.read(c_buf, 0, 8192);
            }
            html = buf.toString();
            breader.close();
            is.close();
            conn.disconnect();
            conn = null;
            url = null;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return html;
    }

    // public String
    // string agent = request.getheader("user-agent");
    // stringtokenizer st = new stringtokenizer(agent,";");
    // st.nexttoken();
    // //得到用户的浏览器名
    // string userbrowser = st.nexttoken();

}
