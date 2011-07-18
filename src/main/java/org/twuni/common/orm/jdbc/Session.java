package org.twuni.common.orm.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.twuni.common.Adapter;
import org.twuni.common.orm.Parameters;
import org.twuni.common.orm.Record;

public class Session {

	private final java.sql.Connection connection;

	public Session( java.sql.Connection connection ) {
		this.connection = connection;
	}

	public void query( String sql, Parameters parameters ) {
		Query query = createQuery( sql );
		parameters.apply( query );
		query.run();
	}

	public void query( String sql ) {
		createQuery( sql ).run();
	}

	public <T> List<T> query( String sql, Parameters parameters, Adapter<Record, T> adapter ) {
		QueryForResult<T> query = createQuery( sql, adapter );
		parameters.apply( query );
		return query.list();
	}

	public <T> List<T> query( String sql, Adapter<Record, T> adapter ) {
		return createQuery( sql, adapter ).list();
	}

	public Query createQuery( String sql ) {
		try {
			PreparedStatement statement = connection.prepareStatement( sql );
			return new Query( statement );
		} catch( SQLException exception ) {
			throw new RuntimeException( exception );
		}
	}

	public <T> QueryForResult<T> createQuery( String sql, Adapter<Record, T> adapter ) {
		try {
			PreparedStatement statement = connection.prepareStatement( sql );
			return new QueryForResult<T>( statement, adapter );
		} catch( SQLException exception ) {
			throw new RuntimeException( exception );
		}
	}

}
