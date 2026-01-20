package assign02;

import java.lang.classfile.instruction.ReturnInstruction;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.GregorianCalendar;

/**
 * This class represents a record of patients that have visited a UHealth
 * facility. It maintains a collection of CurrentPatients.
 *
 * @author CS 2420 course staff and ***STUDENT FILL YOUR NAME IN***
 * @version ***STUDENT FILL IN THE DATE***
 */
public class FacilityGeneric<Type> {

	private ArrayList<CurrentPatientGeneric<Type>> patientList;

	/**
	 * Creates an empty facility record.
	 */
	public FacilityGeneric() {
		patientList = new ArrayList<CurrentPatientGeneric<Type>>();
	}

	/**
	 * Adds the given patient to the list of patients, avoiding duplicates.
	 *
	 * @param patient - patient to be added to this record
	 * @return true if the patient was added, false if the patient was not added
	 *         because they already exist in the record
	 */
	public boolean addPatient(CurrentPatientGeneric<Type> patient) {
		if (patient == null) // change to "==" null because it addPatient(null) is throwing a
								// NullPointerException
			// before it ever throws our intended IllegalArgumentException
			throw new IllegalArgumentException("Please enter a patient to add");
		for (int i = 0; i < patientList.size(); i++)
			if (patientList.get(i).equals(patient)) {
				return false;
			} // ---> Checks to see if the current patient is already there if so it returns
				// false

		patientList.add(patient); // ---> if the patient was not on the list and was added it will return true.
		return true;
	}

	// public void add(ArrayList<CurrentPatient> patients) {
	// patientList.add(0, null);
	// }

	/**
	 * Adds all patients from the given list to the list of patients.
	 * 
	 * @param patients - list of patients to be added to this record
	 */
	public void addAll(ArrayList<CurrentPatientGeneric<Type>> patients) {
		patientList.addAll(patients);
	}

	/**
	 * Retrieves the patient with the given UHealthID.
	 *
	 * @param patientID - ID of patient to be retrieved
	 * @return the patient with the given ID, or null if no such patient exists in
	 *         the record
	 */
	public CurrentPatientGeneric<Type> lookupByUHID(UHealthID patientID) {
		// First we check to make sure patient ID is not null then we use
		// a for each loop to match the patientID parameter with the getUheathID method
		if (patientID == null) {
			throw new IllegalArgumentException("Please enter a correct ID.");
		}
		for (CurrentPatientGeneric<Type> patient : patientList) {
			if (patientID.equals(patient.getUHealthID())) {
				return patient;
			}
		}
		// for (int i = 0; i < patientList.size(); i++) { //commented this block of code
		// out until i am 100% sure
		// that we will not need it.
		// if (patientList.get(i).getUHealthID().equals(patientID))
		// return patientList.get(i);}
		return null;
	}

	/**
	 * Retrieves the patient(s) with the given physician.
	 *
	 * @param physician - physician of patient(s) to be retrieved
	 * @return a list of patient(s) with the given physician (in any order), or an
	 *         empty list if no such patients exist in the record
	 */
	public ArrayList<CurrentPatientGeneric<Type>> lookupByPhysician(Type physician) {
		// TODO: Fill in the method according to the contract.
		ArrayList<CurrentPatientGeneric<Type>> foundPatients = new ArrayList<>();
		for (int i = 0; i < patientList.size(); i++) {
			if (patientList.get(i).getPhysician() == physician) // if the current index at getPhysician matches the
																// physician info that was inputted we add it to the
																// list.
				foundPatients.add(patientList.get(i));
//		if (patientList.get(i).getPhysician() != physician) {
//			continue;}	
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
	public ArrayList<CurrentPatientGeneric<Type>> getRecentPatients(GregorianCalendar date) {
		// TODO: Fill in the method according to the contract.
		ArrayList<CurrentPatientGeneric<Type>> foundPatients = new ArrayList<>();
		for (int i = 0; i < patientList.size(); i++) {
			// int targetDate = date.compareTo(patientList.get(i).getLastVisit());
			int targetDate = patientList.get(i).getLastVisit().compareTo(date);
			if (targetDate == 1) {
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
	public ArrayList<Type> getPhysicianList() {
		// TODO: Fill in the method according to the contract.
		ArrayList<Integer> physicianList = new ArrayList<>();
		for (int i = 0; i < patientList.size(); i++) {
			if (physicianList.contains(patientList.get(i).getPhysician())) {
				continue;
			} else {
				physicianList.addAll(i, physicianList);
			}
		}
		return (ArrayList<Type>) physicianList;
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
	public void setPhysician(UHealthID patientID, Type physician) {
		// TODO: Fill in the method according to the contract.
		if (patientID == null) {
			throw new IllegalArgumentException("No patient in records.");
		}
		for (int i = 0; i < patientList.size(); i++) {
			if (patientList.get(i).getUHealthID().equals(patientID)) {
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
		if (patientID == null) {
			throw new IllegalArgumentException("No patient in records.");
		}
		for (int i = 0; i < patientList.size(); i++) {
			if (patientList.get(i).getUHealthID().equals(patientID)) {
				patientList.get(i).updateLastVisit(date);
			}
		}
	}
	
	/**
	 * Returns the list of current patients in this facility, sorted according to
	 * the provided Comparator.
	 * 
	 * @param cmp - the Comparator to use when sorting
	 * @return an ordered list of all patients in this facility
	 */
	public ArrayList<CurrentPatientGeneric<Type>> getOrderedPatients(Comparator<CurrentPatientGeneric<Type>> cmp) {
	    ArrayList<CurrentPatientGeneric<Type>> patientListCopy = new ArrayList<CurrentPatientGeneric<Type>>();
	    for(CurrentPatientGeneric<Type> patient : patientList) {
	        patientListCopy.add(patient);
	    }
	    sort(patientListCopy, cmp);
	    return patientListCopy;
	}

	/**
	 * Performs a SELECTION SORT on the input ArrayList.
	 * 
	 * 1. Finds the smallest item in the list. 
	 * 2. Swaps the smallest item with the first item in the list. 
	 * 3. Reconsiders the list to be the remaining unsorted portion (second item to Nth item) and 
	 *    repeats steps 1, 2, and 3.
	 * 
	 * @param list - to sort
	 * @param cmp  - Comparator to use
	 */
	private static <ListType> void sort(ArrayList<ListType> list, Comparator<ListType> cmp) {
	    for(int i = 0; i < list.size() - 1; i++) {
	        int j, minIndex;
	        for(j = i + 1, minIndex = i; j < list.size(); j++) {
	            if(cmp.compare(list.get(j), list.get(minIndex)) < 0) {
	                minIndex = j;
	            }
	        }
	        ListType temp = list.get(i);
	        list.set(i, list.get(minIndex));
	        list.set(minIndex, temp);
	    }
	}
}