package org.kp.salesconnect.biz.metadataloader.controller;


import java.util.Calendar;
import java.util.Map;

import org.kp.salesconnect.biz.metadataloader.constant.MetadataLoaderConstants;
import org.kp.salesconnect.biz.metadataloader.service.MetadataLoaderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * RestController provides rest endPoints for the application
 * {@link RestController}
 * @author A670691
 *
 */
@RestController
@Component
public class MetadataLoaderController implements InfoContributor{

	private static Logger log = LoggerFactory.getLogger(MetadataLoaderController.class);
	
	@Autowired
	Environment environment;
	
	@Autowired
	MetadataLoaderService metadataloaderService;
	
	/**
	 * method return service health details
	 * @return HealthResponse with details
	 */
	@GetMapping(value = "/health")
	public ResponseEntity<HealthResponse> getServiceHealth() {
		log.debug("MetadataloaderController.getServiceHealth - start");
		HealthResponse healthResponse = null;
		try {
			healthResponse = metadataloaderService.getServiceHealth();
		} catch (Exception ex) {
			healthResponse = new HealthResponse();
			healthResponse.setStatus(MetadataLoaderConstants.DOWN.toString());
			log.error("MetadataloaderController.getServiceHealth - {} ", ex);
		}
		log.debug("MetadataloaderController.getServiceHealth - end");
		return new ResponseEntity<>(healthResponse, HttpStatus.OK);
	}

    /**
	 * method returns service liveness details
	 * @return ResponseEntity<Map<String, String>> with details
	 */
	@GetMapping(value = "/livenessprobe")
    public ResponseEntity<Map<String, String>> getServiceLiveness(){
		log.debug("DirectoryDataStreamController.getServiceLiveness - start");
		HttpStatus status = HttpStatus.OK;
		Map<String, String> response = metadataloaderService.getServiceLiveness();
		if(!CollectionUtils.isEmpty(response) &&
				response.get(CommonConstants.STATUS.getValue()).equalsIgnoreCase(CommonConstants.DOWN.getValue())) {
			status = HttpStatus.SERVICE_UNAVAILABLE;
		}
		log.debug("DirectoryDataStreamController.getServiceLiveness - end");
		return new ResponseEntity<>(response, status);
    }

	/**
	 * method add service info details
	 * @param builder of Info.Builder to add info details
	 */
	@Override
	public void contribute(Info.Builder builder) {
		log.debug("MetadataloaderController.contribute - start");
        builder.withDetail("Last updated at",  Calendar.getInstance().getTime().toString());
		log.debug("MetadataloaderController.contribute - end");		
	}
	
}
