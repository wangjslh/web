package com.jslh.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jslh.bean.dto.DemoDto;
import com.jslh.bean.dto.DemoDto2;
import com.jslh.bean.vo.SysUserVo;
import com.jslh.model.SysUser;
import com.jslh.model.SysUserExample;
import com.jslh.service.IUserService;
import com.jslh.util.bean.BackData;

@RequestMapping(value = "/user")
@Controller
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	IUserService userService;
	
	@RequestMapping(value = "/listUsers")
	public String listUsers(ModelMap model){
		try {
			SysUserExample e = new SysUserExample();
			e.createCriteria().andCreatetimeGreaterThan(new Date());
			List<SysUser> users = userService.selectUsers(e);
			model.addAttribute("users", users);
		} catch (Exception e) {
			logger.error("查询出错", e);
		}
		return "user/userList";
	}
	
	@RequestMapping(value = "/listXmLObjs")
	@ResponseBody
	public DemoDto listXmLUsers(){
		try {
			DemoDto d = new DemoDto();
			d.setName("mainObj");
			List<DemoDto2> l = new ArrayList<DemoDto2>();
			DemoDto2 d2 = new DemoDto2();
			d2.setName("subObj1");
			l.add(d2);
			d2 = new DemoDto2();
			d2.setName("subObj2");
			l.add(d2);
			d.setData(l);
			return d;
		} catch (Exception e) {
			return new DemoDto();
		}
	}
	
	@RequestMapping(value = "/insertUser", method = RequestMethod.POST)
	@ResponseBody
	public BackData insertUser(ModelMap model, SysUserVo vo){
		try {
			userService.insertSysUser(SysUserVo.converVo2SysUser(vo));
			logger.debug("insert success");
		} catch (Exception e) {
			logger.error("插入出错", e);
			return new BackData(false);
		}
		return new BackData(true);
	}
}
