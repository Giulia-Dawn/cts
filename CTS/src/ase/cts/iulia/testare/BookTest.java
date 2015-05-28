package ase.cts.iulia.testare;

import static org.junit.Assert.*;

import java.sql.SQLException;

import junit.framework.TestCase;

import org.junit.Test;

import ase.cts.iulia.Book;
import ase.cts.iulia.Library;
import ase.cts.iulia.LibraryException;

public class BookTest  extends TestCase{
	
Library library;
	
	public void setUp(){
		System.out.println("setUp Unit test");
		library = Library.getInstance();
	}

	public void testTitle() throws ClassNotFoundException, SQLException{
		try{
		library.addBook(null,"Lia","Oxanne");
		assertFalse("Metoda nu arunca exceptie", true);
		}
		catch(LibraryException ex){
			assertTrue(true);
		}
	}
	
	public void testFirstName() throws ClassNotFoundException, SQLException{
		try{
		library.addBook("Muse",null,"Oxanne");
		assertFalse("Metoda nu arunca exceptie", true);
		}
		catch(LibraryException ex){
			assertTrue(true);
		}
	}
	
	public void testLastName() throws ClassNotFoundException, SQLException{
		try{
		library.addBook("Muse","Lia",null);
		assertFalse("Metoda nu arunca exceptie", true);
		}
		catch(LibraryException ex){
			assertTrue(true);
		}
	}
	
	public void testRetrieve() throws  ClassNotFoundException, SQLException
	{
		try{
		Book b;
		b = library.retrieveBook("Ciresarii");
		assertFalse("Metoda nu arunca exceptie",true);
		}
		catch(LibraryException ex)
		{
			assertTrue(true);
		}
		
		
	}
	
	public void testRetrieveId() throws ClassNotFoundException, SQLException
	{
		try{
			Book b;
			b = library.retrieveBookId(8);
			assertFalse("Metoda nu arunca exceptie",true);
			}
			catch(LibraryException ex)
			{
				assertTrue(true);
			}
	}
	
}
