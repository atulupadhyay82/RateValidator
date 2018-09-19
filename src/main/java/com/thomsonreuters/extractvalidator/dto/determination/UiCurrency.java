package com.thomsonreuters.extractvalidator.dto.determination;


/**
 * This POJO is the a data transfer object representing a Currency.
 *
 * @author Srinivas Saitala
 */
public final class UiCurrency
{
	/**
	 * Holds the code for the currency.
	 */
	private String currencyCode;

	/**
	 * Holds the ID for the currency.
	 */
	private Long currencyId;

	/**
	 * Holds the name for the currency.
	 */
	private String currencyName;


	/**
	 * Retrieves the code of the currency.
	 *
	 * @return The code of the currency.
	 */
	public String getCurrencyCode()
	{
		return currencyCode;
	}


	/**
	 * Sets the code of the currency.
	 *
	 * @param currencyCode The currency code.
	 */
	public void setCurrencyCode(final String currencyCode)
	{
		this.currencyCode = currencyCode;
	}


	/**
	 * Retrieves the ID of the currency.
	 *
	 * @return The ID of the currency.
	 */
	public Long getCurrencyId()
	{
		return currencyId;
	}


	/**
	 * Sets the ID of the currency.
	 *
	 * @param currencyId The currency ID.
	 */
	public void setCurrencyId(final Long currencyId)
	{
		this.currencyId = currencyId;
	}


	/**
	 * Retrieves the name of the currency.
	 *
	 * @return The name of the currency.
	 */
	public String getCurrencyName()
	{
		return currencyName;
	}


	/**
	 * Sets the name of the currency.
	 *
	 * @param currencyName The currency name.
	 */
	public void setCurrencyName(final String currencyName)
	{
		this.currencyName = currencyName;
	}
}
