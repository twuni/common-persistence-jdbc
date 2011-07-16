package org.twuni.common.orm;

import java.util.List;

public interface Message<T> {

	public void setParameter( int index, String value );

	public void setParameter( int index, int value );

	public void setParameter( int index, double value );

	public void setParameter( int index, float value );

	public void setParameter( int index, boolean value );

	public void setParameter( int index, long value );

	public void setParameter( int index, short value );

	public void execute();

	public List<T> list( int limit );

	public List<T> list();

	public T unique();
}
