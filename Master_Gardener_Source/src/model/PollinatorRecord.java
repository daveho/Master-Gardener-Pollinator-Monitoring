package model;

public class PollinatorRecord
{
	private int week_number = -1;
	
	public PollinatorRecord(int week_number)
	{
		this.week_number = week_number;
	}
	
	public int getWeekNumber()
	{
		return this.week_number;
	}
	public void setWeekNumber(int week_number)
	{
		this.week_number = week_number;
	}
}