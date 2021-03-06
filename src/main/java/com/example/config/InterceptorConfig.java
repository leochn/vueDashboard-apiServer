package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 继承WebMvcConfigurerAdapter，复写addInterceptors方法
 * @author leo
 *
 */
@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter{
	
	@Autowired
	private ApiInterceptor apiInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		/**
	     * 主要方法说明：
	     * addPathPatterns 用于添加拦截规则
	     * excludePathPatterns 用户排除拦截
	     */
		//super.addInterceptors(registry);
		registry.addInterceptor(apiInterceptor).addPathPatterns("/api/**").excludePathPatterns("/rest/**");
	}
}
