package org.kp.salesconnect.biz.metadataloader.service;

import java.util.Map;

import org.springframework.stereotype.Service;

/**
 * This provides services for application services 
 * @author A670691
 *
 */
@Service
public interface MetadataLoaderService {

	/**
	 * method return service health details
	 * @return HealthResponse with details
	 */
	public HealthResponse getServiceHealth();
	
	/**
	 * method return service liveness details
	 * @return Map<String, String> for liveness details
	 */
	public Map<String, String> getServiceLiveness();
	
}
