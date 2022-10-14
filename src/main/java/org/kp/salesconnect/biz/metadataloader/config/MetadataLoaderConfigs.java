package org.kp.salesconnect.biz.metadataloader.config;

import java.util.Map;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * This class provides configuration for the application
 * @author A670691
 */
@ConfigurationProperties(prefix = "app")
@EnableConfigurationProperties
@Configuration
public class MetadataLoaderConfigs {
	
	private Map<String, String> schedulertimemap;
	 private String environment;

	public String getEnvironment() {
		return environment;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}
	

	public Map<String, String> getSchedulertimemap() {
		return schedulertimemap;
	}

	public void setSchedulertimemap(Map<String, String> schedulertimemap) {
		this.schedulertimemap = schedulertimemap;
	}

	
	
}