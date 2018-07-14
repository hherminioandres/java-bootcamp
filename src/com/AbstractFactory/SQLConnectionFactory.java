package com.AbstractFactory;

public class SQLConnectionFactory {
	
	public AbstractSQLDB getConnection(String motor){
		if(Constant.MYSQL.equalsIgnoreCase(motor)) {
			return new MySQL();
		}
		if(Constant.ORACLESQL.equalsIgnoreCase(motor)) {
			return new OracleSQL();
		}
		if(Constant.POSTGRESQL.equalsIgnoreCase(motor)) {
			return new PostgreSQL();
		}
		return null;
	}
}
