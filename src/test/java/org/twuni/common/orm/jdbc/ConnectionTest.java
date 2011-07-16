package org.twuni.common.orm.jdbc;

import org.junit.Test;
import org.twuni.common.orm.jdbc.Transaction.Behavior;

public class ConnectionTest {

	@Test
	public void testConnection() {

		ConnectionPool pool = new ConnectionPool( 1, "jdbc:hsqldb:file:data/test", "SA", "" );

		pool.run( new Behavior() {

			@Override
			public void perform( Messenger messenger ) {
				Message<?> message = messenger.createMessage( "CREATE TABLE Test ( id int not null, content varchar(255), primary key (id) );" );
				message.execute();
			}

		} );

	}

}
