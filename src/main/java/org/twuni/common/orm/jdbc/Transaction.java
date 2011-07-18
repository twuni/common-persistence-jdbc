package org.twuni.common.orm.jdbc;

import java.sql.SQLException;

import org.twuni.common.orm.jdbc.exception.RollbackException;

public class Transaction implements Runnable {

	public static interface Behavior {

		public void perform( Session session );

	}

	private final java.sql.Connection connection;
	private final Behavior behavior;

	public Transaction( java.sql.Connection connection, Behavior behavior ) {
		this.connection = connection;
		this.behavior = behavior;
	}

	@Override
	public void run() {
		try {
			Session session = new Session( connection );
			behavior.perform( session );
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
		throw new RuntimeException( throwable );
	}

}
