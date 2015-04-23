package com.jslh.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jslh.model.SysOrg;
import com.jslh.service.IOrgService;
import com.jslh.util.bean.BackData;

@RequestMapping(value = "/org")
@Controller
public class OrgController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	IOrgService orgService;
	
	@RequestMapping(value = "/insertOrg", method = RequestMethod.POST)
	@ResponseBody
	public BackData insertArea(String name, String parentPath, String description){
		try {
			orgService.insertSysOrg(name, parentPath, description);
		} catch (Exception e) {
			logger.error("插入出错", e);
			return new BackData(false);
		}
		return new BackData(true);
	}
	
	@RequestMapping(value = "/toAddOrg")
	public String toAddArea(ModelMap model, Long areaId){
		try {
			List<SysOrg> provinces = orgService.selectChildren(null);
			model.addAttribute("provinces", provinces);
		} catch (Exception e) {
			logger.error("查询机构出错", e);
		}
		return "area/addArea";
	}
	
	@RequestMapping(value = "/listOrg", method = RequestMethod.POST)
	@ResponseBody
	public BackData listArea(ModelMap model, String parentPath){
		try {
			List<SysOrg> provinces = orgService.selectDescendants(parentPath);
			return new BackData(true, provinces);
		} catch (Exception e) {
			logger.error("查询机构出错", e);
			return new BackData(false);
		}
	}
}
