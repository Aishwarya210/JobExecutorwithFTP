package org.kp.salesconnect.biz.metadataloader.utils;


import org.kp.salesconnect.biz.metadataloader.response.ExceptionResponse;
import org.kp.salesconnect.biz.metadataloader.response.IDomain;
import org.kp.salesconnect.biz.metadataloader.response.MetadataLoaderServiceResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
/**
 * @author Q540870
 *
 */
public class ExceptionResponseUtility {
    private ExceptionResponseUtility(){
        throw new IllegalStateException("Utility Class should not have public constructor");
    }
    
    /**
	 * method constructs exception response for given code, message & HTTP status
	 * @param ode, message & HTTP status to the method
	 * @return ResponseEntity Object
	 */
    public static ResponseEntity<MetadataLoaderServiceResponse<IDomain>> constructExceptionResponse(String code, String message, HttpStatus httpStatus){
    	ExceptionResponse exceptionResponse =  new ExceptionResponse();
    	exceptionResponse.setCode(code);
    	exceptionResponse.setMessage(message);
        return new ResponseEntity<>(exceptionResponse, httpStatus);
    }
}
