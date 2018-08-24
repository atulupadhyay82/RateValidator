package com.thomsonreuters.extractvalidator.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * TestResult Description.
 *
 * @author Matt Godsey
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public final class TestResult
{
	private String status;
	private String message;
}
