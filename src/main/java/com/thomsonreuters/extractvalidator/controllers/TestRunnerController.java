package com.thomsonreuters.extractvalidator.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import com.thomsonreuters.extractvalidator.dto.RunResults;
import com.thomsonreuters.extractvalidator.dto.TestRun;
import com.thomsonreuters.extractvalidator.services.TestRunnerService;


/**
 * Defines the controller for the test mapping used for running the validation test for the given content extract.
 *
 * @author Matt Godsey
 */
@RestController
@RequestMapping("/test/")
public final class TestRunnerController
{
	/**
	 * Service that manages running the test, and provides feedback.
	 */
	private final TestRunnerService testRunnerService;


	/**
	 * Constructs the controller allowing spring to inject the service bean.
	 *
	 * @param testRunnerService The service that manages the test run.
	 */
	@Autowired
	public TestRunnerController(final TestRunnerService testRunnerService)
	{
		this.testRunnerService = testRunnerService;
	}


	/**
	 * Run the test that validates the extract and extract config against the company config found in Determination, and the correct content found in determination.
	 *
	 * @param data The invoice data to import. Must not be {@code null}.
	 *
	 * @return Returns a result with a status and a message.
	 */
	@CrossOrigin
	@ResponseBody
	@PostMapping(value = "run/static", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
	@ApiOperation(value = "Run the test.", notes = "Run the validation test to verify the provided content extract JSON.")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = TestRun.class),
						   @ApiResponse(code = 400, message = "Bad Request"),
						   @ApiResponse(code = 500, message = "Server error. For more details check the logs.")})
	public RunResults runStaticTest(@ApiParam(value = "The test data.") @RequestBody final TestRun data)
	{
		return testRunnerService.staticRunResults(data);
	}


	/**
	 * Run the test that validates the extract and extract config against the company config found in Determination, and the correct content found in determination.
	 *
	 * @param data The invoice data to import. Must not be {@code null}.
	 *
	 * @return Returns a result with a status and a message.
	 */
	@CrossOrigin
	@ResponseBody
	@PostMapping(value = "run/dynamic", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
	@ApiOperation(value = "Run the test.", notes = "Run the validation test to verify the provided content extract JSON.")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = TestRun.class),
						   @ApiResponse(code = 400, message = "Bad Request"),
						   @ApiResponse(code = 500, message = "Server error. For more details check the logs.")})
	public RunResults runDynamicTest(@ApiParam(value = "The test data.") @RequestBody final TestRun data)
	{
		return testRunnerService.dynamicRunTest(data);
	}
}
