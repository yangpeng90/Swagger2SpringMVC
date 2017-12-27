package com.yichangapp.config.encDecAdvice;

import org.apache.commons.codec.binary.Base64;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yichangapp.annotation.EncryptResponseBody;
import com.yichangapp.utils.cryptograph.AESutils;

@ControllerAdvice
public class EncryptResponseBodyAdvice implements ResponseBodyAdvice<Object>{
	
	private static Logger logger = LogManager.getLogger(EncryptResponseBodyAdvice.class.getName());

	@Value("${CRYPTOGRAPH_SECRET}")
	private String CRYPTOGRAPH_SECRET;
	
	@Value("${CRYPTOGRAPH_CHARSET}")
	private String CRYPTOGRAPH_CHARSET;
	
	@Override
	public boolean supports(MethodParameter returnType,
			Class<? extends HttpMessageConverter<?>> converterType) {
		return true;
	}

	@Override
	public Object beforeBodyWrite(Object body, MethodParameter returnType,
			MediaType selectedContentType,
			Class<? extends HttpMessageConverter<?>> selectedConverterType,
			ServerHttpRequest request, ServerHttpResponse response) {
		/*
		 * 如果想实现app和web公用接口，可以利用request的请求头信息区别是app还是web
		 * 然后加密的条件是app客户端并且有注解执行加密，
		 */
		// logger.debug("响应json数据前执行");
		
		if(returnType.hasMethodAnnotation(EncryptResponseBody.class)) {
			logger.debug("++++++加密++++++方法：" + returnType.getMethod().getName()+"返回加密数据");
			// 使用jackson序列号body
			ObjectMapper objectMapper = new ObjectMapper();
			String bodyStr = null;
			try {
				bodyStr = objectMapper.writeValueAsString(body);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			// 加密
			byte[] contentBytes = AESutils.encryptStrToArr(bodyStr, CRYPTOGRAPH_SECRET, null, CRYPTOGRAPH_CHARSET);
			// 转换加密数组为Base字符串
			return Base64.encodeBase64String(contentBytes);
		}
		
		return body;
	}

}
