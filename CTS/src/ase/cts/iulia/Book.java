package ase.cts.iulia;

public class Book {

	public Book(String title, String firstName, String lastName, boolean state) {
		super();
		this.title = title;
		this.firstName = firstName;
		this.lastName = lastName;
		this.state = state;
	}
	
	private int id;
	private String title;
	private String firstName;
	private String lastName;
	private boolean state;
	
	
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}
	
	
	public void setTitle(String title) {
		this.title = title;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	@Override
	public String toString() {
		String stt;
		if(state==false)
			stt = "Unavailable";
		else 
			stt = "Available";
		return "Book [id="+ id +", title=" + title + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", state=" + stt + "]";
	}
	
	
}
