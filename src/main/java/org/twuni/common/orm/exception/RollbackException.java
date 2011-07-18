package org.twuni.common.orm.exception;

public class RollbackException extends RuntimeException {

	public RollbackException( Throwable throwable ) {
		super( throwable );
	}

}
