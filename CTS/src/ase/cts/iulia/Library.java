package ase.cts.iulia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Library {

	private static Library instance = null;

	SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
	Changer ch = new Changer();
	
	
	private Library() {};
	
	public static Library getInstance (){
		if(instance == null)
			instance = new Library();
		return instance;
			
	}
	
	public void addUser(String firstName, String lastName, String telephone, String email, String address) throws EmailException, TelephoneException, SQLException, ClassNotFoundException, NameException, AddressException
	{
		Connection conexiune = null;
		
		try{
			//folosind reflection incarcam conectorul specific SQLite sau altceva
			Class.forName("org.sqlite.JDBC");
			
			//instantiem conexiunea
			conexiune = DriverManager.getConnection("jdbc:sqlite:cts.db");
			System.out.println("Conexiune reusita.");
			conexiune.setAutoCommit(false);
			//definim o tabela in baza de date
			
			
			
			
			Statement comanda = conexiune.createStatement();
			
			if(firstName == null || lastName == null)
			{
				throw new NameException();
			}
			else if(address == null)
			{
				throw new AddressException();
			} else
			if(!(email.matches("^.+@.+\\..+$"))|| email == null)
			{
				throw new EmailException();
			} 
			else if(!(telephone.matches("[0-9]+")) || email == null)
			{
				throw new TelephoneException();
			}
			else
			{
				
				String sqlInsert="INSERT INTO Users(FirstName,LastName,Email,Telephone,Address,State,NumberDelays) "
						+ "VALUES('"+firstName+"','"+lastName+"','"+email+"','"+telephone+"','"+address+"',1,0);";
				comanda = conexiune.createStatement();
				
				int noRecords = comanda.executeUpdate(sqlInsert);
				if(noRecords == 0)
					System.out.println("Inserare nereusita");
				else
					System.out.println("Inregistrare inserata");
				conexiune.commit();
				
				comanda.close();
			}
			
		}
		finally{
			try {
				conexiune.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void addBook(String title, String firstName, String lastName) throws SQLException, ClassNotFoundException, TitleException, NameException{
		
		
		Connection conexiune = null;
		
		try{
			//folosind reflection incarcam conectorul specific SQLite sau altceva
			Class.forName("org.sqlite.JDBC");
			
			//instantiem conexiunea
			conexiune = DriverManager.getConnection("jdbc:sqlite:cts.db");
			System.out.println("Conexiune reusita.");
			conexiune.setAutoCommit(false);
			//definim o tabela in baza de date
			
			
			
			Statement comanda = conexiune.createStatement();
			
			
			if(title==null)
			{
				throw new TitleException();
			}
			else 
				if(firstName == null || lastName == null)
				{
					throw new NameException();
				}
				else
			{
			String sqlInsert="INSERT INTO Books(Title,AuthorFirstName,AuthorLastName,State) "
					+ "VALUES('"+title+"','"+firstName+"','"+lastName+"',1);";
			comanda = conexiune.createStatement();
			
			int noRecords = comanda.executeUpdate(sqlInsert);
			if(noRecords == 0)
				System.out.println("Inserare nereusita");
			else
				System.out.println("Inregistrare inserata");
			conexiune.commit();
			
			comanda.close();}
			
		}
		finally{
			try {
				conexiune.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public User retrieveOldUser(int id) throws SQLException, ParseException, ClassNotFoundException
	{
		Connection conexiune = null;
		
		try{
			//folosind reflection incarcam conectorul specific SQLite sau altceva
			Class.forName("org.sqlite.JDBC");
			
			//instantiem conexiunea
			conexiune = DriverManager.getConnection("jdbc:sqlite:cts.db");
			System.out.println("Conexiune reusita.");
			conexiune.setAutoCommit(false);
			//definim o tabela in baza de date
			
			
			
			//selectie date din tabela Muncitori
			String sql="SELECT * FROM Users WHERE UserId = "+ id + ";";
			Statement comanda = conexiune.createStatement();
			
			ResultSet rs = comanda.executeQuery(sql);
			
			int size=0;
			while (rs.next()) {
			    size++;
			}
			rs = comanda.executeQuery(sql);
			if(size!=1)
			{
				//throw exception
			}
			boolean state;
			if(rs.getInt("State") == 0)
				state = false;
			else if(rs.getInt("State")==1)
				state = true;
			else
				//throw exept
				state = true;
			
			User us = new User(rs.getString("FirstName"),rs.getString("LastName"),
					rs.getString("Email"),rs.getString("Telephone"),rs.getString("Address"),
					state,rs.getInt("NumberDelays"), formatter.parse(rs.getString("BanEndDate")));
			us.setId(rs.getInt("UserId"));
			return us;
			
		}
		finally{
			try {
				conexiune.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public Book retrieveBook(String title) throws BookNotAvailableException, SQLException, ClassNotFoundException{
		
		Connection conexiune = null;
		
		try{
			//folosind reflection incarcam conectorul specific SQLite sau altceva
			Class.forName("org.sqlite.JDBC");
			
			//instantiem conexiunea
			conexiune = DriverManager.getConnection("jdbc:sqlite:cts.db");
			System.out.println("Conexiune reusita.");
			conexiune.setAutoCommit(false);
			//definim o tabela in baza de date
			
			
			
			//selectie date din tabela Muncitori
			String sql="SELECT * FROM Books WHERE Title = '"+ title + "' AND State = 1;";
			Statement comanda = conexiune.createStatement();
			
			ResultSet rs = comanda.executeQuery(sql);
			
			int size=0;
			while (rs.next()) {
			    size++;
			}
			rs = comanda.executeQuery(sql);
			Book bk;
			boolean state;
			
			if(size>=1)
			{
				
				
				if(rs.getInt("State") == 0)
					state = false;
				else if(rs.getInt("State")==1)
					state = true;
				else
					//throw exept
					state = true;
				bk = new Book(rs.getString("Title"),rs.getString("AuthorFirstName"),
						rs.getString("AuthorLastName"),state);
				bk.setId(rs.getInt("BookId"));
				return bk;
			}
			/*else if(size==1){
				if(rs.getInt("State") == 0)
					state = false;
				else if(rs.getInt("State")==1)
					state = true;
				else
					//throw exept
					state = true;
				bk = new Book(rs.getString("Title"),rs.getString("AuthorFirstName"),
						rs.getString("AuthorLastName"),state);
			}*/
			else
				//throw exception
				throw new BookNotAvailableException();
			
			
			
		}
		finally{
			try {
				conexiune.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public Book retrieveBookId(int id) throws BookNotAvailableException, ClassNotFoundException, SQLException{
		
		Connection conexiune = null;
		
		try{
			//folosind reflection incarcam conectorul specific SQLite sau altceva
			Class.forName("org.sqlite.JDBC");
			
			//instantiem conexiunea
			conexiune = DriverManager.getConnection("jdbc:sqlite:cts.db");
			System.out.println("Conexiune reusita.");
			conexiune.setAutoCommit(false);
			//definim o tabela in baza de date
			
			
			
			//selectie date din tabela Muncitori
			String sql="SELECT * FROM Books WHERE BookId = "+ id + " AND State = 1;";
			Statement comanda = conexiune.createStatement();
			
			ResultSet rs = comanda.executeQuery(sql);
			
			int size=0;
			while (rs.next()) {
			    size++;
			}
			rs = comanda.executeQuery(sql);
			Book bk;
			boolean state;
			
			if(size>=1)
			{
				
				
				if(rs.getInt("State") == 0)
					state = false;
				else if(rs.getInt("State")==1)
					state = true;
				else
					//throw exept
					state = true;
				bk = new Book(rs.getString("Title"),rs.getString("AuthorFirstName"),
						rs.getString("AuthorLastName"),state);
				bk.setId(rs.getInt("BookId"));
			}
			
			else
				//throw exception
				throw new BookNotAvailableException();
			
			return bk;
			
		}
		finally{
			try {
				conexiune.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
}
	
	
	public void createLendingSheet(User usr, Book bk, String start, String end)
	{
		Connection conexiune = null;
		
		try{
			//folosind reflection incarcam conectorul specific SQLite sau altceva
			Class.forName("org.sqlite.JDBC");
			
			//instantiem conexiunea
			conexiune = DriverManager.getConnection("jdbc:sqlite:cts.db");
			System.out.println("Conexiune reusita.");
			conexiune.setAutoCommit(false);
			//definim o tabela in baza de date
			
			
			
			Statement comanda = conexiune.createStatement();
			
			
			String dateStart = null;
			String dateEnd = null;
		 
			try {
		 
				dateStart = formatter.format(formatter.parse(start));
				dateEnd = formatter.format(formatter.parse(end));
		 
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			//String nume = "Tori";
			String sqlInsert="INSERT INTO LendingSheets(UserId,BookId,StartDate,EndDate) "
					+ "VALUES("+usr.getId()+","+bk.getId()+",'"+dateStart+"','"+dateEnd+"');";
			comanda = conexiune.createStatement();
			
			int noRecords = comanda.executeUpdate(sqlInsert);
			if(noRecords == 0)
				System.out.println("Inserare nereusita");
			else
				System.out.println("Inregistrare inserata");
			conexiune.commit();
			
			comanda.close();
			
		}
		catch(Exception ex)
		{
			System.out.println("Eroare conexiune "+ex.getMessage());
		}
		finally{
			try {
				conexiune.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void archiveLendingSheet(int id)
	{
		Connection conexiune = null;
		
		try{
			//folosind reflection incarcam conectorul specific SQLite sau altceva
			Class.forName("org.sqlite.JDBC");
			
			//instantiem conexiunea
			conexiune = DriverManager.getConnection("jdbc:sqlite:cts.db");
			System.out.println("Conexiune reusita.");
			conexiune.setAutoCommit(false);
			//definim o tabela in baza de date
			
			
			//selectie date din tabela Muncitori
			String sql="SELECT * FROM LendingSheets WHERE SheetId = "+ id + ";";
			Statement comanda = conexiune.createStatement();
			
			ResultSet rs = comanda.executeQuery(sql);
			
			int userId = rs.getInt("UserId");
			int bookId = rs.getInt("BookId");
			String startDate = rs.getString("StartDate");
			String endDate = rs.getString("EndDate");
			
			sql = "SELECT * FROM Users WHERE UserId = "+ userId + ";";
			rs = comanda.executeQuery(sql);
			
			boolean state;
			if(rs.getInt("State") == 0)
				state = false;
			else if(rs.getInt("State")==1)
				state = true;
			else
				//throw exept
				state = true;
			
			User usr = new User(rs.getString("FirstName"),rs.getString("LastName"),
					rs.getString("Email"),rs.getString("Telephone"),rs.getString("Address"),
					state,rs.getInt("NumberDelays"),formatter.parse(rs.getString("BanEndDate")));
			usr.setId(rs.getInt("UserId"));
			
			
			
			sql = "SELECT * FROM Books WHERE BookId = "+ bookId + ";";
			rs = comanda.executeQuery(sql);
			
			if(rs.getInt("State") == 0)
				state = false;
			else if(rs.getInt("State")==1)
				state = true;
			else
				//throw exept
				state = true;
			
			Book bk = new Book(rs.getString("Title"),rs.getString("AuthorFirstName"),
					rs.getString("AuthorLastName"),state);
			
			
			
			LendingSheet ls = new LendingSheet(usr,bk,
						formatter.parse(startDate),formatter.parse(endDate));
			
			sql = "INSERT INTO ArchivedSheets(SheetId,UserId,BookId,StartDate,EndDate) ="
					+ "VALUES("+id+","+ls.getUser().getId()+","+ls.getBook().getId()+",'"+ls.getStartDate()+"','"+ ls.getEndDate() + "');";
			
			comanda = conexiune.createStatement();
			
			int noRecords = comanda.executeUpdate(sql);
			if(noRecords == 0)
				System.out.println("Inserare nereusita");
			else
				System.out.println("Inregistrare inserata");
			
			sql = "DELETE * FROM LendingSheets WHERE SheetId = "+id+";";
			comanda = conexiune.createStatement();
			
			noRecords = comanda.executeUpdate(sql);
			if(noRecords == 0)
				System.out.println("Stergere nereusita");
			else
				System.out.println("Stergere reusita");
			
			conexiune.commit();
			
			comanda.close();
			
		}
		catch(Exception ex)
		{
			System.out.println("Eroare conexiune "+ex.getMessage());
		}
		finally{
			try {
				conexiune.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public LendingSheet retrieveSheet(int usrId, int bkId)
	{
		Connection conexiune = null;
		
		try{
			//folosind reflection incarcam conectorul specific SQLite sau altceva
			Class.forName("org.sqlite.JDBC");
			
			//instantiem conexiunea
			conexiune = DriverManager.getConnection("jdbc:sqlite:cts.db");
			System.out.println("Conexiune reusita.");
			conexiune.setAutoCommit(false);
			//definim o tabela in baza de date
			
			
			//selectie date din tabela Muncitori
			String sql="SELECT * FROM LendingSheets WHERE UserId = "+ usrId + " AND BookId = "+ bkId + ";";
			Statement comanda = conexiune.createStatement();
			
			ResultSet rs = comanda.executeQuery(sql);
			
			int userId = rs.getInt("UserId");
			int bookId = rs.getInt("BookId");
			String startDate = rs.getString("StartDate");
			String endDate = rs.getString("EndDate");
			
			sql = "SELECT * FROM Users WHERE UserId = "+ userId + ";";
			rs = comanda.executeQuery(sql);
			
			boolean state;
			if(rs.getInt("State") == 0)
				state = false;
			else if(rs.getInt("State")==1)
				state = true;
			else
				//throw exept
				state = true;
			
			User usr = new User(rs.getString("FirstName"),rs.getString("LastName"),
					rs.getString("Email"),rs.getString("Telephone"),rs.getString("Address"),
					state,rs.getInt("NumberDelays"),formatter.parse(rs.getString("BanEndDate")));
			usr.setId(rs.getInt("UserId"));
			
			
			
			sql = "SELECT * FROM Books WHERE BookId = "+ bookId + ";";
			rs = comanda.executeQuery(sql);
			
			if(rs.getInt("State") == 0)
				state = false;
			else if(rs.getInt("State")==1)
				state = true;
			else
				//throw exept
				state = true;
			
			Book bk = new Book(rs.getString("Title"),rs.getString("AuthorFirstName"),
					rs.getString("AuthorLastName"),state);
			
			
			
			LendingSheet ls = new LendingSheet(usr,bk,
						formatter.parse(startDate),formatter.parse(endDate));
			
			conexiune.commit();
			
			comanda.close();
			
			return ls;
			
		}
		catch(Exception ex)
		{
			System.out.println("Eroare conexiune "+ex.getMessage());
			return null;
		}
		finally{
			try {
				conexiune.close();
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void addDelay(int userId){
		
		Connection conexiune = null;
		
		try{
			//folosind reflection incarcam conectorul specific SQLite sau altceva
			Class.forName("org.sqlite.JDBC");
			
			//instantiem conexiunea
			conexiune = DriverManager.getConnection("jdbc:sqlite:cts.db");
			System.out.println("Conexiune reusita.");
			conexiune.setAutoCommit(false);
			//definim o tabela in baza de date
			
			
			
			
			String sql = "SELECT * FROM Users WHERE UserId = "+ userId + ";";
			Statement comanda = conexiune.createStatement();
			ResultSet rs = comanda.executeQuery(sql);
			
			int del = rs.getInt("NumberDelays") + 1;
			
			sql = "UPDATE Users SET NumberDelays = "+del+" WHERE UserId = "+userId+";";
			
			int noRecords = comanda.executeUpdate(sql);
			if(noRecords == 0)
				System.out.println("Modificare nereusita");
			else
				System.out.println("Modificare inserata");
			comanda = conexiune.createStatement();
			
			conexiune.commit();
			
			comanda.close();
			
			
			
		}
		catch(Exception ex)
		{
			System.out.println("Eroare conexiune "+ex.getMessage());
			
		}
		finally{
			try {
				conexiune.close();
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void lendBook(int userId, int bookId, String startDate, String endDate) throws ClassNotFoundException, SQLException, ParseException, BookNotAvailableException
	{
		Date  dt = new Date();
		User u;
		Book b;
		LendingSheet ls;
		
		u = this.retrieveOldUser(userId);
		b = this.retrieveBookId(bookId);
		if(u.isState() == false)
			if(dt.after(u.getBanEndDate()))
			{
				ch.setState(State.ACTIVE);
				ch.makeChante(u);
			}
			else{
				//throw
			}
				
		if(u.getDelays() >= 5)
		{
			ch.setState(State.INACTIVE);
			ch.makeChante(u);
			//throw message
		}
			
		else
		{
			this.createLendingSheet(u, b, startDate, endDate);
			ch.setState(State.UNAVAILABLE);
			ch.makeChange(b);
		}
			
	}
	
	public void returnBook(int userId, int bookId) throws ClassNotFoundException, SQLException, ParseException, BookNotAvailableException
	{
		Date dt = new Date();
		User u;
		Book b;
		u = this.retrieveOldUser(userId);
		b = this.retrieveBookId(bookId);
		LendingSheet ls;
		ls = this.retrieveSheet(userId, bookId);
		if(dt.after(ls.getEndDate()))
		{
			this.addDelay(userId);
		}
		if(u.getDelays()>=5)
		{
			ch.setState(State.INACTIVE);
			ch.makeChante(u);
		}
		ch.setState(State.AVAILABLE);
		ch.makeChange(b);
	}
}
