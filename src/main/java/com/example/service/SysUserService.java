package com.example.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.mapper.SysUserMapper;
import com.example.pojo.SysUser;

@Service
public class SysUserService {
	
	@Autowired
	private SysUserMapper sysUserMapper;
	
	public SysUser getLogin(String loginName,String password){
		return this.sysUserMapper.getLogin(loginName, password);
	}
	
	@Transactional(rollbackFor = {IllegalArgumentException.class})
	public Integer save(SysUser sysUser){
		// 测试事务
		this.sysUserMapper.save(sysUser);
		SysUser sysUser2 = new SysUser();
		sysUser2.setId("5");
		sysUser2.setLoginName("guest2");
		sysUser2.setPwd("pass2");
		sysUser2.setCreateBy("1");
		sysUser2.setUpdateBy("1");
		sysUser2.setCreateTime(new Date());
		// sysUser2.setUpdateTime(new Date()); //update_time不能为空,否则会报错.
		this.sysUserMapper.save(sysUser2);
		return null;
	}
	
}
