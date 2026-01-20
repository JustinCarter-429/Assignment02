package assign02;

import java.util.Comparator;

/**
* Comparator that defines an ordering among current patients by their first names.
* More specifically, if the first names of the patients are the same,
* then ordering by their last names, and if patients last names also are the same 
* the comparison is based on the string representation of the ID
* UHealthIDs are guaranteed to be unique.
* 
* @author CS 2420 Justin Carter and Anthony Voegeli
* @version 01/18/26
*/
public class OrderByName<Type> implements Comparator <CurrentPatientGeneric<Type>>{

	public int compare(CurrentPatientGeneric<Type> patient1, CurrentPatientGeneric<Type> patient2) {
		if (patient1.getFirstName().compareTo(patient2.getFirstName()) == 0) {
			if (patient1.getLastName().compareTo(patient2.getLastName()) == 0) 
				return patient1.getUHealthID().toString().compareTo(patient2.getUHealthID().toString());
		}
		return patient1.getFirstName().compareTo(patient2.getFirstName());
	}
}
