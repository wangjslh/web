package com.jslh.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jslh.model.SysArea;
import com.jslh.service.impl.AreaService;
import com.jslh.util.bean.BackData;

@Controller
@RequestMapping(value = "/area")
public class AreaController {

	@Resource(name = "areaService")
	private AreaService areaService;
	
	@RequestMapping(value = "/detailAreas")
	public String detailAreas(ModelMap model){
		return "area/detailAreas";
	}
	
	@RequestMapping(value = "/listProvinces")
	@ResponseBody
	public BackData listProvinces(ModelMap model){
		try {
			List<SysArea> provinces = areaService.selectChildren(null);
			return new BackData(true, provinces);
		} catch (Exception e) {
			return new BackData(false);
		}
	}
	
	@RequestMapping(value = "/listDescendants", method = RequestMethod.POST)
	@ResponseBody
	public BackData listDescendants(ModelMap model, String parentPath){
		try {
			List<SysArea> provinces = areaService.selectDescendants(parentPath);
			return new BackData(true, provinces);
		} catch (Exception e) {
			return new BackData(false);
		}
	}
}
