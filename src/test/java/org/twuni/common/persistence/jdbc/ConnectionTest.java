package org.twuni.common.persistence.jdbc;

import org.junit.Test;
import org.twuni.common.persistence.Parameterized;
import org.twuni.common.persistence.Parameters;

public class ConnectionTest {

	@Test
	public void testConnection() {

		Connection connection = new Connection( "jdbc:hsqldb:mem:test", "SA", "" );

		connection.run( new org.twuni.common.persistence.Transaction() {

			@Override
			public void perform( org.twuni.common.persistence.Session session ) {

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
