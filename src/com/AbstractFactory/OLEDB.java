package com.AbstractFactory;

public class OLEDB extends SQLFactory {
	
	static final String SQLSERVER = "SQLServer";
	
	public AbstractSQLDB getDB(String db) {
		if(SQLSERVER.equalsIgnoreCase(db)) {
			return new SQLServer();
		}
		return null;
	}
	
}
