package org.twuni.common.orm;

import java.util.List;

public interface Locates<T> {

	public List<T> list( int limit );

	public List<T> list();

	public T unique();

}
