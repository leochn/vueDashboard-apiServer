package com.example.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pojo.SysUser;
import com.example.service.SysUserService;
import com.example.utils.JsonResult;

@RestController
@RequestMapping(value = "api")
public class UserController {
	@Autowired
	private SysUserService sysUserService;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//======================================================
	
	@RequestMapping(value = "test")
	public ResponseEntity<JsonResult> test(String username,String password, HttpServletRequest request) {
		System.out.println("Authorization=" + request.getHeader("Authorization"));
		System.out.println("username=" + request.getParameter("username"));
		System.out.println("password=" + password);
		try {
			JsonResult jsonResult = JsonResult.ok();
			System.out.println("test..........");
			return ResponseEntity.ok(jsonResult);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}

	@RequestMapping(value = "us/{uid}")
	public ResponseEntity<JsonResult> us(@PathVariable String uid) {
		System.out.println("uid======" + uid);
		try {
			JsonResult jsonResult = JsonResult.ok();
			return ResponseEntity.ok(jsonResult);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
	
	
	@RequestMapping(value = "save")
	public ResponseEntity<JsonResult> save() {
		try {
			SysUser sysUser2 = new SysUser();
			sysUser2.setId("21");
			sysUser2.setLoginName("guest2");
			sysUser2.setPwd("pass2");
			sysUser2.setCreateBy("1");
			sysUser2.setUpdateBy("1");
			sysUser2.setCreateTime(new Date());
			sysUser2.setUpdateTime(new Date());
			this.sysUserService.save(sysUser2);
			JsonResult jsonResult = JsonResult.ok();
			return ResponseEntity.ok(jsonResult);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
}
