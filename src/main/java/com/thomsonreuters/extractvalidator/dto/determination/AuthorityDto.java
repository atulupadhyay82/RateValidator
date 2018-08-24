package com.thomsonreuters.extractvalidator.dto.determination;


import java.util.List;

import lombok.Data;


/**
 * A data transfer object representing an authority.
 *
 * @author Neal Schultz
 */
@Data
public final class AuthorityDto
{
	/**
	 * The company roles for this authority.
	 */
	private List<CompanyRoleDto> companyRoles;
}
