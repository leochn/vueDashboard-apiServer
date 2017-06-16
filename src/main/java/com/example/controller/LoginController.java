package com.example.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.config.Constant;
import com.example.pojo.SysUser;
import com.example.service.SysUserService;
import com.example.utils.JsonResult;
import com.example.utils.JwtUtil;

@RestController
@RequestMapping(value = "rest")
public class LoginController {
	@Autowired
	private SysUserService sysUserService;
	
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public ResponseEntity<JsonResult> login(@RequestParam("username") String username,
			@RequestParam("password") String password, HttpServletRequest request, HttpServletResponse response){
//		Enumeration<String> names = request.getHeaderNames();
//		System.out.println("postman-token=" + request.getHeader("postman-token"));
//		System.out.println("===================================================================");
//		while (names.hasMoreElements()) {
//			String name = (String) names.nextElement();
//			System.out.println(name + ":" + request.getHeader(name));
//		}
//		System.out.println("===================================================================");
		System.out.println("username=" + request.getParameter("username"));
		try {
			SysUser sysUser = this.sysUserService.getLogin(username, password);
			String token = null;
			if (sysUser != null) {
				token = JwtUtil.generateToken("signingKey", sysUser.getLoginName());
				token = JwtUtil.generateToken(Constant.JWT_SECRET, JwtUtil.generalSubject(sysUser));
				Map<String, Object> result = new HashMap<String, Object>();
				result.put("token",token);
				result.put("userName",sysUser.getLoginName()); //把用户信息返回,方便显示.
				JsonResult jsonResult = JsonResult.custom(result);
				return ResponseEntity.ok(jsonResult);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
	
}
