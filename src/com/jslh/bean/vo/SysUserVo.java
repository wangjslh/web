package com.jslh.bean.vo;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

import com.jslh.model.SysUser;
import com.jslh.util.BiAssert;

public class SysUserVo extends SysUser {

	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date birthday;

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	public static SysUser converVo2SysUser(SysUserVo vo){
		BiAssert.notNull(vo);
		SysUser user = new SysUser();
		BeanUtils.copyProperties(vo, user);
		return user;
	}
}
