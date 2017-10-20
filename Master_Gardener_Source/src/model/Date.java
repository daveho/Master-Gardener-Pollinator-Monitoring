package model;

public class Date
{
	private int day = -1, month = -1, year = -1;
	
	public Date(int day, int month, int year)
	{
		this.day = day;
		this.month = month;
		this.year = year;
	}
	
	/**
	 * 
	 * @param day - The day of month as a number (01-31)
	 * @param month - The month of the year as a number (01-12)
	 * @param year - The year in which the data was taken (example year = 2017)
	 * @return - Returns the date as a string. Example: 02/06/2017 = February 6th, 2017
	 */
	public String stringifyDate()
	{
		String date = "";
		
		date += Integer.toString(this.month);
		date += "/";
		date += Integer.toString(this.day);
		date += "/";
		date += Integer.toString(this.year);
		
		return date;
	}
	
	public void setDay(int day)
	{
		this.day = day;
	}
	
	public int getDay()
	{
		return this.day;
	}
	
	public void setMonth(int month)
	{
		this.month = month;
	}
	
	public int getMonth()
	{
		return this.month;
	}
	
	public void setYear(int year)
	{
		this.year = year;
	}
	
	public int getYear()
	{
		return this.year;
	}
}