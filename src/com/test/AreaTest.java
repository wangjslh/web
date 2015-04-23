package com.test;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.jslh.model.SysArea;
import com.jslh.service.IAreaService;

public class AreaTest extends BaseTest {

	@Autowired
	IAreaService sysAreaService;
	
	
	@Test
	public void testInsertSysArea(){
	}
	
	@Test
	public void testSelectChildren(){
		List<SysArea> list = sysAreaService.selectChildren("");
		list = sysAreaService.selectChildren("00012");
	}
}
