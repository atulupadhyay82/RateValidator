package com.thomsonreuters.extractvalidator.dto.determination;


import lombok.Data;


/**
 * Establishments data for the the scenario/line.
 *
 * @author Mrugendra Madalgi
 */
@Data
public final class UiModelScenarioEstInfo
{
	/**
	 * Holds the BillTo flag for the Establishment.
	 */
	private Boolean isBillTo;

	/**
	 * Holds the BuyerPrimary flag for the Establishment.
	 */
	private Boolean isBuyerPrimary;

	/**
	 * Holds the Middleman flag for the Establishment.
	 */
	private Boolean isMiddleman;

	/**
	 * Holds the ShipFrom flag for the Establishment.
	 */
	private Boolean isShipFrom;

	/**
	 * Holds the ShippTo flag for the Establishment.
	 */
	private Boolean isShipTo;

	/**
	 * Holds the Supply flag for the Establishment.
	 */
	private Boolean isSupply;

	/**
	 * Holds the Seller Primary flag for the Establishment.
	 */
	private Boolean isSellerPrimary;

	/**
	 * Holds the Order Origin flag for the Establishment.
	 */
	private Boolean isOrderOrigin;

	/**
	 * Holds the Order Acceptance flag for the Establishment.
	 */
	private Boolean isOrderAcceptance;
}
