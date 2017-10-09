package model;

import org.junit.*;

import static org.junit.Assert.assertEquals;

public class AccountTest {
	Account a1, a2, a3, a4, a5;
	
    @Before
	public void setUp(){
    	//---------Same setup as BooksForSaleTest (all components needed)---------------------------------------------------------------
       
	    this.a1 = new Account("LoganH24", "abc12314", 1, "LogeyBear", "Logan.Harris1995@gmail.com", "555-555-5555");
	    this.a2 = new Account("Amcdevitt97", "def456123", 2, "Ally", "amcdevitt97@ycp.edu", "555-555-5555");
	    this.a3 = new Account("GRuths", "ghi789122", 3, "Garry", "gruths@ycp.edu", "555-555-5555");
	    
	
		//---------Same setup as BooksForSaleTest (all components needed)----------------------------------------------------------------
	}

	@Test(expected = IllegalArgumentException.class)
	public void illegalArgumentExceptionTest()
	{
		// Test password length
		a4 = new Account("ChugCroble", "1234567", 20, "Chug Croble", "chugcroble@gmail.com", "420-666-6969");

		// Test literally anything else
		a5 = new Account("Memes&Cheese", "123456789", -1, "", "", "");
	}

	@Test
	public void getUsernameTest(){
		assertEquals(a1.getUsername(),"LoganH24");
		assertEquals(a2.getUsername(),"Amcdevitt97");
		assertEquals(a3.getUsername(),"GRuths");
	}

	@Test
	//Only works if getUsername works, which is also tested.
	public void setUsernameTest()
	{
		a1.setUsername("mememan69");
		assertEquals(a1.getUsername(), "mememan69");
		
		a2.setUsername("crub");
		assertEquals(a2.getUsername(), "crub");
	}
	
	@Test
	public void getPasswordTest(){
		assertEquals(a1.getPassword(),"abc12314");
		assertEquals(a2.getPassword(),"def456123");
		assertEquals(a3.getPassword(),"ghi789122");
	}
	
	@Test
	//Bear in mind that this tests when password lengths meet requirements
	//(kind of like every other test)
	public void setPasswordTest()
	{
		a1.setPassword("crub_skriggle");
		assertEquals(a1.getPassword(), "crub_skriggle");
	}
	
	@Test
	public void getLoginIdTest(){
		assertEquals(a1.getLoginId(), 1);
		assertEquals(a2.getLoginId(), 2);
		assertEquals(a3.getLoginId(), 3);
	}
	
	@Test
	//This test won't be seen by the user
	public void setLoginIDTest()
	{
		a1.setLoginId(42);
		assertEquals(a1.getLoginId(), 42);
	}
	
	@Test
	public void getNameTest(){
		assertEquals(a1.getName(),"LogeyBear");
		assertEquals(a2.getName(),"Ally");
		assertEquals(a3.getName(),"Garry");
	}
	
	@Test
	public void setNameTest()
	{
		a1.setName("Edwardo");
		assertEquals(a1.getName(), "Edwardo");
	}
	
	@Test
	public void getEmailTest(){
		assertEquals(a1.getEmail(),"Logan.Harris1995@gmail.com");
		assertEquals(a2.getEmail(),"amcdevitt97@ycp.edu");
		assertEquals(a3.getEmail(),"gruths@ycp.edu");
	}
	
	@Test
	public void setEmailTest()
	{
		a1.setEmail("nicksucks@gmail.com");
		assertEquals(a1.getEmail(), "nicksucks@gmail.com");
	}
	
	@Test
	public void getDescriptionTest()
	{
		assertEquals(a1.getDescription(), "555-555-5555");
	}
	
	@Test
	public void setDescriptionTest()
	{
		a1.setDescription("555-555-5555 is a terrible description.");
		assertEquals(a1.getDescription(), "555-555-5555 is a terrible description.");
	}
	
}