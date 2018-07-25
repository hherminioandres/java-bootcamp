package com.AbstractFactory;

public class AbstractFactory {
	
	static final String JDBC = "JDBC";
	static final String OLEBD = "OLEDB"; 
	static final String ORACLESQL = "OracleSQL";
	static final String POSTGRESQL = "PostgreSQL";
	static final String	MYSQL = "MySQL";
	static final String SQLSERVER = "SQLServer";
	
	public SQLFactory getConnection(String acc) {
		if(JDBC.equalsIgnoreCase(acc)) {
			return new JDBC();
		}
		if(OLEBD.equalsIgnoreCase(acc)) {
			return new OLEDB();
		}
		return null;
	}

	public static void main(String[] args) {
		/**
		 * JDBC -> OracleSQL, PostgreSQL, MySQL
		 * OLEDB -> SQLServer
		 **/
		AbstractFactory af = new AbstractFactory();
		SQLFactory sqlf = af.getConnection(JDBC);
		AbstractSQLDB sqldb = sqlf.getDB(ORACLESQL);
		sqldb.connect();
		
		sqlf = af.getConnection(OLEBD);
		sqldb = sqlf.getDB(SQLSERVER);
		sqldb.connect();
		
		sqlf = af.getConnection(JDBC);
		sqldb = sqlf.getDB(POSTGRESQL);
		sqldb.connect();
		
		sqlf = af.getConnection(JDBC);
		sqldb = sqlf.getDB(MYSQL);
		sqldb.connect();
		
	}
}
