// Alex - Use this to create forms later eventually. 
package controller;

import database.DatabaseProvider;
import database.DerbyDatabase;
import database.IDatabase;

import java.io.DataInput;

public class DataFormController
{
	private IDatabase database = null;

	public DataFormController() {
		DatabaseProvider.setInstance(new DerbyDatabase());
		database = DatabaseProvider.getInstance();		
	}
	/*
	public boolean createDataInput(DataInput dataInput) {
		return this.database.insertNewDataInputofGardenIntoDatabase(dataInput);
	}
	*/
}
