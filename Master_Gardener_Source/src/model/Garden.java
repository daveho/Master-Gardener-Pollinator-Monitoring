package model;

public class Garden
{
	private String address = "";
	
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
	
}
