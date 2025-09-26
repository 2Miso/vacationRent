//�α��� ���� �˻�
package com.rent.vaca.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.rent.vaca.user.UserVO;

//���� : http��û(url��û)�� ����ä�� Ŭ����
//AOP(�������� ���α׷���) : ��û�� �� '��'
public class AdminInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//preHandle�޼ҵ忡�� ���ǰ˻�
		UserVO user = (UserVO)request.getSession().getAttribute("user"); //Session �⺻���� �� ���༭ session.getAttribute ��� request.getSession().getAttribute() ���
		if(user == null) {
			response.sendRedirect(request.getContextPath() + "/login/main");
			return false;
		} else if (user.getGrade().equals("A")){
			return true;
		} else {
			response.sendRedirect(request.getContextPath() + "");
			return false;
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
