package com.jslh.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jslh.dao.mapper.SysOrgMapper;
import com.jslh.model.SysOrg;
import com.jslh.model.SysOrgExample;
import com.jslh.service.IOrgService;
import com.jslh.util.BiAssert;
import com.jslh.dao.mapperext.SysOrgExtMapper;

@Service
public class OrgServiceImpl implements IOrgService {

	@Autowired
	SysOrgMapper sysOrgMapper;
	
	@Autowired
	SysOrgExtMapper SysOrgExtMapper;
	
	private final int PIECE_LENGTH = 5;
	
	@Override
	public int deleteByExample(SysOrgExample example) {
		return sysOrgMapper.deleteByExample(example);
	}

	@Override
	public int deleteByPrimaryKey(Long id) {
		return sysOrgMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void insertSysOrg(String name, String parentPath, String description) {
		BiAssert.hasText(name, "the name of org must not be null or empty");
		//insert data
		SysOrg record = new SysOrg();
		record.setName(name);
		record.setDescription(description);
		sysOrgMapper.insert(record);
		//update data
		if(parentPath != null && !parentPath.trim().equals("")){
			parentPath = parentPath.trim();
		}else{
			parentPath = "";
		}
		BiAssert.notNull(record.getId());
		record.setPath(parentPath + this.createPiece(record.getId().toString()));
		int updateCount = sysOrgMapper.updateByPrimaryKey(record);
		BiAssert.isTrue(updateCount > 0);
	}

	@Override
	public List<SysOrg> selectByExample(SysOrgExample example) {
		return sysOrgMapper.selectByExample(example);
	}

	@Override
	public SysOrg selectByPrimaryKey(Long id) {
		return sysOrgMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByExampleSelective(SysOrg record, SysOrgExample example) {
		return sysOrgMapper.updateByExampleSelective(record, example);
	}

	@Override
	public int updateByExample(SysOrg record, SysOrgExample example) {
		return sysOrgMapper.updateByExample(record, example);
	}

	@Override
	public int updateByPrimaryKeySelective(SysOrg record) {
		return sysOrgMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(SysOrg record) {
		return sysOrgMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<SysOrg> selectDescendants(String path) {
		return SysOrgExtMapper.selectDescendants(path);
	}

	@Override
	public List<SysOrg> selectChildren(String path) {
		int pathLength = this.PIECE_LENGTH;
		if(StringUtils.isNotBlank(path)){
			path = path.trim();
			BiAssert.isTrue(path.length() % this.PIECE_LENGTH == 0, "the param of path is not correct");
			pathLength = (path.length() / this.PIECE_LENGTH + 1) * this.PIECE_LENGTH;
		}else{
			path = null;
		}
		return SysOrgExtMapper.selectChildren(path, pathLength);
	}

	private String createPiece(String code){
		BiAssert.hasText(code);
		code = code.trim();
		int expandNum = this.PIECE_LENGTH - code.length();
		BiAssert.isTrue(expandNum >= 0);
		StringBuffer buf = new StringBuffer();
		for(int i = 0; i < expandNum; i++){
			buf.append("0");
		}
		return buf.append(code).toString();
	}
}
