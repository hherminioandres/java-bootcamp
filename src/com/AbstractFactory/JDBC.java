package com.AbstractFactory;

public class JDBC extends SQLFactory {

	public AbstractSQLDB getDB(String db) {
		if(Constant.MYSQL.equalsIgnoreCase(db)) {
			return new MySQL();
		}
		if(Constant.ORACLESQL.equalsIgnoreCase(db)) {
			return new OracleSQL();
		}
		if(Constant.POSTGRESQL.equalsIgnoreCase(db)) {
			return new PostgreSQL();
		}
		return null;
	}
	
}
