package org.kp.salesconnect.biz.metadataloader.constant;

/**
 * This class provides constants for the application
 * @author A670691
 */
public enum MetadataLoaderConstants {	 
	ACCTNO("acctno"),
	FIRSTNAME("firstName"),
	LASTNAME("lastName"),
	FULLNAME("fullName"),
	PROFESSIONALTITLE("professionalTitle"),
	CPMID("cpmid"),
	TITLE("title"),
	MOBILEPHONE("mobilePhone"),
	OFFICEPHONE("officePhone"),
	NOTES("notes"),
	EMAIL("email"),
	PAGERTYPE("pagerType"),
	PAGER("pager"),
	EMPLOYEETYPE("employeeType"),
	ISCORTEXTENABLED("isCortextEnabled"),
	REGISTEREDWITH("registeredWith"),
	CONTACTTYPE("contactType"),
	CORTEXTCONTACT("cortextContact"),
	JOBDESCRIPTION("jobDescription"),	
	IMAGEURL("imageURL"),
	REF_REGION("region"),
	REF_MCID("mcId"),
	REF_MCNAME("mcName"),
	REF_DEPTID("deptId"),
	REF_DEPTNAME("deptName"),
	MCID("id"),
	MCNAME("name"),
	DEPTID("id"), 
	DEPTNAME("name"),
	NATIONAL("NATIONAL"),
	HEALTH_ENDPOINT_URI("HEALTH"),
	UP("UP"),
	DOWN("DOWN"),
	HTTPS("https"),
	CF_INSTANCE_INDEX("CF_INSTANCE_INDEX"),
	DELETE("DELETE"),
	ADD("ADD"),
	ROLE("role"),
	ORDER("order"),
	SORT("sort"),
	UNDERSCORE("_"),
	COLON(":"),
	COMMA(","),
	PERIOD("."),
	REDIS_GET_FIELD_SYMBOL("*->"),
	ONCALLDATACOUNT("oncallDataCount"),
	BUILD_VERSION("BUILD_VERSION"),
	TEMP_REG_KEY("tmp:reg:"),
	UPDATE("UPDATE"),
	REMOVE("REMOVE"),
	HCA("HCA"),
	LASTNAME_SORT_MAX_LENGTH("50"),
	LASTNAME_SORT_APPEND_CHAR("0"),
	REG_FLAG_LUA_PATH("lua/registrationflag.lua"),
	ONCALL_DATA_COUNT_LUA_PATH("lua/script.lua"),	
	ONCALLREFNUM("oncallRefNum"),	
	STARTDATETIME("startdatetime"),
	ENDDATETIME("enddatetime"),
	ALL("ALL"),
	UPDATE_EVENT_COUNT("UPDATE_EVENT_COUNT"),
	DELETE_EVENT_COUNT("DELETE_EVENT_COUNT"),
	LATEST_UPDATE_EVENT_TM("LATEST_UPDATE_EVENT_TM"),
	LATEST_DELETE_EVENT_TM("LATEST_DELETE_EVENT_TM"),
	USERID("USERID"),
	CREATEDBY("CREATEDBY"),
	UPDATEDBY("UPDATEDBY"),
	TEMP_NREG_KEY("tmp:nreg:");
	
	private final String value;
	
	private MetadataLoaderConstants(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
