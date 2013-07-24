package cn.hs.core.springext.security.casclient.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

/**
 * 
 * @ProjectNmae：sihibase.
 * @ClassName：CasLogoutSuccessHandler
 * @Description：casLogout登出handler
 * @author：HuangS
 * @crateTime：2013-5-30
 * @editor：
 * @editTime：
 * @editDescription：
 * @version 1.0.0
 */
public class CasLogoutSuccessHandler implements LogoutSuccessHandler {

    private String url = "";

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        if ("".equals(url)) {
            url = "http://www.itkx.cn:80/sihisso/logout";
        }
        String service = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/index.jsp";
        url += "?service=" + service;
        response.sendRedirect(url);
    }

    public void setTargetUrl(String url) {
        this.url = url;
    }

}
