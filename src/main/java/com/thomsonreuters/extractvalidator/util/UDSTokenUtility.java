package com.thomsonreuters.extractvalidator.util;


import lombok.Data;
import org.owasp.esapi.ESAPI;
import org.owasp.esapi.Logger;

/**
 * UDS token for use in making public REST service calls to Determination.
 */
@Data
public final class UDSTokenUtility
{
	/**
	 * Logger for this class.
	 */
	private static final Logger LOGGER = ESAPI.getLogger(UDSTokenUtility.class);


	private String username;

	private String password;


	public UDSTokenUtility(String username, String password){
		this.username= username;
		this.password=password;
	}



}
