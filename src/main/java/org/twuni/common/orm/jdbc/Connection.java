package org.twuni.common.orm.jdbc;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.twuni.common.orm.Behavior;
import org.twuni.common.orm.exception.ConnectionLimitExceededException;

public class Connection implements org.twuni.common.orm.Connection {

	private static final int DEFAULT_POOL_SIZE = 10;

	private final List<java.sql.Connection> connections;
	private final int connectionLimit;
	private final String url;
	private final String username;
	private final String password;

	private int numberOfConnections;

	/**
	 * Creates a new database connection pool that will contain a maximum of
	 * {@link #DEFAULT_POOL_SIZE} connections.
	 * 
	 * @param url
	 *            The JDBC URL for the database connection.
	 */
	public Connection( String url, String username, String password ) {
		this( url, username, password, DEFAULT_POOL_SIZE );
	}

	public Connection( String url, String username, String password, int size ) {
		this.connections = new ArrayList<java.sql.Connection>( size );
		this.numberOfConnections = 0;
		this.connectionLimit = size;
		this.url = url;
		this.username = username;
		this.password = password;
	}

	/**
	 * Runs the given behavior, rolling back any database changes if an uncaught exception occurs.
	 */
	@Override
	public void run( Behavior behavior ) {

		if( connections.isEmpty() ) {
			if( numberOfConnections >= connectionLimit ) {
				throw new ConnectionLimitExceededException();
			}
			connections.add( createConnection() );
		}

		java.sql.Connection connection = connections.remove( 0 );
		try {
			new Transaction( connection, behavior ).run();
		} finally {
			connections.add( connection );
		}

	}

	private java.sql.Connection createConnection() {

		try {

			java.sql.Connection connection = DriverManager.getConnection( url, username, password );
			connection.setAutoCommit( false );
			numberOfConnections++;
			return connection;

		} catch( SQLException exception ) {
			throw new RuntimeException( exception );
		}

	}

}
