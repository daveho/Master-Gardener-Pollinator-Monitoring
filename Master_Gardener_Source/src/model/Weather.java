// Alex -  This class may seem strange but refer to the DB schema provided in order to make some sense of it.

package model;

public class Weather
{
	// Alex - These variable names are very unintuitive, but based on the data given to us this is what I came up
	// with. Other suggestions are always welcome.
	
	private int weatherID, temperature;
	private String wind, cloud;
	
	// Alex - Once again, might need these, might not. This is for DB table organization, but the DB might handle 
	// stuff like this.
	//private int countyID, weekNumber;
	
	
	public Weather()
	{
		
	}
	
	public void setWeatherID(int weatherID)
	{
		this.weatherID = weatherID;
	}
	
	/**
	 * 
	 * @param temperature Temperature in FAHRENHEIT. Celsius to Fahrenheit equation: ((YourTemp C) * (9/5)) + 32 = F
	 */
	public void setTemperature(int temperature)
	{
		this.temperature = temperature;
	}
	
	/**
	 * 
	 * @param wind How was the wind that day? W? LB? Yeah I don't know either.
	 */
	public void setWindStatus(String wind)
	{
		this.wind = wind;
	}
	
	/**
	 * 
	 * @param cloud How were the clouds that day? O? PC? MC? Seriously, who knows?
	 */
	public void setCloudStatus(String cloud)
	{
		this.cloud = cloud;
	}
	
	public int getWeatherID()
	{
		return this.weatherID;
	}
	
	public int getTemperature()
	{
		return this.temperature;
	}
	
	public String getWindStatus()
	{
		return this.wind;
	}
	
	public String getCloudStatus()
	{
		return this.cloud;
	}
}
