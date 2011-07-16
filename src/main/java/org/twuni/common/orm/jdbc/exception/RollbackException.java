package org.twuni.common.orm.jdbc.exception;

public class RollbackException extends RuntimeException {

	public RollbackException( Throwable throwable ) {
		super( throwable );
	}

}
