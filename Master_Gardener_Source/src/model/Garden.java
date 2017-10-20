package model;

public class Garden
{
	private String address = "";
	private int GardenID;
	private String GardenContact = ""; // Person who is head of the Garden to contact for questions or information.
	
	public Garden() {
		this.address = "";
		
		
	}
	
	
	
	public Garden(String address)
	{
		this.address = address;
	}
	
	public String getGardenAddress()
	{
		return this.address;
	}
	
	public void setGardenAddress(String address)
	{
		this.address = address;
	}



	public int getGardenID() {
		return this.GardenID;
	}



	public void setGardenID(int gardenID) {
		this.GardenID = gardenID;
	}



	public String getGardenContact() {
		return this.GardenContact;
	}



	public void setGardenContact(String gardenContact) {
		this.GardenContact = gardenContact;
	}
	
}