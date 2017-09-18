package model;

public class Calendar
{
	
	// Alex - TimeStart and TimeStop are set on a weekly basis.
	// The ID is based on the year when the data was taken. The week
	// number is, as you can see in our data, the week number.
	private int calendarID, timeStart, timeStop, weekNumber;
	
	// Alex - Consider developing a "Date" class to represent dates as listed in the GOODDATA xlsx files.
	//private Date date;
	
	// Alex - County ID is potentially not required here. Probably.
	//private int countyID;
	
	public Calendar()
	{
		
	}
	
	/**
	 * 
	 * @param calendarID For internal use only
	 */
	
	public void setCalendarID(int calendarID)
	{
		this.calendarID = calendarID;
	}
	
	/**
	 * 
	 * @param timeStart What time did you start collecting data? (Military time, no colon eg. 1815 = 6:15 pm)
	 */
	public void setTimeStart(int timeStart)
	{
		this.timeStart = timeStart;
	}
	
	/**
	 * 
	 * @param timeStop What time did you stop collecting data? (Military time, no colon eg. 1815 = 6:15 pm)
	 */
	public void setTimeStop(int timeStop)
	{
		this.timeStop = timeStop;
	}
	
	/**
	 * 
	 * @param weekNumber Which week were you collecting data? Is this your first week (weekNumber = 1) or your 12th
	 * week (weekNumber = 12)
	 */
	public void setWeekNumber(int weekNumber)
	{
		this.weekNumber = weekNumber;
	}
	
	/**
	 * 
	 * @return For internal use only
	 */
	
	public int getCalendarID()
	{
		return this.calendarID;
	}
	
	/**
	 * 
	 * @return Get what time data collection began on a specific day (in military time, eg. 1815 = 6:15 pm)
	 */
	public int getTimeStart()
	{
		return this.timeStart;
	}
	
	/**
	 * 
	 * @return Get what time data collection ended on a specific day (in military time, eg. 1815 = 6:15 pm)
	 */
	public int getTimeStop()
	{
		return this.timeStop;
	}
	/**
	 * 
	 * @return Get the week number of a data set (Which week did X happen on?)
	 */
	public int getWeekNumber()
	{
		return this.weekNumber;
	}
}
