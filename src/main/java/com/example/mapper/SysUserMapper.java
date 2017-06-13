package com.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.pojo.SysUser;

@Mapper
public interface SysUserMapper {
	public SysUser getLogin(@Param("loginName")String loginName,@Param("pwd")String password);
}