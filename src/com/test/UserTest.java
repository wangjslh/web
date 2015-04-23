package com.test;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.jslh.dao.mapperext.SysUserExtMapper;
import com.jslh.model.SysUser;
import com.jslh.model.ext.SysUserExt;
import com.jslh.service.IUserService;
import com.jslh.util.BiAssert;
import com.jslh.util.page.Page;

public class UserTest extends BaseTest {

	@Autowired
	SysUserExtMapper sysUserExtMapper;
	
	@Autowired
	IUserService userService;
	
	@Test
	public void testSelectUserExt(){
		SysUser user = new SysUser();
		user.setId(1L);
		List<SysUserExt> list = sysUserExtMapper.selectUserExt(user, 0, 10);
		BiAssert.notNull(list);
	}
	
	@Test
	public void testSelectUserExt2() throws Exception{
		SysUser user = new SysUser();
		user.setId(1L);
		Page<SysUserExt> page = userService.selectUserExt(user, 0, 10);
		BiAssert.notNull(page);
	}
}
