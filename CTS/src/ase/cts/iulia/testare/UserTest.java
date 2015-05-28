package ase.cts.iulia.testare;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.text.ParseException;

import junit.framework.TestCase;

import org.junit.Test;


import ase.cts.iulia.EmailException;
import ase.cts.iulia.Library;
import ase.cts.iulia.LibraryException;
import ase.cts.iulia.TelephoneException;
import ase.cts.iulia.User;

public class UserTest extends TestCase {

	
	
	Library library;
	
	public void setUp(){
		System.out.println("setUp Unit test");
		library = Library.getInstance();
	}
	
	public void testNumber() throws ClassNotFoundException, SQLException{
		try{
		library.addUser("Iulia","Dobos","asd098","iulia@email.com","Moxa D");
		assertFalse("Metoda nu arunca exceptie", true);
		}
		catch(LibraryException ex){
			assertTrue(true);
		}
	}
	
	public void testEmail() throws ClassNotFoundException, SQLException{
		try{
		library.addUser("Iulia","Dobos","098","iulia.email.com","Moxa D");
		assertFalse("Metoda nu arunca exceptie", true);
		}
		catch(LibraryException ex){
			assertTrue(true);
		}
	}
	
	public void testFirstName() throws ClassNotFoundException, SQLException{
		try{
		library.addUser(null,"Dobos","098","iulia@email.com","Moxa D");
		assertFalse("Metoda nu arunca exceptie", true);
		}
		catch(LibraryException ex){
			assertTrue(true);
		}
	}
	
	public void testLastName() throws ClassNotFoundException, SQLException{
		try{
		library.addUser("Iulia",null,"098","iulia@email.com","Moxa D");
		assertFalse("Metoda nu arunca exceptie", true);
		}
		catch(LibraryException ex){
			assertTrue(true);
		}
	}
	
	public void testRetrieveUser() throws ClassNotFoundException, SQLException, ParseException
	{
		String expected = "0740666098";
		User u;
		u = library.retrieveOldUser(1);
		assertEquals(
				expected,
				u.getTelephone());
		
	}
		
	
	
	

}
