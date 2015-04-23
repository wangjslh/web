package com.jslh.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jslh.dao.mapperext.SysAreaExtMapper;
import com.jslh.model.SysArea;
import com.jslh.service.IAreaService;

@Service
public class AreaService implements IAreaService {

	@Autowired
	SysAreaExtMapper SysAreaExtMapper;
	
	private final int PIECE_LENGTH = 2;

	@Override
	public List<SysArea> selectDescendants(String code) {
		return SysAreaExtMapper.selectDescendants(code);
	}

	@Override
	public List<SysArea> selectChildren(String code) {
		int codeLength = this.PIECE_LENGTH;
		if(StringUtils.isNotBlank(code)){
			code = code.trim();
			codeLength = (code.length() / this.PIECE_LENGTH + 1) * this.PIECE_LENGTH;
		}else{
			code = null;
		}
		return SysAreaExtMapper.selectChildren(code, codeLength);
	}
}
