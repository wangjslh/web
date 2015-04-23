package com.jslh.service;

import java.util.List;

import com.jslh.model.SysArea;

public interface IAreaService {

	List<SysArea> selectDescendants(String code);
	
	List<SysArea> selectChildren(String code);
}
