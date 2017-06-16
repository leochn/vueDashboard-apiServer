package com.example.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class ApiInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("1.ApiInterceptor......preHandle......");
		String Authorization = request.getHeader(Constant.AUTHORIZATION);
		System.out.println("Authorization=" + Authorization);
		// 解析jwt,看前端登陆信息是否在有效时间内(时间是否过期),
		// 无效时间,则要求用户重新登陆,如何让前端重新登陆? --> 抛一个异常，然后在@ExceptionHandler里处理???????
		// 有效时间,则查询是否有该用户,没有该用户抛出 FORBIDDEN 
		// response.sendRedirect("http://www.baidu.com"); //跨域
		
		boolean flag = false;
        if (StringUtils.isNotBlank(Authorization)) {
            // 验证
        	flag = true;
        }
        if (!flag) {
            //response.setStatus(HttpStatus.FORBIDDEN.value());
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter().print(Constant.ACCESSTOKENERROR);
        }
        return flag;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("2.ApiInterceptor......postHandle......");
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("3.ApiInterceptor......afterCompletion......");
	}

}
