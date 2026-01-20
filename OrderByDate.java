package assign02;

import java.util.Comparator;

/**
* Comparator that defines an ordering among current patients by their lastVisit.
* More specifically, if the lastVisit of the patients are the same,
* the comparison is based on the string representation of the ID
* UHealthIDs are guaranteed to be unique.
* 
* @author CS 2420 Justin Carter and Anthony Voegeli
* @version 01/18/26
*/
public class OrderByDate<Type> implements Comparator <CurrentPatientGeneric<Type>>{

	public int compare(CurrentPatientGeneric<Type> patient1, CurrentPatientGeneric<Type> patient2) {
		if (patient1.getLastVisit().compareTo(patient2.getLastVisit()) == 0) {
			return (patient1.getUHealthID().toString().compareTo(patient2.getUHealthID().toString()));
		}
		return patient1.getLastVisit().compareTo(patient2.getLastVisit());
	}
}
