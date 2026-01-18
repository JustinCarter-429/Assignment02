package assign02;

import java.util.GregorianCalendar;

public class CurrentPatient extends Patient{
	private int physician;
	private GregorianCalendar lastVisit;

	public CurrentPatient(String firstName, String lastName, UHealthID uHealthID, 
			int physician, GregorianCalendar lastVisit) {
		super(firstName, lastName, uHealthID);
		this.physician = physician;
		this.lastVisit = lastVisit;
	}
	
	public int getPhysician() {
		if(this.physician < 0)
			throw new IllegalArgumentException();
		return this.physician;
	}
	
	public GregorianCalendar getLastVisit() {
		return this.lastVisit;
		
	}

	public void updatePhysician(int newPhysician) {
		if(newPhysician < 0)
			throw new IllegalArgumentException();
		this.physician = newPhysician;
	}
	
	public void updateLastVisit(GregorianCalendar date) {
		this.lastVisit = date;
	}

}