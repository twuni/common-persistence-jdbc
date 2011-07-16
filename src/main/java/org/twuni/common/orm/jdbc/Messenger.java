package org.twuni.common.orm.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.twuni.common.Adapter;
import org.twuni.common.orm.Record;

public class Messenger {

	private final java.sql.Connection connection;

	public Messenger( java.sql.Connection connection ) {
		this.connection = connection;
	}

	public <T> Message<T> createMessage( String sql ) {
		return createMessage( sql, null );
	}

	public <T> Message<T> createMessage( String sql, Adapter<Record, T> adapter ) {
		try {
			PreparedStatement statement = connection.prepareStatement( sql );
			return new Message<T>( statement, adapter );
		} catch( SQLException exception ) {
			throw new RuntimeException( exception );
		}
	}

	public void execute( String sql ) {
		createMessage( sql ).execute();
	}

	public <T> List<T> list( String sql, Adapter<Record, T> adapter ) {
		return createMessage( sql, adapter ).list();
	}

}
