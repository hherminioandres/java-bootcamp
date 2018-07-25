package com.AbstractFactory;

public class SQLConnectionFactory {
	
	static final String ORACLESQL = "OracleSQL";
	static final String POSTGRESQL = "PostgreSQL";
	static final String	MYSQL = "MySQL";
	
	public AbstractSQLDB getConnection(String motor){
		if(MYSQL.equalsIgnoreCase(motor)) {
			return new MySQL();
		}
		if(ORACLESQL.equalsIgnoreCase(motor)) {
			return new OracleSQL();
		}
		if(POSTGRESQL.equalsIgnoreCase(motor)) {
			return new PostgreSQL();
		}
		return null;
	}
}
