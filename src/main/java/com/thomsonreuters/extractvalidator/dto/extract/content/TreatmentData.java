package com.thomsonreuters.extractvalidator.dto.extract.content;


import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;


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
