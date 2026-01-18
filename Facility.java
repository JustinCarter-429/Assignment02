package assign02;

import java.lang.classfile.instruction.ReturnInstruction;
import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * This class represents a record of patients that have visited a UHealth
 * facility. It maintains a collection of CurrentPatients.
 *
 * @author CS 2420 course staff and ***STUDENT FILL YOUR NAME IN***
 * @version ***STUDENT FILL IN THE DATE***
 */
public class Facility {

	private ArrayList<CurrentPatient> patientList;

	/**
	 * Creates an empty facility record.
	 */
	public Facility() {
		patientList = new ArrayList<CurrentPatient>();
	}

	/**
	 * Adds the given patient to the list of patients, avoiding duplicates.
	 *
	 * @param patient - patient to be added to this record
	 * @return true if the patient was added, false if the patient was not added
	 *         because they already exist in the record
	 */
	public boolean addPatient(CurrentPatient patient) {
		if (patient == null) // change to "==" null because it addPatient(null) is throwing a NullPointerException 
			//before it ever throws our intended IllegalArgumentException
			throw new IllegalArgumentException("Please enter a patient to add");
		for (int i = 0; i < patientList.size(); i++)
			if (patientList.get(i).equals(patient)) {
				return false;} //---> Checks to see if the current patient is already there if so it returns false
				 
	patientList.add(patient);	// ---> if the patient was not on the list and was added it will return true.	
		return true;}

	//public void add(ArrayList<CurrentPatient> patients) {
		//patientList.add(0, null);
	//}

	/**
	 * Adds all patients from the given list to the list of patients.
	 * 
	 * @param patients - list of patients to be added to this record
	 */
	public void addAll(ArrayList<CurrentPatient> patients) {
		patientList.addAll(patients);
	}

	/**
	 * Retrieves the patient with the given UHealthID.
	 *
	 * @param patientID - ID of patient to be retrieved
	 * @return the patient with the given ID, or null if no such patient exists in
	 *         the record
	 */
	public CurrentPatient lookupByUHID(UHealthID patientID) {
		// First we check to make sure patient ID is not null then we use
		// a for each loop to match the patientID parameter with the getUheathID method
		if (patientID == null) {
			throw new IllegalArgumentException("Please enter a correct ID.");
		}
		for (CurrentPatient patient : patientList) {
			if (patientID.equals(patient.getUHealthID())) {
				return patient;
			}
		}
		//for (int i = 0; i < patientList.size(); i++) { //commented this block of code out until i am 100% sure 
		// that we will not need it.
			//if (patientList.get(i).getUHealthID().equals(patientID))
		//		return patientList.get(i);}
		return null;
		}

	/**
	 * Retrieves the patient(s) with the given physician.
	 *
	 * @param physician - physician of patient(s) to be retrieved
	 * @return a list of patient(s) with the given physician (in any order), or an
	 *         empty list if no such patients exist in the record
	 */
	public ArrayList<CurrentPatient> lookupByPhysician(int physician) {
		// TODO: Fill in the method according to the contract.
		ArrayList<CurrentPatient> foundPatients = new ArrayList<>();
		for (int i = 0; i < patientList.size(); i++) {
			if (patientList.get(i).getPhysician() == physician) // if the current index at getPhysician matches the physician info that was inputted we add it to the list.
				foundPatients.add(patientList.get(i));
		if (patientList.get(i).getPhysician() != physician) {
			continue;}	
		}
		return foundPatients; // we then return the list after
		
	}

	/**
	 * Retrieves the patient(s) with last visits newer than a given date. Note that
	 * GregorianCalendar includes a compareTo method that may be useful.
	 *
	 * NOTE: If the last visit date equals this date, do not add the patient.
	 *
	 * @param date - cutoff date earlier than visit date of all returned patients.
	 * @return a list of patient(s) with last visit date after cutoff (in any
	 *         order), or an empty list if no such patients exist in the record
	 */
	public ArrayList<CurrentPatient> getRecentPatients(GregorianCalendar date) {
		// TODO: Fill in the method according to the contract.
		ArrayList<CurrentPatient> foundPatients = new ArrayList<>();
		for (int i = 0; i < patientList.size(); i++) {
			//int targetDate = date.compareTo(patientList.get(i).getLastVisit());
			int targetDate = patientList.get(i).getLastVisit().compareTo(date); 
			if (targetDate == 1 ){
				foundPatients.add(0, patientList.get(i));
		}
		}
		return foundPatients;
		}
	

	/**
	 * Retrieves a list of physicians assigned to patients at this facility.
	 *
	 * NOTE: Do not put duplicates in the list. Make sure each physician is only
	 * added once.
	 *
	 * @return a list of physician(s) assigned to current patients, or an empty list
	 *         if no patients exist in the record
	 */
	public ArrayList<Integer> getPhysicianList() {
		// TODO: Fill in the method according to the contract.
		ArrayList<Integer> physicianList = new ArrayList<>();
		for (int i = 0; i < patientList.size(); i++) {
			if (physicianList.contains(patientList.get(i).getPhysician())){
				continue ;		
			} else {
				physicianList.addAll(i, physicianList);
			}
		}
		return physicianList;
	}

	/**
	 * Sets the physician of a patient with the given UHealthID.
	 *
	 * NOTE: If no patient with the ID exists in the collection, then this method
	 * has no effect.
	 *
	 * @param patientID - UHealthID of patient to modify
	 * @param physician - identifier of patient's new physician
	 */
	public void setPhysician(UHealthID patientID, int physician) {
		// TODO: Fill in the method according to the contract.
		if(patientID == null) {
			throw new IllegalArgumentException("No patient in records.");
		}		
			for (int i = 0; i < patientList.size(); i++) {
			if(patientList.get(i).getUHealthID().equals(patientID)){
				patientList.get(i).updatePhysician(physician);
			}
		}
	}

	/**
	 * Sets the last visit date of a patient with the given UHealthID.
	 *
	 * NOTE: If no patient with the ID exists in the collection, then this method
	 * has no effect.
	 *
	 * @param patientID - UHealthID of patient to modify
	 * @param date      - date of last visit
	 */
	public void setLastVisit(UHealthID patientID, GregorianCalendar date) {
		// TODO: Fill in the method according to the contract.
		if(patientID == null) {
			throw new IllegalArgumentException("No patient in records.");
		}		
		for (int i = 0; i < patientList.size(); i++) {
		if(patientList.get(i).getUHealthID().equals(patientID)){
			patientList.get(i).updateLastVisit(date);
	}
}
	}}