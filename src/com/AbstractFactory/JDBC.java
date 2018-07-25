package com.AbstractFactory;

public class JDBC extends SQLFactory {
	
	static final String ORACLESQL = "OracleSQL";
	static final String POSTGRESQL = "PostgreSQL";
	static final String	MYSQL = "MySQL";

	public AbstractSQLDB getDB(String db) {
		if(MYSQL.equalsIgnoreCase(db)) {
			return new MySQL();
		}
		if(ORACLESQL.equalsIgnoreCase(db)) {
			return new OracleSQL();
		}
		if(POSTGRESQL.equalsIgnoreCase(db)) {
			return new PostgreSQL();
		}
		return null;
	}
	
}
