package ase.cts.iulia;

import java.util.Date;

public class User {
	
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String telephone;
	private String address;
	private boolean state;
	private int delays;
	private Date banEndDate;
	
	public User (String fN, String lN, String em, String tel, String add, boolean st, int del, Date ban)
	{
		firstName = fN;
		lastName = lN;
		email = em;
		telephone = tel;
		address = add;
		state = st;
		delays = del;
		banEndDate = ban;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public int getDelays() {
		return delays;
	}

	public void setDelays(int delays) {
		this.delays = delays;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		String stt;
		if(state==false)
			stt = "Unavailable";
		else 
			stt = "Available";
		return "User [id=" + id + ", firstName=" + firstName + ", lastName="
				+ lastName + ", email=" + email + ", telephone=" + telephone
				+ ", address=" + address + ", state=" + state + ", delays="
				+ delays + ", banEndDate=" + banEndDate + "]";
	}

	public Date getBanEndDate() {
		return banEndDate;
	}

	public void setBanEndDate(Date banEndDate) {
		this.banEndDate = banEndDate;
	}
	
	

}
