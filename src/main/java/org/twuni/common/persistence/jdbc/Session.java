package org.twuni.common.persistence.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.twuni.common.Adapter;
import org.twuni.common.persistence.Parameters;
import org.twuni.common.persistence.Record;

class Session implements org.twuni.common.persistence.Session {

	private final java.sql.Connection connection;

	public Session( java.sql.Connection connection ) {
		this.connection = connection;
	}

	@Override
	public void query( String sql, Parameters parameters ) {
		Query query = createQuery( sql );
		parameters.apply( query );
		query.run();
	}

	@Override
	public void query( String sql ) {
		createQuery( sql ).run();
	}

	@Override
	public <T> List<T> query( String sql, Parameters parameters, Adapter<Record, T> adapter ) {
		QueryForResult<T> query = createQuery( sql, adapter );
		parameters.apply( query );
		return query.list();
	}

	@Override
	public <T> List<T> query( String sql, Adapter<Record, T> adapter ) {
		return createQuery( sql, adapter ).list();
	}

	private Query createQuery( String sql ) {
		try {
			PreparedStatement statement = connection.prepareStatement( sql );
			return new Query( statement );
		} catch( SQLException exception ) {
			throw new RuntimeException( exception );
		}
	}

	private <T> QueryForResult<T> createQuery( String sql, Adapter<Record, T> adapter ) {
		try {
			PreparedStatement statement = connection.prepareStatement( sql );
			return new QueryForResult<T>( statement, adapter );
		} catch( SQLException exception ) {
			throw new RuntimeException( exception );
		}
	}

	@Override
	public <T> List<T> query( String sql, Parameters parameters, Adapter<Record, T> adapter, int limit ) {
		QueryForResult<T> query = createQuery( sql, adapter );
		parameters.apply( query );
		return query.list( limit );
	}

	@Override
	public <T> List<T> query( String sql, Adapter<Record, T> adapter, int limit ) {
		return createQuery( sql, adapter ).list( limit );
	}

	@Override
	public <T> T unique( String sql, Parameters parameters, Adapter<Record, T> adapter ) {
		QueryForResult<T> query = createQuery( sql, adapter );
		parameters.apply( query );
		return query.unique();
	}

	@Override
	public <T> T unique( String sql, Adapter<Record, T> adapter ) {
		return createQuery( sql, adapter ).unique();
	}

}
