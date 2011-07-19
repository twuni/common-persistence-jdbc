This library supports object-relational mapping via JDBC.

Sample Code
-----------

The following code creates a new database connection pool of the default size for an in-memory
HSQL database. It then runs an SQL query that creates the `person` table.

	Connection connection = new Connection( "jdbc:hsqldb:mem:test", "SA", "" );
	
	connection.run( new Transaction() {
	
		@Override
		public void perform( Session session ) {
			session.query( "CREATE TABLE person ( first_name varchar(128), last_name varchar(128) );" );
		}
	
	} );

