package com.thomsonreuters.extractvalidator.util;


import org.apache.commons.lang.exception.ExceptionUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.thomsonreuters.extractvalidator.determination.dbconnection.DatabaseCommunicationException;


/**
 * Unit of work to execute in a transaction.
 *
 * @param <S> The type to pass in to the transaction.
 * @param <T> The type to return from the transaction.
 *
 * @author MattM
 */
public abstract class UnitOfWork<T, S>
{
	/**
	 * Interface for a unit of work to execute in a transaction.
	 *
	 * @param sessionFactory Wrapper for Hibernate session factory.
	 * @param input Input object for service.
	 *
	 * @return Results of the query.
	 *
	 * @throws DatabaseCommunicationException On error from application layer.
	 */
	public T doUnitOfWork(final SessionFactory sessionFactory, final S input) throws DatabaseCommunicationException
	{
		final T t;

		try
		{
			sessionFactory.getCurrentSession().beginTransaction();
			t = doWork(sessionFactory.getCurrentSession(), input);
			sessionFactory.getCurrentSession().getTransaction().commit();
		}
		catch (final Exception e)
		{
			sessionFactory.getCurrentSession().getTransaction().rollback();

			throw new DatabaseCommunicationException("Unable to complete transaction: " + ExceptionUtils.getRootCauseMessage(e), e);
		}

		return t;
	}


	/**
	 * Executes in the context of a transaction.
	 *
	 * @param currentSession The current Hibernate session.
	 * @param input Return object for service.
	 *
	 * @return Results of the query.
	 *
	 * @throws DatabaseCommunicationException On error performing work.
	 */
	protected abstract T doWork(final Session currentSession, final S input) throws DatabaseCommunicationException;
}
