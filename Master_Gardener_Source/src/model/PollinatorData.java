// Alex- This class replaces Calendar and Weather classes. Maybe. I don't know if we'll use this but it's here.
package model;

import java.util.ArrayList;

public class PollinatorData
{
	
	// probably have a garden id to tie each table of pollinator data to a garden
	private int WeekID;
	private Date date;
	private int time_start = -1, time_stop = -1, temperature = -1;
	private String cloud = "", wind = "";
	
	/**
	 * 
	 * @param time_start - (24 hour clock) Time when data recording began
	 * @param time_stop - (24 hour clock) Time when data recording ended
	 * @param temperature - Temperature during data recording
	 * @param cloud - 
	 * @param wind
	 * @param day - Day (Integer, 1-31) of the month.
	 * @param month - Month (Integer, 1-12) of the year.
	 * @param year - Year (Integer, eg. 2017)
	 */

	public PollinatorData(int time_start, int time_stop, int temperature, String cloud, String wind, int day,
			int month, int year)
	{
		this.date = new Date(day, month, year);
		this.time_start = time_start;
		this.time_stop = time_stop;
		this.temperature = temperature;
		this.cloud = cloud;
		this.wind = wind;
	}
	
	public PollinatorData() {
//		 TODO Auto-generated constructor stub
//		this.description = "";
//		this.name = "";
//		this.rating = 0;
//		this.members = new ArrayList<Account>();
//		this.moderators = new ArrayList<Account>();
//		this.posts = new ArrayList<Post>();
		
		//this.date = null;
		this.time_start = -1;
		this.time_stop = -1;
		
	}

	public String getDate()
	{
		return this.date.stringifyDate();
	}
	
	public void setDate(int day, int month, int year)
	{
		this.date = new Date(day, month, year);
	}
	
	public int getTimeStart()
	{
		return this.time_start;
	}
	public void setTimeStart(int time_start)
	{
		this.time_start = time_start;
	}
	public int getTimeStop()
	{
		return this.time_stop;
	}
	public void setTimeStop(int time_stop)
	{
		this.time_stop = time_stop;
	}
	public int getTemperature()
	{
		return this.temperature;
	}
	public void setTemperature(int temperature)
	{
		this.temperature = temperature;
	}
	public String getCloudStatus()
	{
		return this.cloud;
	}
	public void setCloudStatus(String cloud)
	{
		this.cloud = cloud;
	}
	public String getWindStatus()
	{
		return this.wind;
	}
	public void setWindStatus(String wind)
	{
		this.wind = wind;
	}
	
	public void getWeekID(int WeekID)
	{
		this.WeekID = WeekID;
	}
	
	public int setWeekID(int WeekID)
	{
		return this.WeekID;
	}
	
}