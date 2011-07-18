package org.twuni.common.orm;

public interface Parameterized {

	public void setParameter( int index, String value );

	public void setParameter( int index, int value );

	public void setParameter( int index, double value );

	public void setParameter( int index, float value );

	public void setParameter( int index, boolean value );

	public void setParameter( int index, long value );

	public void setParameter( int index, short value );

}
