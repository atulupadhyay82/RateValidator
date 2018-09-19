package com.thomsonreuters.extractvalidator.dto.determination;


import java.math.BigDecimal;

import javax.validation.constraints.Size;

import lombok.Data;


/**
 * Holds the model scenario exemption details information. Used for both scenario and line level implementations.
 *
 * @author Nandha Kumar
 */
@Data
public final class UiModelScenarioExemptions
{
	/**
	 * Holds the flag if a scenario is exempt for an authority.
	 */
	private Boolean isExempt;

	/**
	 * Holds the certificate number for the chosen exemption authority.
	 */
	@Size(max = 100, message = "Certificate Number must not exceed 100 Characters.")
	private String certificateNumber;

	/**
	 * Holds the reason code for the exemption.
	 */
	@Size(max = 20, message = "Reason Code must not exceed 20 Characters.")
	private String reasonCode;

	/**
	 * Holds the exempt Amount value.
	 */
	private BigDecimal exemptAmount;
}
