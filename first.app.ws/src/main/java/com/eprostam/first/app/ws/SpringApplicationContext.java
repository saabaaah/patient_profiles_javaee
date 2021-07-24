package com.eprostam.first.app.ws;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import ch.qos.logback.core.Context;
import ch.qos.logback.core.spi.ContextAware;
import ch.qos.logback.core.status.Status;

public class SpringApplicationContext implements ApplicationContextAware {

	private static ApplicationContext CONTEXT;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		CONTEXT = applicationContext;
		
	}
	
	public static Object getBean(String name) {
		return CONTEXT.getBean(name);
	}

}
