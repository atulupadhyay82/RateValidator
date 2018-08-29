package com.thomsonreuters.extractvalidator.determination.services;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.owasp.esapi.ESAPI;
import org.owasp.esapi.Logger;
import org.springframework.stereotype.Service;

import com.thomsonreuters.extractvalidator.determination.dbconnection.DatabaseCommunicationException;
import com.thomsonreuters.extractvalidator.determination.entities.Authority;
import com.thomsonreuters.extractvalidator.determination.entities.AuthorityType;
import com.thomsonreuters.extractvalidator.determination.entities.Location;
import com.thomsonreuters.extractvalidator.determination.entities.Merchant;
import com.thomsonreuters.extractvalidator.determination.entities.ProductCrossReferenceGroup;
import com.thomsonreuters.extractvalidator.determination.entities.ProductGroup;
import com.thomsonreuters.extractvalidator.determination.entities.Zone;
import com.thomsonreuters.extractvalidator.dto.TestRun;
import com.thomsonreuters.extractvalidator.util.ExtractValidatorException;
import com.thomsonreuters.extractvalidator.util.UnitOfWork;


/**
 * CompanyService Description.
 *
 * @author Matt Godsey
 */
@Service
public final class DeterminationDataService
{
	private static final String FETCH_MERCHANT_FOR_NAME = "select m from Merchant m where name = :companyName";

	private static final String FETCH_LOCATIONS_FOR_MERCHANT = "select l from Location l where merchantId = :merchantId";

	private static final String FETCH_ESTABLISHED_ZONES_FOR_MERCHANT = "select z from Zone where z.zoneId in (select ez.zoneId from EstablishedZone where "
																	   + "merchantId = :merchantId and establishedType = 'Y')";

	private static final String FETCH_ESTABLISHED_AUTHORITIES_FOR_MERCHANT = "select a from Authority a where a.authorityId in (select ea.authorityId from "
																			 + "EstablishedAuthority ea where ea.merchantId = :merchantId and established='Y'";

	private static final String FETCH_ESTABLISHED_AUTHORITY_TYPES_FOR_MERCHANT = "select at from AuthorityType at where at.authorityTypeId in (select "
																				 + "eat.authorityTypeId from EstablishedAuthorityType eat where eat.merchantId "
																				 + "= :merchantId and established='Y'";

	private static final String FETCH_PRODUCT_GROUP_BY_ID = "select pg from ProductGroup pg where productGroupId = :productGroupId";

	private static final String FETCH_PRODUCT_CROSS_REF_GROUP_BY_ID = "select p from ProductCrossReferenceGroup p where productCrossReferenceGroupId = :productCrossReferenceGroupId";

	/**
	 * Logging handle for this class
	 */
	private static final Logger LOGGER = ESAPI.getLogger(DeterminationDataService.class);

	private static final String MERCHANT_ID = "merchantId";

	private SessionFactory sessionFactory;


	public void configureHibernateSession(final TestRun testRunData)
	{
		LOGGER.info(Logger.EVENT_UNSPECIFIED, String.format("Configuring Hibernate with URL: %s | username: %s | password: %s",
															testRunData.getDatabaseUrl(),
															testRunData.getDatabaseUserName(),
															testRunData.getDatabasePassword()));

		final Configuration configuration = new Configuration();

		configuration.configure();

		configuration.setProperty("hibernate.connection.url", testRunData.getDatabaseUrl());
		configuration.setProperty("hibernate.connection.username", testRunData.getDatabaseUserName());
		configuration.setProperty("hibernate.connection.password", testRunData.getDatabasePassword());

		sessionFactory = configuration.buildSessionFactory();
	}


	public Merchant fetchMerchantForTestRun(final TestRun testRunData) throws ExtractValidatorException
	{
		final UnitOfWork<Merchant, Void> unit = new UnitOfWork<Merchant, Void>()
		{
			@Override
			protected Merchant doWork(final Session currentSession, final Void v) throws DatabaseCommunicationException
			{
				final Query merchantQuery = currentSession.createNamedQuery(FETCH_MERCHANT_FOR_NAME);

				merchantQuery.setParameter("companyName", testRunData.getTestCompanyName());

				return (Merchant) merchantQuery.getSingleResult();
			}
		};

		final Merchant merchant;

		try
		{
			merchant = unit.doUnitOfWork(sessionFactory, null);
		}
		catch (final DatabaseCommunicationException dce)
		{
			LOGGER.error(Logger.EVENT_FAILURE, "Failed retrieving merchant from Determination. Message: " + dce.getMessage());

			throw new ExtractValidatorException(dce.getMessage(), dce, dce.getCode());
		}

		return merchant;
	}


	public List<Location> fetchLocationsForMerchant(final Merchant merchant) throws ExtractValidatorException
	{
		final UnitOfWork<List<Location>, Void> unit = new UnitOfWork<List<Location>, Void>()
		{
			@Override
			protected List<Location> doWork(final Session currentSession, final Void v) throws DatabaseCommunicationException
			{
				final Query locationQuery = currentSession.createNamedQuery(FETCH_LOCATIONS_FOR_MERCHANT);

				locationQuery.setParameter(MERCHANT_ID, merchant.getMerchantId());

				return locationQuery.getResultList();
			}
		};

		final List<Location> locations;

		try
		{
			locations = unit.doUnitOfWork(sessionFactory, null);
		}
		catch (final DatabaseCommunicationException dce)
		{
			LOGGER.error(Logger.EVENT_FAILURE, "Failed retrieving locations from Determination. Message: " + dce.getMessage());

			throw new ExtractValidatorException(dce.getMessage(), dce, dce.getCode());
		}

		return locations;
	}


	public List<Zone> fetchEstablishedZonesForMerchant(final Merchant merchant) throws ExtractValidatorException
	{
		final UnitOfWork<List<Zone>, Void> unit = new UnitOfWork<List<Zone>, Void>()
		{
			@Override
			protected List<Zone> doWork(final Session currentSession, final Void v) throws DatabaseCommunicationException
			{
				final Query establishedZoneQuery = currentSession.createNamedQuery(FETCH_ESTABLISHED_ZONES_FOR_MERCHANT);

				establishedZoneQuery.setParameter(MERCHANT_ID, merchant.getMerchantId());

				return establishedZoneQuery.getResultList();
			}
		};

		final List<Zone> zones;

		try
		{
			zones = unit.doUnitOfWork(sessionFactory, null);
		}
		catch (final DatabaseCommunicationException dce)
		{
			LOGGER.error(Logger.EVENT_FAILURE, "Failed retrieving established zones from Determination. Message: " + dce.getMessage());

			throw new ExtractValidatorException(dce.getMessage(), dce, dce.getCode());
		}

		return zones;
	}


	public List<Authority> fetchEstablishedAuthoritiesForMerchant(final Merchant merchant) throws ExtractValidatorException
	{
		final UnitOfWork<List<Authority>, Void> unit = new UnitOfWork<List<Authority>, Void>()
		{
			@Override
			protected List<Authority> doWork(final Session currentSession, final Void v) throws DatabaseCommunicationException
			{
				final Query establishedAuthoritiesQuery = currentSession.createNamedQuery(FETCH_ESTABLISHED_AUTHORITIES_FOR_MERCHANT);

				establishedAuthoritiesQuery.setParameter(MERCHANT_ID, merchant.getMerchantId());

				return establishedAuthoritiesQuery.getResultList();
			}
		};

		final List<Authority> authorities;

		try
		{
			authorities = unit.doUnitOfWork(sessionFactory, null);
		}
		catch (final DatabaseCommunicationException dce)
		{
			LOGGER.error(Logger.EVENT_FAILURE, "Failed retrieving established authorities from Determination. Message: " + dce.getMessage());

			throw new ExtractValidatorException(dce.getMessage(), dce, dce.getCode());
		}

		return authorities;
	}


	public List<AuthorityType> fetchEstablishedAuthorityTypesForMerchant(final Merchant merchant) throws ExtractValidatorException
	{
		final UnitOfWork<List<AuthorityType>, Void> unit = new UnitOfWork<List<AuthorityType>, Void>()
		{
			@Override
			protected List<AuthorityType> doWork(final Session currentSession, final Void v) throws DatabaseCommunicationException
			{
				final Query establishedAuthorityTypeQuery = currentSession.createNamedQuery(FETCH_ESTABLISHED_AUTHORITY_TYPES_FOR_MERCHANT);

				establishedAuthorityTypeQuery.setParameter(MERCHANT_ID, merchant.getMerchantId());

				return establishedAuthorityTypeQuery.getResultList();
			}
		};

		final List<AuthorityType> authorityTypes;

		try
		{
			authorityTypes = unit.doUnitOfWork(sessionFactory, null);
		}
		catch (final DatabaseCommunicationException dce)
		{
			LOGGER.error(Logger.EVENT_FAILURE, "Failed retrieving established authority types from Determination. Message: " + dce.getMessage());

			throw new ExtractValidatorException(dce.getMessage(), dce, dce.getCode());
		}

		return authorityTypes;
	}


	public ProductGroup fetchUsProductGroupForMerchant(final Merchant merchant) throws ExtractValidatorException
	{
		return fetchProductGroupById(merchant.getUsProductGroupId());
	}


	public ProductGroup fetchIntlProductGroupForMerchant(final Merchant merchant) throws ExtractValidatorException
	{
		return fetchProductGroupById(merchant.getIntlProductGroupId());
	}


	public ProductCrossReferenceGroup fetchProductCrossReferenceGroup(final Merchant merchant) throws ExtractValidatorException
	{
		final UnitOfWork<ProductCrossReferenceGroup, Void> unit = new UnitOfWork<ProductCrossReferenceGroup, Void>()
		{
			@Override
			protected ProductCrossReferenceGroup doWork(final Session currentSession, final Void v) throws DatabaseCommunicationException
			{
				final Query productCrossReferenceGroupQuery = currentSession.createNamedQuery(FETCH_PRODUCT_CROSS_REF_GROUP_BY_ID);

				productCrossReferenceGroupQuery.setParameter("productCrossReferenceGroupId", merchant.getProductCrossReferenceGroupId());

				return (ProductCrossReferenceGroup) productCrossReferenceGroupQuery.getSingleResult();
			}
		};

		final ProductCrossReferenceGroup productCrossReferenceGroup;

		try
		{
			productCrossReferenceGroup = unit.doUnitOfWork(sessionFactory, null);
		}
		catch (final DatabaseCommunicationException dce)
		{
			LOGGER.error(Logger.EVENT_FAILURE, "Failed retrieving product cross reference group from Determination. Message: " + dce.getMessage());

			throw new ExtractValidatorException(dce.getMessage(), dce, dce.getCode());
		}

		return productCrossReferenceGroup;
	}


	private ProductGroup fetchProductGroupById(final Long productGroupId) throws ExtractValidatorException
	{
		final UnitOfWork<ProductGroup, Void> unit = new UnitOfWork<ProductGroup, Void>()
		{
			@Override
			protected ProductGroup doWork(final Session currentSession, final Void v) throws DatabaseCommunicationException
			{
				final Query productGroupQuery = currentSession.createNamedQuery(FETCH_PRODUCT_GROUP_BY_ID);

				productGroupQuery.setParameter("productGroupId", productGroupId);

				return (ProductGroup) productGroupQuery.getSingleResult();
			}
		};

		final ProductGroup productGroup;

		try
		{
			productGroup = unit.doUnitOfWork(sessionFactory, null);
		}
		catch (final DatabaseCommunicationException dce)
		{
			LOGGER.error(Logger.EVENT_FAILURE, "Failed retrieving product group from Determination. Message: " + dce.getMessage());

			throw new ExtractValidatorException(dce.getMessage(), dce, dce.getCode());
		}

		return productGroup;
	}
}
