package com.yichangapp.mine.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.yichangapp.mine.mapper.RoleMapper;
import com.yichangapp.po.userInfo.Role;

/**
 * 这里为测试程序
 * 测试表单数据的绑定
 * @author 杨鹏
 *
 */

@Api(tags="角色信息接口")
@RestController
@RequestMapping("/roleInfo")
public class RoleController {
	
	@Autowired
	private RoleMapper roleMapper;
	
	private static Logger logger = LogManager.getLogger(RoleController.class.getName());
	
	@ApiOperation(value="根据id查询角色", 
			notes="测试json的返回",
			response=Role.class,
			tags="角色信息接口")
	@RequestMapping(value = "/getRoleById/{id}", method={RequestMethod.GET},
			produces = { "application/xml", "application/json" })
	public ResponseEntity<Role> getRoleById(@ApiParam(value="角色id", name="id") @PathVariable("id") String id) {
		Role role = roleMapper.selectByPrimaryKey(id);
		return ResponseEntity.ok(role);
	}
	
	/*
	 * 测试表单格式数据请求
	 */
	@ApiOperation(value="插入单个角色", 
		notes="测试表单数据的绑定", 
		response=Void.class,
		tags="角色信息接口")
	@RequestMapping(value="/insertRole", method={RequestMethod.POST}, 
			produces = { "application/xml", "application/json" }, 
	        consumes = { "application/x-www-form-urlencoded" })
	public ResponseEntity<Void> insertRole(@ApiParam(value="角色id", required=true) @RequestPart(value="id", required=false) @RequestParam String id,
			@ApiParam(value="用户的id", required=true) @RequestPart(value="uid", required=false) @RequestParam String uid,
			@ApiParam(value="角色名", required=true) @RequestPart(value="roleName", required=false) @RequestParam String roleName) {
		
		// System.out.println(id+"-----------" + uid + "-----------" + roleName);
		
		logger.debug(id+"-----" + uid + "-----" + roleName);
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}
