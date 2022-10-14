package org.kp.salesconnect.biz.metadataloader.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
/**
 * @author A670691
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExceptionResponse extends MetadataLoaderServiceResponse<IDomain> implements Serializable, IDomain{

	private static final long serialVersionUID = -3696274602715052932L;

	public ExceptionResponse() {
		super();
	}

	private String code;

	private String message;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
	
	@Override
	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return new StringBuilder()
				.append("ExceptionResponse [code=")
				.append(code)
				.append(", message=")
				.append(message)
				.append("]")
				.toString();
	}

}
