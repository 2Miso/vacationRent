//�α��� ���� �˻�
package com.rent.vaca.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

//���� : http��û(url��û)�� ����ä�� Ŭ����
//AOP(�������� ���α׷���) : ��û�� �� '��'
public class LoginInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//preHandle�޼ҵ忡�� ���ǰ˻�
		Object user = request.getSession().getAttribute("user"); //Session �⺻���� �� ���༭ session.getAttribute ��� request.getSession().getAttribute() ���
		if(user == null) {
			response.sendRedirect(request.getContextPath() + "/login/main");
			return false;
		} else {
			return true;
		}
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}
}
