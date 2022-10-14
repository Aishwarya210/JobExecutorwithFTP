package org.kp.salesconnect.biz.metadataloader.serviceImpl;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.kp.salesconnect.biz.metadataloader.constant.MetadataLoaderConstants;
import org.kp.salesconnect.biz.metadataloader.service.MetadataLoaderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.availability.ApplicationAvailability;
import org.springframework.boot.availability.LivenessState;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

/**
 * This provides services for application services
 * 
 * @author A670691
 *
 */

@Service
public class MetadataLoaderServiceImpl implements MetadataLoaderService {

	private static Logger log = LoggerFactory.getLogger(MetadataLoaderServiceImpl.class);
	
	@Autowired
	Environment environment;
	
	@Autowired
    ApplicationAvailability applicationAvailability;

	@Override
	public HealthResponse getServiceHealth() {
		log.debug("MetadataLoaderServiceImpl.getServiceHealth - start");
		HealthResponse healthResponse = new HealthResponse();
		try {
			String cfInstanceIndexString = environment.getProperty(MetadataLoaderConstants.CF_INSTANCE_INDEX.toString());

			if (null != cfInstanceIndexString && cfInstanceIndexString.chars().allMatch(Character::isDigit)) {
				healthResponse.setCfInstanceIndex(Integer.parseInt(cfInstanceIndexString));
			}
			
			healthResponse.setJavaEnv(String.join(",", environment.getActiveProfiles()));
			healthResponse.setLastUpdatedAt(Calendar.getInstance().getTime().toString());
			healthResponse.setStatus(MetadataLoaderConstants.UP.toString());
		} catch (Exception ex) {
			log.debug("MetadataLoaderServiceImpl.getServiceHealth - {}", ex);
			healthResponse.setStatus(MetadataLoaderConstants.DOWN.toString());
		}
		log.debug("MetadataLoaderServiceImpl.getServiceHealth - end");
		return healthResponse;
	}
	
	public Map<String, String> getServiceLiveness(){
		log.debug("DirectoryDataStreamController.getServiceLiveness - start");
		Map<String, String> response = new HashMap<>();
		if(LivenessState.CORRECT.equals(applicationAvailability.getLivenessState())) {
			response.put(CommonConstants.STATUS.getValue(), CommonConstants.UP.getValue());
		}else {
			response.put(CommonConstants.STATUS.getValue(), CommonConstants.DOWN.getValue());
		}
		log.debug("DirectoryDataStreamController.getServiceLiveness - end");
		return response;
	}

}
