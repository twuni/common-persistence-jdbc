package org.twuni.common.orm;

public interface Record {

	public String getString( String key );

	public byte getByte( String key );

	public int getInt( String key );

	public short getShort( String key );

	public long getLong( String key );

	public double getDouble( String key );

	public float getFloat( String key );

	public byte [] getBytes( String key );

}
