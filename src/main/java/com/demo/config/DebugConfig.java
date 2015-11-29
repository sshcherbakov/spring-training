package com.demo.config;

import java.lang.reflect.Proxy;

import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Profile;

import com.demo.aop.LoggingJDKProxy;
import com.demo.controller.DeepThoughtController;
import com.demo.controller.IDeepThoughtController;
import com.demo.service.IDeepThoughtService;
import com.demo.service.IVogonprotokollService;
import com.demo.utils.LoggingBeanFactoryPostProcessor;
import com.demo.utils.LoggingBeanPostProcessor;
import com.demo.utils.LoggingSmartLifecycle;

@Profile("debug")
@EnableAspectJAutoProxy		// <-- enables @Aspect annotations processing
@Configuration
public class DebugConfig {
	
	@Bean
	public LoggingBeanFactoryPostProcessor loggingBeanFactoryPostProcessor() {
		return new LoggingBeanFactoryPostProcessor();
	}
	
	@Bean
	public LoggingBeanPostProcessor loggingBeanPostProcessor() {
		return new LoggingBeanPostProcessor();
	}
	
	@Bean
	public LoggingSmartLifecycle loggingSmartLifecycle() {
		return new LoggingSmartLifecycle();
	}

	
	// TODO: 1. Wrap controller into a LoggingJDKProxy
	
	@Bean
	public IDeepThoughtController deepThoughtController(
			IDeepThoughtService deepThoughtService,
			IVogonprotokollService vogonprotokollService) {
		
		DeepThoughtController controller = new DeepThoughtController(
				deepThoughtService, vogonprotokollService);

		// Create the proxy
		IDeepThoughtController proxy = (IDeepThoughtController) Proxy.newProxyInstance(
				DeepThoughtController.class.getClassLoader(),
				controller.getClass().getInterfaces(), new LoggingJDKProxy(controller));
		
		return proxy;
	}

	
	// TODO: 2. Alternative way of manual proxy creation (independent from @EnableAspectJAutoProxy)
	// 			Remove the controller JDK proxy first
	
	@Bean
	public BeanNameAutoProxyCreator loggingProxyFactory() {
		
		BeanNameAutoProxyCreator factory = new BeanNameAutoProxyCreator();
		factory.setBeanNames("deep*");
		factory.setInterceptorNames("loggingInterceptor");
		
		return factory;
	}
	
	
}