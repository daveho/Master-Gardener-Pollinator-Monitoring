package model;

public class Plant
{
	private int plantID, plantHeight, plantSize, percentCoverage;
	private String plantName;
	private char bloomsOpen, vigor;
	
	// Unsure if this ( v ) should be here
	// private int weekNumber;
	
	public Plant()
	{
		
	}
	
	public void setPlantID(int plantID)
	{
		this.plantID = plantID;
	}
	
	public void setPlantHeight(int plantHeight)
	{
		this.plantHeight = plantHeight;
	}
	
	public void setPlantSize(int plantSize)
	{
		this.plantSize = plantSize;
	}
	
	/**
	 * 
	 * @param percentageCovered The percent covered, as indicated as an INTEGER on the data sheet.
	 */
	public void setPercentCoverage (int percentageCovered)
	{
		this.percentCoverage = percentageCovered;
	}
	
	public void setPlantName(String plantName)
	{
		this.plantName = plantName;
	}
	
	/**
	 * 
	 * @param bloomsOpen This is typically a 1 character entry found on the data sheets. Contact system admin (Alex)
	 * in the event of any issues.
	 */
	public void setBloomsOpen (char bloomsOpen)
	{
		this.bloomsOpen = bloomsOpen;
	}
	
	/**
	 * 
	 * @param vigor This is typically a 1 character entry found on the data sheets. Contact system admin (Alex)
	 * in the event of any issues.
	 */
	public void setVigor (char vigor)
	{
		this.vigor = vigor;
	}
	
	public int getPlantID()
	{
		return this.plantID;
	}
	
	public int getPlantHeight()
	{
		return this.plantHeight;
	}
	
	public int getPlantSize()
	{
		return this.plantSize;
	}
	
	public int getPercentCoverage()
	{
		return this.percentCoverage;
	}
	
	public String getPlantName()
	{
		return this.plantName;
	}
	
	public char getBloomsOpen()
	{
		return this.bloomsOpen;
	}
	
	public char getVigor()
	{
		return this.vigor;
	}
}
