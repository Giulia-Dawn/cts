package ase.cts.iulia;

public class Changer {

	IChange now;
	
	public Changer (){
		now = new Default();
	}
	
	public void setState(State st)
	{
		if(st == State.AVAILABLE)
		{
			now = new Available();
		} 
		else if (st == State.UNAVAILABLE)
		{
			now = new Unavailable();
		}
		else if(st == State.ACTIVE)
		{
			now = new Active();
		}
		else if(st == State.INACTIVE)
		{
			now = new Inactive();
		}
	}
	
	public void makeChange(Book b)
	{
		this.now.change(b);
	}
	
	public void makeChante(User u)
	{
		this.now.change(u);
	}
	
	
	
}
