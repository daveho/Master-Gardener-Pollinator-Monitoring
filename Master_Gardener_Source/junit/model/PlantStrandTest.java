package model;

import org.junit.*;

import static org.junit.Assert.assertEquals;

public class PlantStrandTest {
    PlantStrand p1, p2;

    @Before
    public void setup()
    {
        p1 = new PlantStrand("Alex Fly Trap");
    }

    @Test(expected = IllegalArgumentException.class)
    public void illegalArgumentExceptionTest()
    {
        p2 = new PlantStrand("");
    }

    @Test
    public void getStrandNameTest()
    {
        assertEquals(p1.getStrandName(), "Alex Fly Trap");
    }

    @Test
    public void setStrandNameTest()
    {
    	p1.setStrandName("Alex Tried Trap");
    	assertEquals(p1.getStrandName(), "Alex Tried Trap");
    }
    
}
