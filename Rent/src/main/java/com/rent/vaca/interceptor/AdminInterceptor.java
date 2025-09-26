//로그인 여부 검사
package com.rent.vaca.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.rent.vaca.user.UserVO;

//역할 : http요청(url요청)을 가로채는 클래스
//AOP(관점지향 프로그래밍) : 요청이 올 '때'
public class AdminInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//preHandle메소드에서 세션검사
		UserVO user = (UserVO)request.getSession().getAttribute("user"); //Session 기본제공 안 해줘서 session.getAttribute 대신 request.getSession().getAttribute() 사용
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
