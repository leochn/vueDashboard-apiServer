package com.example.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.example.model.SysUser;
import com.example.service.SysUserService;
import com.example.utils.JwtUtil;

@Component
public class ApiInterceptor implements HandlerInterceptor {
	
	@Autowired
	private SysUserService sysUserService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//System.out.println("1.ApiInterceptor......preHandle......");
		String authorization = request.getHeader(Constant.AUTHORIZATION);
		System.out.println("request.getMethod()=" + request.getMethod());
		System.out.println("authorization====" + authorization);
		// 解析jwt,看前端登陆信息是否在有效时间内(时间是否过期),
		// 无效时间,则要求用户重新登陆,如何让前端重新登陆? --> 抛一个异常，然后在@ExceptionHandler里处理???????
		// 有效时间,则查询是否有该用户,没有该用户抛出 FORBIDDEN 
		
		
		//非简单请求（不是 get、post 或存在多余的头部）时，浏览器会首先进行一个 OPTIONS 请求，这个请求来询问跨域是否被允许。Access-Control-Request-Headers 就属于这个请求。
		// 是否可以这样写???
		if (request.getMethod().equals("OPTIONS")) {
			return true;
		}
		
		boolean flag = false;
        if (StringUtils.isNotBlank(authorization)) {
            // 验证时间是否过期,及签名是否正确.
        	flag = JwtUtil.isJwtValid(authorization);
        	if (flag) {
        		// 验证用户是否存在
        		SysUser sysUser = JwtUtil.getSysUser(authorization);
        		if (sysUser != null) {
        			SysUser user = sysUserService.queryById(sysUser.getId());
        			if (user != null) {
        				flag = sysUser.getLoginName().equals(user.getLoginName());
        			}
        		}
			}
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
		//System.out.println("2.ApiInterceptor......postHandle......");
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		//System.out.println("3.ApiInterceptor......afterCompletion......");
	}

}
