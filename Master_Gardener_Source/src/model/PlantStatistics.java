package model;

public class PlantStatistics
{
	private int plant_height = -1, plant_size = -1, percent_coverage = -1, plot_size = -1;
	private char blooms_open, vigor;
	
	public PlantStatistics(int plant_height, int plant_size, int percent_coverage, int plot_size, char blooms_open,
			char vigor)
	{
		this.plant_height = plant_height;
		this.plant_size = plant_size;
		this.percent_coverage = percent_coverage;
		this.plot_size = plot_size;
		this.blooms_open = blooms_open;
		this.vigor = vigor;
	}
	
	
	public void setplant_height(int plant_height)
	{
		this.plant_height = plant_height;
	}
	
	public void setPlantSize(int plantSize)
	{
		this.plant_size = plantSize;
	}
	
	/**
	 * 
	 * @param percentageCovered The percent covered, as indicated as an INTEGER on the data sheet.
	 */
	public void setPercentCoverage (int percentageCovered)
	{
		this.percent_coverage = percentageCovered;
	}
		
	/**
	 * 
	 * @param blooms_open This is typically a 1 character entry found on the data sheets. Contact system admin (Alex)
	 * in the event of any issues.
	 */
	public void setblooms_open (char blooms_open)
	{
		this.blooms_open = blooms_open;
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
	
	public int getPlantHeight()
	{
		return this.plant_height;
	}
	
	public int getPlantSize()
	{
		return this.plant_size;
	}
	
	public int getPercentCoverage()
	{
		return this.percent_coverage;
	}
	
	public char getBloomsOpen()
	{
		return this.blooms_open;
	}
	
	public char getVigor()
	{
		return this.vigor;
	}
	
	public void setPlotSize(int plot_size)
	{
		this.plot_size = plot_size;
	}
	
}
