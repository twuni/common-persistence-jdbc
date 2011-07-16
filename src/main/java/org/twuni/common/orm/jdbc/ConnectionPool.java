package org.twuni.common.orm.jdbc;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.twuni.common.orm.jdbc.Transaction.Behavior;
import org.twuni.common.orm.jdbc.exception.ConnectionLimitExceededException;

public class ConnectionPool {

	private final List<java.sql.Connection> connections;
	private final int connectionLimit;
	private final String url;
	private final String username;
	private final String password;

	private int numberOfConnections;

	public ConnectionPool( int size, String url, String username, String password ) {
		this.connections = new ArrayList<java.sql.Connection>( size );
		this.numberOfConnections = 0;
		this.connectionLimit = size;
		this.url = url;
		this.username = username;
		this.password = password;
	}

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
