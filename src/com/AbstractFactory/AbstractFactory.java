package com.AbstractFactory;

public class AbstractFactory {
	
	public SQLFactory getConnection(String acc) {
		if(Constant.JDBC.equalsIgnoreCase(acc)) {
			return new JDBC();
		}
		if(Constant.OLEBD.equalsIgnoreCase(acc)) {
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
		SQLFactory sqlf = af.getConnection(Constant.JDBC);
		AbstractSQLDB sqldb = sqlf.getDB(Constant.ORACLESQL);
		sqldb.connect();
		
		sqlf = af.getConnection(Constant.OLEBD);
		sqldb = sqlf.getDB(Constant.SQLSERVER);
		sqldb.connect();
	}
}
