package com.yichangapp.config.encDecAdvice;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import com.yichangapp.annotation.DecryptRequestBody;
import com.yichangapp.utils.cryptograph.AESutils;

/*
 * RequestBodyAdvice从spring4.2开始支持
 */
@ControllerAdvice
public class DecryptRequestBodyAdvice implements RequestBodyAdvice {
	
	private static Logger logger = LogManager.getLogger(DecryptRequestBodyAdvice.class.getName());
	
	@Value("${CRYPTOGRAPH_SECRET}")
	private String CRYPTOGRAPH_SECRET;
	
	@Value("${CRYPTOGRAPH_CHARSET}")
	private String CRYPTOGRAPH_CHARSET;

	@Override
	public boolean supports(MethodParameter methodParameter, Type targetType,
			Class<? extends HttpMessageConverter<?>> converterType) {
		return true;
	}

	@Override
	public Object handleEmptyBody(Object body, HttpInputMessage inputMessage,
			MethodParameter parameter, Type targetType,
			Class<? extends HttpMessageConverter<?>> converterType) {
		return body;
	}

	@Override
	public HttpInputMessage beforeBodyRead(final HttpInputMessage inputMessage,
			MethodParameter parameter, Type targetType,
			Class<? extends HttpMessageConverter<?>> converterType)
			throws IOException {
		
//		boolean decrypt = false;
//		
//		// 有解密注解，因此要执行解密操作
//		Annotation[][] parameterAnnos = parameter.getMethod().getParameterAnnotations();
//		for (int i = 0; i < parameterAnnos.length; i++) {
//			for (int j = 0; j < parameterAnnos[i].length; j++) {
//				if(parameterAnnos[i][j] instanceof DecryptRequestBody) {
//					decrypt = true;
//					break;
//				}
//			}
//		}
		
		/*
		 * 如果想实现app和web共用接口，可以利用inputMessage的请求头信息区别是app还是web
		 * 然后加密的条件是app客户端并且有注解执行加密，
		 */
		
		if(parameter.hasParameterAnnotation(DecryptRequestBody.class)) {
			logger.debug("++++++解密数据++++++请求调用方法" + parameter.getMethod().getName());
			return new HttpInputMessage() {
				
				@Override
				public HttpHeaders getHeaders() {
					return inputMessage.getHeaders();
				}
				
				@Override
				public InputStream getBody() throws IOException {
					
					InputStream in = inputMessage.getBody();
					
					// AES解密
					return AESutils.decryptInputStream(in, CRYPTOGRAPH_SECRET, null, CRYPTOGRAPH_CHARSET);
					
				}
			};
		}
		
		return inputMessage;
	}

	@Override
	public Object afterBodyRead(Object body, HttpInputMessage inputMessage,
			MethodParameter parameter, Type targetType,
			Class<? extends HttpMessageConverter<?>> converterType) {
		return body;
	}

}
