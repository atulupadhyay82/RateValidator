package com.thomsonreuters.extractvalidator.dto.extract.content;


import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;


/**
 * TreatmentData Description.
 *
 * @author Matt Godsey
 */
@Data
public final class TreatmentData
{
	private LocalDateTime fromDate;
	private LocalDateTime toDate;
	private List<Treatment> treatments;
}
