package com.aop;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
	
/**
* 拦截器,用于控制用户,必须有session才能登录
* * @author Administrator
* *HandlerInterceptorAdapter:抽象类
* */
public class SessionInterceptor  extends HandlerInterceptorAdapter{
				
					/**
					 *  handler 这个参数,可以得到调用的方法的相关信息
					 *  重写HandlerInterceptorAdapter的prehandle方法：预处理请求，在请求到服务器时判断是否有session，决定是否同意处理请求。
					 */
public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

						//没有session,则转到登录页
				if(request.getSession().getAttribute("session_user")==null) {
					request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
					return false;
				}
				else {
					return true;
				}
			}
				
}