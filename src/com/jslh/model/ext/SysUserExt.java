package com.jslh.model.ext;

import java.util.List;

import com.jslh.model.SysOrg;
import com.jslh.model.SysUser;
import com.jslh.model.SysUserRoleKey;

public class SysUserExt extends SysUser {

	private List<SysUserRoleKey> roles;
	private SysOrg org;

	public List<SysUserRoleKey> getRoles() {
		return roles;
	}

	public void setRoles(List<SysUserRoleKey> roles) {
		this.roles = roles;
	}

	public SysOrg getOrg() {
		return org;
	}

	public void setOrg(SysOrg org) {
		this.org = org;
	}

}
