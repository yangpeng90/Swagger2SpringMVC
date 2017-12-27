package com.yichangapp.mine.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yichangapp.annotation.DecryptRequestBody;
import com.yichangapp.annotation.EncryptResponseBody;
import com.yichangapp.mine.service.UserService;
import com.yichangapp.po.response.Page;
import com.yichangapp.po.userInfo.User;

/**
 * 用户信息控制器
 * 这里是测试程序
 * @author 杨鹏
 *
 */

// tags="用户信息接口"相当于分组
@Api(tags="用户信息接口")
@RestController
@RequestMapping(value="/userInfo")
public class UserInfoController {
	
	private static Logger logger = LogManager.getLogger(UserInfoController.class.getName());
	
	// 依赖注入
	@Resource(name="userService")
	private UserService userService;
	
	/*|*******************************************************************************
	 * header请求参数的获取：@ApiParam 配合 @RequestHeader 获取请求头的值 
	 * query请求参数的获取：@ApiParam 配合 @RequestParam 获取链接参数的值 ?aa=AA&bb=BB POST可以使用
	 * path请求参数的获取：@ApiParam 配合 @PathVariable 获取路径的值，如/aaa/{id}
	 * body请求参数获取：@ApiParam 配合 @RequestBody 从输入流获取文本，如json串
	 * form请求参数获取：@ApiParam 配合 @RequestPart 和 @RequestParam 获取表单参数的值，如k/v
	 ********************************************************************************|*/
	
	/*
	 * 表单post请求，响应json
	 */
	@ApiOperation(value="根据id查询用户", 
		notes="测试json的返回",
		response=User.class,
		tags="用户信息接口")
//	//暗含的参数
//	@ApiImplicitParams(value={
//		@ApiImplicitParam(name="id", value="用户id", required=true, paramType="query", dataType="String")
//	})
	@RequestMapping(value="/getUserByid", method={RequestMethod.GET},
		produces=MediaType.APPLICATION_JSON_UTF8_VALUE,
		consumes=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@EncryptResponseBody
	public ResponseEntity<User> getUserById(@ApiParam(value="用户id", name="id") @RequestParam String id) {
		
		User user = userService.getUserById(id);
		
//		com.yichangapp.po.response.ResponseEntity<User> resEntity = new com.yichangapp.po.response.ResponseEntity<User>();
//		resEntity.setStatus(1);
//		resEntity.setMessage("success");
//		resEntity.setData(user);
		
		logger.debug("查询id=" + id + "的用户");
		
//		return ResponseEntity.status(HttpStatus.OK).body(user);
//		return new ResponseEntity<User>(user, HttpStatus.OK);
//		ResponseEntity.notFound().headers(headers).build();
		
		return ResponseEntity.ok(user);
		//return resEntity;
	}

	/*
	 * 测试模型属性，表单post请求，json响应
	 * post请求编码问题没有解决，这是swagger的自身问题不影响正常开发
	 */
	@ApiOperation(value="插入单个用户", 
		notes="暂时不支持中文测试，请使用英文测试！测试表单的封装", 
		response=Void.class,
		tags="用户信息接口")
	@RequestMapping(value="/insertUser", method={RequestMethod.POST}, 
		produces=MediaType.APPLICATION_JSON_UTF8_VALUE,
		consumes=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<Void> insertUser(
		// @modelAttribute使得api文档显示pojo的属性上@ApiModelProperty的信息，书写方便
		@ApiParam(value="用户") @ModelAttribute User user) throws UnsupportedEncodingException {
		
		user.setId(UUID.randomUUID().toString().replace("-", ""));
		user.setCreateTime(new Date());
		
		userService.insertUser(user);
		
//		com.yichangapp.po.response.ResponseEntity<ApiOperationInfo> resEntity = new com.yichangapp.po.response.ResponseEntity<ApiOperationInfo>();
//		resEntity.setStatus(1);
//		resEntity.setMessage("success");
		
		// 其实swagger使用ISO-8859-1，极有可能是我们的设置paramType="query"所致，
		String nickname = new String(user.getNickname().getBytes("ISO-8859-1"), "UTF-8");
		
		logger.debug("nickname=" + nickname);
//		logger.debug(new Date().toString());
		
//		HttpHeaders httpHeaders = new HttpHeaders();
//		httpHeaders.setDate(new Date().getTime());
//		ApiOperationInfo body = new ApiOperationInfo("操作成功");
		
//		resEntity.setData(body);
//		return new ResponseEntity<ApiOperationInfo>(body, httpHeaders, HttpStatus.OK);
		return new ResponseEntity<Void>(HttpStatus.OK);
		
//		return resEntity;
	}
	
	/*
	 * 测试请求json，发送json
	 * 测试数据：{"pageNO": 1, "pageSize": 5}
	 * 加密结果：gtxa7ILygVpbmp9KnYD/6rjias6SGI6AgFW2rMv6zpg=
	 */
	@ApiOperation(value="分页查询用户", 
		notes="分页查询用户", 
		response=User.class,
		responseContainer="List",
		tags="用户信息接口")
	@ApiResponses(value={
		@ApiResponse(code=200, message="success", response=User.class, responseContainer="List"),
		@ApiResponse(code=400, message="Invilid status value")})
	@RequestMapping(value="/userList", method={RequestMethod.POST}, 
		produces=MediaType.APPLICATION_JSON_UTF8_VALUE,
		consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
//		produces=MediaType.TEXT_PLAIN_VALUE,
//		consumes=MediaType.TEXT_PLAIN_VALUE
	@EncryptResponseBody
	public ResponseEntity<List<User>> userList(@ApiParam(value="page分页") @RequestBody @DecryptRequestBody Page page) {
		
		List<User> userList = userService.userList(page);
		
		return ResponseEntity.ok(userList);
	}
}
