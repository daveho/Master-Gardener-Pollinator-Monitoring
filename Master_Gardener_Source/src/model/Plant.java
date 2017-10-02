package model;

public class Plant
{
	private int plantID = -1;
	private String plant_species = "";
	
	// Unsure if this ( v ) should be here
	// private int weekNumber;
	
	public Plant(int plantID, String plant_species)
	{
		this.plantID = plantID;
		this.plant_species = plant_species;
	}
	
	public void setPlantID(int plantID)
	{
		this.plantID = plantID;
	}
	
	public int getPlantID()
	{
		return this.plantID;
	}
	
	public void setPlantSpecies(String plant_species)
	{
		this.plant_species = plant_species;
	}
	
	public String getPlantSpecies()
	{
		return this.plant_species;
	}
}
