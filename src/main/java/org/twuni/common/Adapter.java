package org.twuni.common;

public interface Adapter<From, To> {

	public To adapt( From from );

}
