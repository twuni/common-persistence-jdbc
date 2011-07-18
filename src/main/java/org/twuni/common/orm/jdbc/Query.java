package org.twuni.common.orm.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.twuni.common.orm.Parameterized;

public class Query implements Parameterized, Runnable {

	protected final PreparedStatement statement;

	public Query( PreparedStatement statement ) {
		this.statement = statement;
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
	public final void run() {
		try {
			statement.execute();
		} catch( SQLException exception ) {
			throw new RuntimeException( exception );
		}
	}

}
