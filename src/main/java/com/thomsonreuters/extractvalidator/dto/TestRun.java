package com.thomsonreuters.extractvalidator.dto;


import lombok.Data;


/**
 * TestRun Description.
 *
 * @author Matt Godsey
 */
@Data
public final class TestRun
{
	private String testRunNumber;
	private String testCompanyName;
	private String testExtractConfigName;
	private String determinationBaseUrl;
	private String contentExtractBaseUrl;
	private String databaseUrl;
	private String databaseUserName;
	private String databasePassword;
}
