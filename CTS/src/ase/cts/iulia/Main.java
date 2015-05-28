package ase.cts.iulia;

import java.sql.SQLException;
import java.text.ParseException;

public class Main {

	/**
	 * @param args
	 * @throws ParseException 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws TelephoneException 
	 * @throws EmailException 
	 * @throws NameException 
	 * @throws AddressException 
	 * @throws BookNotAvailableException 
	 * @throws LibraryException
	 */
	public static void main(String[] args) throws ClassNotFoundException, SQLException, ParseException, EmailException, TelephoneException, NameException, AddressException, TitleException, BookNotAvailableException {
		// TODO Auto-generated method stub
		
		Library lib = Library.getInstance();
		
		lib.addUser("Lia", null, "0740666098", "lia_oxanne@email.com", "Moxa D");
		lib.addBook("Divergent", "Veronica", "Roth");
		
		User us;
		Book bk;
		bk = lib.retrieveBook("Divergent");
		us = lib.retrieveOldUser(1);
		System.out.println(bk);
		System.out.println(us);
		Changer ch = new Changer();
		//ch.setState(State.UNAVAILABLE);
		//ch.makeChange(bk);
		//System.out.println(bk);
		/*bk = lib.retrieveBook("Divergent");
		System.out.println(bk);
		//lib.createLendingSheet(us, bk, "27-05-2015", "10-06-2015");
		LendingSheet ls;
		ls = lib.retrieveSheet(1, 3);
		System.out.println(ls);*/

	}

}
