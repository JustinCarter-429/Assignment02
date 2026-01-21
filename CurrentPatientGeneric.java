package assign02;

import java.util.GregorianCalendar;

/**
 * This class represents a UHealth CurrentPatient of the generic type who has a
 * unique physician and a lastVisit, also inheriting UHealthID, firstName, and
 * lastName from the parent class of Patient.
 *
 * @author CS 2420 course staff and Justin Carter and Anthony Voegeli
 * @version 01/19/2026
 */
public class CurrentPatientGeneric<Type> extends Patient {
	private Type physician; // Changed the int data type to the generic Type as instructed
	private GregorianCalendar lastVisit;

	/**
	 * Creates a CurrentPatient with a given name, ID, physician and their
	 * lastVisit.
	 *
	 * @param firstName  - of the patient
	 * @param lastName   - of the patient
	 * @param UHealthID  - of the patient
	 * @param physician  - of the patient
	 * @param lastVisity - of the patient
	 */
	public CurrentPatientGeneric(String firstName, String lastName, UHealthID uHealthID, Type physician,
			GregorianCalendar lastVisit) {
		// Changed the date type of physician in the parameter to the Generic Type
		super(firstName, lastName, uHealthID);
		this.physician = physician;
		this.lastVisit = lastVisit;
	}

	/**
	 * Method to retrieve the physician information of the patient.
	 * 
	 * @return physician - of the patient.
	 */
	public Type getPhysician() {
		return this.physician;
	}

	/**
	 * Method to retrieve the lastVisity information of the patient.
	 * 
	 * @return lastVisit - of the patient.
	 */
	public GregorianCalendar getLastVisit() {
		return this.lastVisit;
	}

	/**
	 * Method to change the current physician of a the CurrentPatient to a
	 * newPhysician.
	 * 
	 * @param newPhysician - of the patient.
	 */
	public void updatePhysician(Type newPhysician) {
		this.physician = newPhysician;
	}

	/**
	 * Method to update the lastVisit of the CurrentPatient to a new date.
	 * 
	 * @param date - of the patient.
	 */
	public void updateLastVisit(GregorianCalendar date) {
		this.lastVisit = date;
	}
}