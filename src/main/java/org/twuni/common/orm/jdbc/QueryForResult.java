package org.twuni.common.orm.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.twuni.common.Adapter;
import org.twuni.common.orm.Locates;
import org.twuni.common.orm.Record;
import org.twuni.common.orm.jdbc.exception.NonUniqueObjectException;
import org.twuni.common.orm.jdbc.exception.ObjectNotFoundException;

public class QueryForResult<T> extends Query implements Locates<T> {

	private final Adapter<Record, T> adapter;

	public QueryForResult( PreparedStatement statement, Adapter<Record, T> adapter ) {
		super( statement );
		this.adapter = adapter;
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

			run();

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
