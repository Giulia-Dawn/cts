package ase.cts.iulia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Unavailable implements IChange {

	@Override
	public void change(Book b) {
		// TODO Auto-generated method stub
		
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
			
			//String nume = "Tori";
			String sqlInsert="UPDATE Books SET State = 0 WHERE BookId = "+b.getId()+";";
			comanda = conexiune.createStatement();
			
			int noRecords = comanda.executeUpdate(sqlInsert);
			if(noRecords == 0)
				System.out.println("Modificare nereusita");
			else
				System.out.println("Modificare inserata");
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

	@Override
	public void change(User u) {
		// TODO Auto-generated method stub
		
	}

}
