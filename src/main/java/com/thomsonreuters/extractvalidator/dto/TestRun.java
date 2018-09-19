package com.thomsonreuters.extractvalidator.dto;


import java.util.List;

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
	private String udsToken;
	private String serviceUser;
	private String servicePassword;
	private List<TestCase> testCases;
}
