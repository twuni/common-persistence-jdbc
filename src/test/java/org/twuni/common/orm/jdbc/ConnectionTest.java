package org.twuni.common.orm.jdbc;

import org.junit.Test;
import org.twuni.common.orm.Parameterized;
import org.twuni.common.orm.Parameters;
import org.twuni.common.orm.jdbc.Transaction.Behavior;

public class ConnectionTest {

	@Test
	public void testConnection() {

		Connection connection = new Connection( "jdbc:hsqldb:mem:test", "SA", "" );

		connection.run( new Behavior() {

			@Override
			public void perform( Session session ) {

				session.query( "CREATE TABLE person ( first_name varchar(128), last_name varchar(128) );" );
				session.query( "INSERT INTO person ( first_name, last_name ) VALUES ( ?, ? );", new Parameters() {

					@Override
					public void apply( Parameterized target ) {
						target.setParameter( 1, "John" );
						target.setParameter( 2, "Smith" );
					}

				} );

			}

		} );

	}

}
