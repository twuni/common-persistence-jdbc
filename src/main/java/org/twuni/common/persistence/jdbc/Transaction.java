package org.twuni.common.persistence.jdbc;

import java.sql.SQLException;

import org.twuni.common.persistence.exception.RollbackException;

class Transaction implements Runnable {

	private final java.sql.Connection connection;
	private final org.twuni.common.persistence.Transaction transaction;

	public Transaction( java.sql.Connection connection, org.twuni.common.persistence.Transaction transaction ) {
		this.connection = connection;
		this.transaction = transaction;
	}

	@Override
	public void run() {
		try {
			Session session = new Session( connection );
			transaction.perform( session );
			connection.commit();
		} catch( Exception exception ) {
			tryRollback( exception );
		}
	}

	private void tryRollback( Throwable throwable ) {
		try {
			connection.rollback();
		} catch( SQLException exception ) {
			throw new RollbackException( exception );
		}

		if( throwable instanceof RuntimeException ) {
			throw (RuntimeException) throwable;
		}

		throw new RuntimeException( throwable );

	}

}
