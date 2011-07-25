package org.twuni.common.persistence.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.twuni.common.persistence.Record;

class ResultSetRecord implements Record {

	private final ResultSet results;

	public ResultSetRecord( ResultSet results ) {
		this.results = results;
	}

	@Override
	public String getString( String key ) {
		try {
			return results.getString( key );
		} catch( SQLException exception ) {
			throw new RuntimeException( exception );
		}
	}

	@Override
	public byte getByte( String key ) {
		try {
			return results.getByte( key );
		} catch( SQLException exception ) {
			throw new RuntimeException( exception );
		}
	}

	@Override
	public int getInt( String key ) {
		try {
			return results.getInt( key );
		} catch( SQLException exception ) {
			throw new RuntimeException( exception );
		}
	}

	@Override
	public short getShort( String key ) {
		try {
			return results.getShort( key );
		} catch( SQLException exception ) {
			throw new RuntimeException( exception );
		}
	}

	@Override
	public long getLong( String key ) {
		try {
			return results.getLong( key );
		} catch( SQLException exception ) {
			throw new RuntimeException( exception );
		}
	}

	@Override
	public double getDouble( String key ) {
		try {
			return results.getDouble( key );
		} catch( SQLException exception ) {
			throw new RuntimeException( exception );
		}
	}

	@Override
	public float getFloat( String key ) {
		try {
			return results.getFloat( key );
		} catch( SQLException exception ) {
			throw new RuntimeException( exception );
		}
	}

	@Override
	public byte [] getBytes( String key ) {
		try {
			return results.getBytes( key );
		} catch( SQLException exception ) {
			throw new RuntimeException( exception );
		}
	}

}
