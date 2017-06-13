package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mapper.SysUserMapper;
import com.example.pojo.SysUser;

@Service
public class SysUserService {
	
	@Autowired
	private SysUserMapper sysUserMapper;
	
	public SysUser getLogin(String loginName,String password){
		return this.sysUserMapper.getLogin(loginName, password);
	}
}
