package com.AbstractFactory;

public class OLEDB extends SQLFactory {
	
	static final String SQLSERVER = "SQLSEVER";

	public AbstractSQLDB getDB(String db) {
		if(Constant.SQLSERVER.equalsIgnoreCase(db)) {
			return new SQLServer();
		}
		return null;
	}
	
}
