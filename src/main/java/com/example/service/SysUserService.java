package com.example.service;

import java.util.Date;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.model.SysUser;
import com.example.utils.IDUtil;
import com.example.utils.UserThreadLocal;

@Service
public class SysUserService extends BaseService<SysUser>{

	
	// ======================================
	@Transactional(rollbackFor = {IllegalArgumentException.class})
	public Integer save1(SysUser sysUser){
		// 测试事务
		save(sysUser);
		SysUser sysUser2 = new SysUser();
		sysUser2.setId("5");
		sysUser2.setLoginName("guest2");
		sysUser2.setPwd("pass2");
		sysUser2.setCreateBy("1");
		sysUser2.setUpdateBy("1");
		sysUser2.setCreateTime(new Date());
		// sysUser2.setUpdateTime(new Date()); //update_time不能为空,否则会报错.
		save(sysUser2);
		return null;
	}
	

	@Override
	public Integer saveSelective(SysUser record) {
		// 不为空的字段
		SysUser sysUser = UserThreadLocal.get();
		if (sysUser != null) {
			record.setId(IDUtil.genSID());
			record.setCreateBy(sysUser.getId());
			record.setUpdateBy(sysUser.getId());
			Date date = new Date();
			// record.setCreateTime(date);
			record.setUpdateTime(date);
			return super.saveSelective(record);
		}
		return 0;
	}
	
	
	@Override
	public Integer updateSelective(SysUser record) {
		SysUser sysUser = UserThreadLocal.get();
		if (sysUser != null) {
			record.setUpdateBy(sysUser.getId());
			record.setUpdateTime(new Date());
			return super.updateSelective(record);
		}
		return 0;
	}
	
}
