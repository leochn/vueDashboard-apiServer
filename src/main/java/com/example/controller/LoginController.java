package com.example.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.pojo.SysUser;
import com.example.service.SysUserService;
import com.example.utils.JwtUtil;

@RestController
public class LoginController {
	@Autowired
	private SysUserService sysUserService;
	
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public SysUser login(HttpServletResponse httpServletResponse, String loginName, String password){
		return this.sysUserService.getLogin(loginName, password);
	}
	 
	@RequestMapping(value = "doLogin", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> doLogin(@RequestParam("username") String username,
			@RequestParam("password") String password, HttpServletRequest request, HttpServletResponse response) {
		try {
			Map<String, Object> result = new HashMap<String, Object>();
			SysUser sysUser = this.sysUserService.getLogin(username, password);
			String token = null;
			if (sysUser != null) {
				token = JwtUtil.generateToken("signingKey", sysUser.getLoginName());
			}
			if (! StringUtils.isEmpty(token)) {
				result.put("status", 200);
				result.put("token",token);
				return ResponseEntity.ok(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}

}
