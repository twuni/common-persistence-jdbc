package org.twuni.common.orm;

import java.util.List;

import org.twuni.common.Adapter;

public interface Session {

	public <T> List<T> query( String sql, Parameters parameters, Adapter<Record, T> adapter, int limit );

	public <T> List<T> query( String sql, Parameters parameters, Adapter<Record, T> adapter );

	public <T> List<T> query( String sql, Adapter<Record, T> adapter, int limit );

	public <T> List<T> query( String sql, Adapter<Record, T> adapter );

	public void query( String sql, Parameters parameters );

	public void query( String sql );

	public <T> T unique( String sql, Parameters parameters, Adapter<Record, T> adapter );

	public <T> T unique( String sql, Adapter<Record, T> adapter );

}
