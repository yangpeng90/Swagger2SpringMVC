# Swagger2SpringMVC

---------
使用swagger注解生成接口文档，集成在线测试功能，使用spring AOP实现APP接口加密，具体使用@ControllerAdvice注解以及自定义注解实现。

----------
# 主配置代码

----------

    package com.yichangapp.config.swagger;

	import io.swagger.annotations.ApiOperation;
	
	import org.springframework.context.annotation.Bean;
	import org.springframework.context.annotation.Configuration;
	import org.springframework.web.servlet.config.annotation.EnableWebMvc;
	
	import springfox.documentation.builders.ApiInfoBuilder;
	import springfox.documentation.builders.PathSelectors;
	import springfox.documentation.builders.RequestHandlerSelectors;
	import springfox.documentation.service.ApiInfo;
	import springfox.documentation.service.Contact;
	import springfox.documentation.spi.DocumentationType;
	import springfox.documentation.spring.web.plugins.Docket;
	import springfox.documentation.swagger2.annotations.EnableSwagger2;
	
		/**
		 * 类描述：配置swagger2信息 
		 * 创建人：yp
		 * 创建时间：2017年8月25日 上午15:43:29
		 * @version 1.0.0
		 */
		
	@Configuration      //让Spring来加载该类配置相当于xml配置文件的<beans>
	@EnableWebMvc       //启用Mvc，非springboot框架需要引入注解@EnableWebMvc
	@EnableSwagger2     //启用Swagger2
	public class Swagger2Config {
		/*	
		 * Docket stands for A summary or other brief statement of the contents of a document; an abstract.
		 * Docket是API的摘要，总结，抽象
		 * Docket helps configure a subset of the services to be documented and groups them by name
		 * Docket帮助配置被文档说明的服务的子集，并且使用名称对服务分组
		 *****/
    @Bean	// 相当于xml配置文件的<bean>
    public Docket customDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
        	// 设置元信息，被包含到资源的响应列表中，元信息使用方法apiInfo()配置获取
        	.apiInfo(apiInfo())
        	// 初始化一个API选择的构建器 返回值：ApiSelectorBuilder
        	.select() 
        	// 与给定注解的处理器方法进行匹配
        	.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
        	// 与设定的请求路径进行匹配
        	// 自己定义的对API的请求路径的规则
        	//.paths(paths())
        	// 对API的请求路径，不做限制，即任意都可
        	.paths(PathSelectors.any())
        	// 如果API接口开发的请求路径不满足此正则，则API接口的说明就不显示到页面
        	//.paths(PathSelectors.regex("/userInfo.*"))
        	// 构建Docket
        	.build();
    }

    private ApiInfo apiInfo() {
    	// 联系人
    	Contact contact = new Contact("yp", "https://www.yichangapp.com", "123456@qq.com");
    	
        return new ApiInfoBuilder()
        // API标题
        .title("球场APP的服务接口")
        // API描述
        .description("Android & IOS API Decument")
        // 服务的URL
        .termsOfServiceUrl("https://www.yichangapp.com/footballMatch")
        // 负责该应用联系人
        .contact(contact)
        // 版本
        .version("1.0.0")
        // 构建应用信息
        .build();
    }
    
    // 如果输入请求满足下列某一路径返回Predicate<String> 进行校验
	//    private Predicate<String> paths() {
	//    	return new Predicate<String>() {
	//
	//			@Override
	//			public boolean apply(String input) {
	//				if(input.matches("/footballMatch.*"))
	//					return true;
	//				//else if(input.matches("/*"))
	//				//	return true;
	//				// ...
	//				else
	//					return false;
	//			}
	//			
	//		};
	//    }
	}

# 主要依赖

----------
    <!-- swagger2-springfox -->
		<dependency>
			<groupId>io.springfox</groupId>
			<artifactId>springfox-swagger2</artifactId>
			<version>2.6.1</version>
		</dependency>
		<dependency>
			<groupId>io.springfox</groupId>
			<artifactId>springfox-swagger-ui</artifactId>
			<version>2.6.1</version>
		</dependency>
		<!-- swagger2-springfox -->

		<!-- springMVC4解析json,响应json -->
		<dependency>
			<groupId>com.fasterxml.jackson.core</groupId>
			<artifactId>jackson-core</artifactId>
			<version>2.6.5</version>
		</dependency>
		<dependency>
			<groupId>com.fasterxml.jackson.core</groupId>
			<artifactId>jackson-databind</artifactId>
			<version>2.6.5</version>
		</dependency>
		<dependency>
			<groupId>com.fasterxml.jackson.core</groupId>
			<artifactId>jackson-annotations</artifactId>
			<version>2.6.5</version>
		</dependency>
		<!-- springMVC4解析json,响应json -->