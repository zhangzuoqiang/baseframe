package cn.hs.core.springext.mvc.exceptionresolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import cn.hs.core.springext.mvc.view.MappingJacksonJsonView;

/**
 * Title: CustomExceptionResolver<br>
 * Description: 自定义Controller异常处理器.<br>
 * Company: EORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * 
 * @author ZhangKW
 * @createDate 2011-5-19
 * @version $Revision: 1.1 $
 */
public class CustomExceptionResolver extends SimpleMappingExceptionResolver {

	/**
	 * 捕获异常,覆写父类方法负责记录日志、区分同步与异步请求做出不同回应
	 * 
	 * @param request
	 * @param response
	 * @param handler
	 * @param ex
	 * @return
	 * @author ZhangKW
	 * @date 2011-5-19
	 */
	@Override
	protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

		// 记录日志
		logger.error("异常发生在[" + handler.getClass() + "],异常信息[" + ex.getMessage() + "]", ex);

		String viewName = determineViewName(ex, request);
		// JSP格式返回
		if (viewName != null) {
			// 如果不是异步请求
			String accept = request.getHeader("accept");
			String requested = request.getHeader("X-Requested-With");
			if (!((accept != null && accept.indexOf("application/json") > -1) || (requested != null && requested.indexOf("XMLHttpRequest") > -1))) {
				// Apply HTTP status code for error views, if specified.
				// Only apply it if we're processing a top-level request.
				Integer statusCode = determineStatusCode(request, viewName);
				if (statusCode != null) {
					// 异常信息绑定
					applyStatusCodeIfPossible(request, response, statusCode);
					// 返回错误页
					return getModelAndView(viewName, ex, request);
				}
			}
			// JSON格式返回
			else {
				MappingJacksonJsonView view = new MappingJacksonJsonView();
				// view.setContentType("application/json; charset=UTF-8");
				ModelAndView mav = new ModelAndView(view);
				// try {
				// 返回异常
				// response.setContentType("application/json;charset=UTF-8");
				// 修改错误异常绑定重新生成token
				// JSONObject object = new JSONObject();
				// IToken token = SpringBeanUtil.getBean(IToken.class);
				// object.setSuccess(false);
				// object.setMsg("系统错误，请联系管理员。");
				// object.setData(token.saveToken(request));
				// response.getWriter().print(JSONUtils.objToJson(object));
				mav.addObject("success", false);
				mav.addObject("msg", "系统错误，请联系管理员。");
				// response.getWriter().print("{\"success\":false,\"msg\":\"\"}");
				// response.getWriter().print("{\"success\":false,\"msg\":\""+ex.getMessage().replaceAll("\"",
				// "\\\"")+"\"}");
				// } catch (Exception e) {
				// e.printStackTrace();
				// }
				return mav;
			}
			return null;
		} else {
			return null;
		}
	}
}
