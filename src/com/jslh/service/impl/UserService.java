package com.jslh.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jslh.dao.mapper.SysUserMapper;
import com.jslh.dao.mapper.SysUserRoleMapper;
import com.jslh.dao.mapperext.SysUserExtMapper;
import com.jslh.model.SysUser;
import com.jslh.model.SysUserExample;
import com.jslh.model.SysUserRoleKey;
import com.jslh.model.ext.SysUserExt;
import com.jslh.service.IUserService;
import com.jslh.util.BiAssert;
import com.jslh.util.page.Page;

@Service
public class UserService implements IUserService {

	@Autowired
	SysUserMapper sysUserMapper;
	
	@Autowired
	SysUserExtMapper sysUserExtMapper;
	
	@Autowired
	SysUserRoleMapper sysUserRoleMapper;

	@Override
	public List<SysUser> selectUsers(SysUserExample example) throws Exception {
		return sysUserMapper.selectByExample(example);
	}

	@Override
	public void insertSysUser(SysUser record) throws Exception {
		BiAssert.notNull(record);
		BiAssert.hasText(record.getAccount());
		record.setCreatetime(new Date());
		record.setModifytime(new Date());
		record.setStatus((byte) 0);
		sysUserMapper.insert(record);
	}

	@Override
	public int updateSysUserByExampleSelective(SysUser record, SysUserExample example)
			throws Exception {
		BiAssert.notNull(record);
		return sysUserMapper.updateByExampleSelective(record, example);
	}

	@Override
	public int updateSysUserByPrimaryKeySelective(SysUser record) throws Exception {
		BiAssert.notNull(record);
		return sysUserMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public void insertSysUserRole(SysUserRoleKey record) throws Exception {
		sysUserRoleMapper.insert(record);
	}

	@Override
	public Page<SysUserExt> selectUserExt(SysUser user, Integer pageNo,
			Integer pageSize) throws Exception {
		Page<SysUserExt> page = new Page<SysUserExt>(pageNo, pageSize);
		List<SysUserExt> list = sysUserExtMapper.selectUserExt(user, (page.getPageNo() - 1) * page.getPageSize(), page.getPageSize());
		int count = sysUserExtMapper.countUserExt(user, (page.getPageNo() - 1) * page.getPageSize(), page.getPageSize());
		page.setResults(list);
		page.setTotalCount(count);
		return page;
	}
	
}
