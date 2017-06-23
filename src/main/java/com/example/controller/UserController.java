package com.example.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.SysUser;
import com.example.service.SysUserService;
import com.example.utils.JsonResult;
import com.github.pagehelper.PageInfo;

@RestController
@RequestMapping(value = "api")
public class UserController {
	@Autowired
	private SysUserService sysUserService;
	
	
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public ResponseEntity<JsonResult> getPageListAndOrderBy(
			@RequestParam(value = "page") Integer page,
			@RequestParam(value = "rows") Integer rows,
			@RequestParam(value = "sortField", defaultValue = "id") String sortField,
			@RequestParam(value = "sortOrder", defaultValue = "asc") String sortOrder) {
		try {
			PageInfo<SysUser> pageInfo = sysUserService.queryPageListByWhereAndOrderBy(new SysUser(), page, rows, sortField, sortOrder);
			if (pageInfo != null) {
				JsonResult result = JsonResult.custom(pageInfo.getTotal(), pageInfo.getList());
				return ResponseEntity.ok(result);
			}
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
	
	 /**
     * 获取单个用户详情
     * @param id
     * @return
     */
    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public ResponseEntity<JsonResult> user(@PathVariable("id") String id) {
    	try {
			SysUser SysUser = this.sysUserService.queryById(id);
			if (SysUser != null) {
				JsonResult result = JsonResult.custom(SysUser);
				return ResponseEntity.ok(result);
			}
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
	
	
    /**
     * 新增用户
     * @param SysUser
     * @return
     */
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ResponseEntity<JsonResult> addUser(@RequestBody SysUser sysUser) {
    	try {
    		Integer num = this.sysUserService.saveSelective(sysUser);
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
	
	
    /**
     * 更新用户
     * @param id
     * @param SysUser
     * @return
     */
	@RequestMapping(value = "/users/{id}", method = RequestMethod.PUT)
	public ResponseEntity<JsonResult> updateUser(@PathVariable("id") String id, @RequestBody SysUser sysUser) {
		// 前后端分离,前端通过json的方式传输数据.
		try {
			sysUser.setId(id);
			Integer num = this.sysUserService.updateSelective(sysUser);
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
	
	
	/**
     * 删除用户
     * @param id
     * @return
     */
    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<JsonResult>  deleteUser(@PathVariable("id") String id) {
    	try {
			Integer num = this.sysUserService.deleteById(id);
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
	
	
	//======================================================
	//======================================================
	//======================================================
	//======================================================
	//======================================================
	
	@RequestMapping(value = "test")
	public ResponseEntity<JsonResult> test(String loginname,String pwd, HttpServletRequest request) {
		System.out.println("Authorization=" + request.getHeader("Authorization"));
		System.out.println("loginname=" + request.getParameter("loginname"));
		System.out.println("pwd=" + pwd);
		try {
			SysUser user = new SysUser();
			user.setLoginName(loginname);
			user.setPwd(pwd);
			SysUser sysUser = this.sysUserService.queryOne(user);
			JsonResult jsonResult = JsonResult.custom(sysUser);
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
