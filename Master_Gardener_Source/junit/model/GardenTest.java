package model;

import org.junit.*;

import static org.junit.Assert.assertEquals;

public class GardenTest {
    Garden g1, g2, g3;

    @Before
    public void setup()
    {
        g1 = new Garden(2,"343 Main Road Avenue Lane");
    }

    @Test(expected = IllegalArgumentException.class)
    public void illegalArgumentExceptionTest()
    {
        g2 = new Garden(1, "");
        g3 = new Garden(-1, "boo 420");
    }

    @Test
    public void getGardenAddressTest()
    {
        assertEquals(g1.getGardenAddress(), "343 Main Road Avenue Lane");
    }

    @Test
    public void setGardenAddressTest()
    {
    	g1.setGardenAddress("420 meme avenue");
    	assertEquals(g1.getGardenAddress(), "420 meme avenue");
    }
    
    @Test
    public void getGardenIDTest()
    {
        assertEquals(g1.getGarden_id(), 2);
    }
    
    @Test
    public void setGardenIDTest()
    {
    	g1.setGarden_id(42);
    	assertEquals(g1.getGarden_id(), 42);
    }
    
}
