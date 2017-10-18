// Alex - Use this to create forms later eventually. 
package controller;

import database.DatabaseProvider;
import database.DerbyDatabase;
import database.IDatabase;

public class DataFormController
{
	private IDatabase database = null;

	public DataFormController() {

		// creating DB instance here
		DatabaseProvider.setInstance(new DerbyDatabase());
		database = DatabaseProvider.getInstance();		
	}
	
	
	
	/*
	 createNewDataForm(){
	 
	 }
	 
	 //Consider making a form class
	 submitNewDataForm(Form form){
	 
	 }
	 */
}
