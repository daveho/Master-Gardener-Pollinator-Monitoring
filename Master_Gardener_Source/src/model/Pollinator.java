// Alex - When starting the work on this I had issues with the system using an old JRE system library
// I fixed it using this link: https://stackoverflow.com/questions/17240708/implicit-super-constructor-object-is-undefined-must-explicitly-invoke-another
// But that might also mean that someone else's build path might be messed up. If that's the case we need to square
// away issues ASAP.

package model;

public class Pollinator
{
	private int pollinatorID;
	
	// Alex - Pollinator name/type might be identical in terms of what we're trying to store in the DB
	private String pollinatorName;
	private String pollinatorType;
	
	// This might not be necessary, therefore it's documented here in the event that it is.
	// It doesn't seem like it will be though. Don't take my word on that. You don't know who wrote 
	// this comment. Go away.
	//private int weekNumber;
	
	public Pollinator()
	{
		
	}
	
	public void setPollinatorID(int pollinatorid)
	{
		this.pollinatorID = pollinatorid;
	}
	public void setPollinatorName(String pollinatorname)
	{
		this.pollinatorName = pollinatorname;
	}
	public void setPollinatorType(String pollinatortype)
	{
		this.pollinatorType = pollinatortype;
	}
	
	public int getPollinatorID()
	{
		return this.pollinatorID;
	}
	public String getPollinatorName()
	{
		return this.pollinatorName;
	}
	public String geetPollinatorType()
	{
		return this.pollinatorType;
	}
	
}