package com.jslh.service;

import java.util.List;

import com.jslh.model.SysUser;
import com.jslh.model.SysUserExample;
import com.jslh.model.SysUserRoleKey;
import com.jslh.model.ext.SysUserExt;
import com.jslh.util.page.Page;

public interface IUserService {

	List<SysUser> selectUsers(SysUserExample example) throws Exception;
	
	void insertSysUser(SysUser record) throws Exception;
	
	int updateSysUserByExampleSelective(SysUser record, SysUserExample example) throws Exception;

    int updateSysUserByPrimaryKeySelective(SysUser record) throws Exception;
    
    void insertSysUserRole(SysUserRoleKey record) throws Exception;
    
    Page<SysUserExt> selectUserExt(SysUser user, Integer pageNo, Integer pageSize) throws Exception;
    
}
