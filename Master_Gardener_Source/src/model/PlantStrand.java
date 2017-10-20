package model;

public class PlantStrand
{
	private String strand_name = "";

	public PlantStrand(String strand_name)
	{
		this.strand_name = strand_name;
	}
	
	public String getStrandName()
	{
		return this.strand_name;
	}
	
	public void setStrandName(String strand_name)
	{
		this.strand_name = strand_name;
	}
}