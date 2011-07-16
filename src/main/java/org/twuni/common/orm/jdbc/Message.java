package org.twuni.common.orm.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.twuni.common.Adapter;
import org.twuni.common.orm.Record;
import org.twuni.common.orm.jdbc.exception.NonUniqueObjectException;
import org.twuni.common.orm.jdbc.exception.ObjectNotFoundException;

public class Message<T> implements org.twuni.common.orm.Message<T> {

	protected final PreparedStatement statement;
	private final Adapter<Record, T> adapter;

	public Message( PreparedStatement statement, Adapter<Record, T> adapter ) {
		this.statement = statement;
		this.adapter = adapter;
	}

	@Override
	public void setParameter( int index, String value ) {
		try {
			statement.setString( index, value );
		} catch( SQLException exception ) {
			throw new RuntimeException( exception );
		}
	}

	@Override
	public void setParameter( int index, int value ) {
		try {
			statement.setInt( index, value );
		} catch( SQLException exception ) {
			throw new RuntimeException( exception );
		}
	}

	@Override
	public void setParameter( int index, double value ) {
		try {
			statement.setDouble( index, value );
		} catch( SQLException exception ) {
			throw new RuntimeException( exception );
		}
	}

	@Override
	public void setParameter( int index, float value ) {
		try {
			statement.setFloat( index, value );
		} catch( SQLException exception ) {
			throw new RuntimeException( exception );
		}
	}

	@Override
	public void setParameter( int index, boolean value ) {
		try {
			statement.setBoolean( index, value );
		} catch( SQLException exception ) {
			throw new RuntimeException( exception );
		}
	}

	@Override
	public void setParameter( int index, long value ) {
		try {
			statement.setLong( index, value );
		} catch( SQLException exception ) {
			throw new RuntimeException( exception );
		}
	}

	@Override
	public void setParameter( int index, short value ) {
		try {
			statement.setShort( index, value );
		} catch( SQLException exception ) {
			throw new RuntimeException( exception );
		}
	}

	@Override
	public final void execute() {
		try {
			statement.execute();
		} catch( SQLException exception ) {
			throw new RuntimeException( exception );
		}
	}

	@Override
	public final List<T> list( int limit ) {

		try {
			statement.setMaxRows( limit );
		} catch( SQLException exception ) {
			throw new RuntimeException( exception );
		}

		return list();

	}

	@Override
	public final List<T> list() {

		try {

			execute();

			ResultSet results = statement.getResultSet();
			List<T> list = new ArrayList<T>();
			Record record = new ResultSetRecord( results );

			while( results.next() ) {
				list.add( adapter.adapt( record ) );
			}

			return list;

		} catch( SQLException exception ) {
			throw new RuntimeException( exception );
		}

	}

	@Override
	public final T unique() {

		List<T> results = list( 2 );

		switch( results.size() ) {
			case 0:
				throw new ObjectNotFoundException();
			case 1:
				return results.get( 0 );
			default:
				throw new NonUniqueObjectException();
		}

	}

}
