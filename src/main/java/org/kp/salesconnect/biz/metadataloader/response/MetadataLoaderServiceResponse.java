package org.kp.salesconnect.biz.metadataloader.response;

/**
 * @author A670691
 *
 */
public class MetadataLoaderServiceResponse<T extends IDomain>{
	

	public MetadataLoaderServiceResponse() {
		super();
	}
	
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return new StringBuilder()
				.append("MetadataLoaderServiceResponse [message=")
				.append(message)
				.append("]")
				.toString();
	}
	
}
