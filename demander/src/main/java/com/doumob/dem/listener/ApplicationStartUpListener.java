package com.doumob.dem.listener;

import java.util.Map.Entry;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.doumob.handle.HandleContext;
import com.doumob.handle.MessageHandle;

/**
 * 1）实现ApplicationListener重写onApplicationEvent方法
 * 2)实现ServletContextListener
 * @author killer
 *
 */
public class ApplicationStartUpListener implements ServletContextListener {
	private Logger logger=Logger.getLogger(getClass());

//	public void onApplicationEvent(ContextRefreshedEvent event) {
//		ApplicationContext context = event.getApplicationContext();
//		if (context.getParent() == null) {
//			for (Entry<String, MessageHandle> ez : context.getBeansOfType(MessageHandle.class).entrySet()) {
//				System.out.println(ez.getValue().getClass());
//			}
//		}
//	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(sce.getServletContext());
		if (context.getParent() == null) {
			logger.debug("can do anything you want!");
			for (Entry<String, MessageHandle> ez : context.getBeansOfType(MessageHandle.class).entrySet()) {
				HandleContext.registerHandle(ez.getValue());
			}
		}
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
	}

}
