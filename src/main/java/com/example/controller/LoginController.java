package com.example.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.config.Constant;
import com.example.model.SysUser;
import com.example.service.SysUserService;
import com.example.utils.JsonResult;
import com.example.utils.JwtUtil;
import com.example.utils.SpringContextHolder;

@RestController
@RequestMapping(value = "rest")
public class LoginController {
	@Autowired
	private SysUserService sysUserService;
	private static Logger logger = LoggerFactory.getLogger(SpringContextHolder.class);
	
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public ResponseEntity<JsonResult> login(@RequestParam("loginName") String loginName,
			@RequestParam("password") String password, HttpServletRequest request, HttpServletResponse response){
		System.out.println("loginName=" + request.getParameter("loginName"));
		if (logger.isDebugEnabled()){
			logger.debug("LoginController.......login........");
		}
		try {
			SysUser user = new SysUser();
			user.setLoginName(loginName);
			user.setPwd(password);
			SysUser sysUser = this.sysUserService.queryOne(user);
			String token = null;
			if (sysUser != null) {
				token = JwtUtil.generateToken("signingKey", sysUser.getLoginName());
				token = JwtUtil.generateToken(Constant.JWT_SECRET, JwtUtil.generalSubject(sysUser));
				Map<String, Object> result = new HashMap<String, Object>();
				result.put("token",token);
				result.put("loginName",sysUser.getLoginName()); //把用户信息返回,方便显示.
				JsonResult jsonResult = JsonResult.custom(result);
				return ResponseEntity.ok(jsonResult);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
	
	@RequestMapping(value = "login2", method = RequestMethod.POST)
	public ResponseEntity<JsonResult> login2(@RequestBody SysUser user){
		try {
			SysUser sysUser = this.sysUserService.queryOne(user);
			String token = null;
			if (sysUser != null) {
				token = JwtUtil.generateToken("signingKey", sysUser.getLoginName());
				token = JwtUtil.generateToken(Constant.JWT_SECRET, JwtUtil.generalSubject(sysUser));
				Map<String, Object> result = new HashMap<String, Object>();
				result.put("token",token);
				result.put("loginName",sysUser.getLoginName()); //把用户信息返回,方便显示.
				JsonResult jsonResult = JsonResult.custom(result);
				return ResponseEntity.ok(jsonResult);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
	
	///// ==========================================================================
	
	
	@RequestMapping(value = "userpost", method = RequestMethod.POST)
	public ResponseEntity<JsonResult> postUser(
			@RequestParam("loginName") String loginName,
			@RequestParam("password") String password){
		// 测试使用, 接收前端提交的 表单格式数据.
		try {
    		//Integer num = this.sysUserService.save(sysUser);
    		Integer num = 1;
			if (num == 1) {
				JsonResult result = JsonResult.ok();
				return ResponseEntity.ok(result);
			}
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
	
	
	@RequestMapping(value = "user", method = RequestMethod.POST)
	public ResponseEntity<JsonResult> saveUser(@RequestBody SysUser sysUser){
		// 测试使用, 接收前端提交的 json 格式数据.
		System.out.println("loginName=" + sysUser.getLoginName());
		try {
			//Integer num = this.sysUserService.save(sysUser);
			Integer num = 1;
			if (num == 1) {
				JsonResult result = JsonResult.custom(sysUser);
				return ResponseEntity.ok(result);
			}
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
	
	
	
	
	///=================================================================================
	
	@RequestMapping(value = "login2")
	public ResponseEntity<JsonResult> login2(){
		
//		Enumeration<String> names = request.getHeaderNames();
//		System.out.println("postman-token=" + request.getHeader("postman-token"));
//		System.out.println("===================================================================");
//		while (names.hasMoreElements()) {
//			String name = (String) names.nextElement();
//			System.out.println(name + ":" + request.getHeader(name));
//		}
//		System.out.println("===================================================================");
		
		try {
			SysUser sysUser = this.sysUserService.queryById("1");
			String token = null;
			if (sysUser != null) {
				token = JwtUtil.generateToken("signingKey", sysUser.getLoginName());
				token = JwtUtil.generateToken(Constant.JWT_SECRET, JwtUtil.generalSubject(sysUser));
				Map<String, Object> result = new HashMap<String, Object>();
				result.put("token",token);
				result.put("loginName",sysUser.getLoginName()); //把用户信息返回,方便显示.
				JsonResult jsonResult = JsonResult.custom(result);
				return ResponseEntity.ok(jsonResult);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
	
	
}
