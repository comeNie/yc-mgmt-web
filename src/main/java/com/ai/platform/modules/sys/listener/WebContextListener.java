package com.ai.platform.modules.sys.listener;

import javax.servlet.ServletContext;

import org.springframework.web.context.WebApplicationContext;

import com.ai.opt.sdk.components.mcs.MCSClientFactory;
import com.ai.paas.ipaas.mcs.interfaces.ICacheClient;
import com.ai.platform.modules.sys.service.SystemService;

public class WebContextListener extends org.springframework.web.context.ContextLoaderListener {
	private final static ICacheClient _jedisGnarea = MCSClientFactory.getCacheClient("com.ai.platform.common.cache.gnarea");

	@Override
	public WebApplicationContext initWebApplicationContext(ServletContext servletContext) {
		_jedisGnarea.del("areaTreeALL","OfficeAllList","findTreeInit");
		if (!SystemService.printKeyLoadMessage()){
			return null;
		}
		
		
		
		return super.initWebApplicationContext(servletContext);
	}
}
