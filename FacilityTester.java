package assign02;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.Scanner;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains tests for Facility.
 * 
 * @author CS 2420 course staff and ***STUDENT FILL YOUR NAME IN***
 * @version ***STUDENT FILL IN THE DATE***
 */
public class FacilityTester {

	private Facility emptyFacility, verySmallFacility, smallFacility;
	private UHealthID uHID1, uHID2, uHID3, uHID4, uHID5, uHID6;
	private GregorianCalendar date1, date2, date3, date4, date5, date6;

	@BeforeEach
	void setUp() throws Exception {

		uHID1 = new UHealthID("AAAA-1111");
		uHID2 = new UHealthID("BCBC-2323");
		uHID3 = new UHealthID("HRHR-7654");

		date1 = new GregorianCalendar(2023, 0, 1);
		date2 = new GregorianCalendar(2023, 3, 17);
		date3 = new GregorianCalendar(2022, 8, 21);
		date4 = new GregorianCalendar(2021, 5, 21);
		date5 = new GregorianCalendar(2023, 5, 14);
		date6 = new GregorianCalendar(2024, 5, 24);

		emptyFacility = new Facility();

		verySmallFacility = new Facility();
		verySmallFacility.addPatient(new CurrentPatient("Jane", "Doe", uHID1, 1010101, date1));
		verySmallFacility.addPatient(new CurrentPatient("Drew", "Hall", uHID2, 3232323, date2));
		verySmallFacility.addPatient(new CurrentPatient("Riley", "Nguyen", uHID3, 9879876, date3));
		verySmallFacility.addPatient(new CurrentPatient("john", "manzel", uHID4, 3232323, date5));
		verySmallFacility.addPatient(new CurrentPatient("Justin", "Carter", uHID5, 9879876, date4));
		verySmallFacility.addPatient(new CurrentPatient("Drew", "Hall", uHID2, 3232323, date2));

		smallFacility = new Facility();
		smallFacility.addAll(readFromFile("src/assign02/small_patient_list.txt"));

		// Extend this tester to add more tests for the facilities above,
		// as well as to create and test larger facilities.
		// (HINT: For a larger facility, use the helpers at the end of this file to
		// generate names, IDs, and dates.)
	}

	// Empty Facility tests --------------------------------------------------------

	@Test
	public void testEmptyLookupUHID() {
		assertNull(emptyFacility.lookupByUHID(uHID1));
	}

	@Test
	public void testEmptyLookupPhysician() {
		ArrayList<CurrentPatient> patients = emptyFacility.lookupByPhysician(1010101);
		assertEquals(0, patients.size());
	}

	@Test
	public void testEmptySetVisit() {
		// ensure no exceptions thrown
		emptyFacility.setLastVisit(uHID2, date3);
	}

	@Test
	public void testEmptySetPhysician() {
		// ensure no exceptions thrown
		emptyFacility.setPhysician(uHID2, 1010101);
	}

	@Test
	public void testEmptyGetRecentPatients() {
		ArrayList<CurrentPatient> patients = emptyFacility.getRecentPatients(date3);
		assertEquals(0, patients.size());
	}

	// Very small facility tests ---------------------------------------------------

	@Test
	public void testVerySmallLookupUHID() {
		Patient expected = new Patient("Drew", "Hall", new UHealthID("BCBC-2323"));
		CurrentPatient actual = verySmallFacility.lookupByUHID(new UHealthID("BCBC-2323"));
		assertEquals(expected, actual);
	}

	@Test
	public void testVerySmallLookupPhysicianCount() {
		ArrayList<CurrentPatient> actualPatients = verySmallFacility.lookupByPhysician(9879876);
		assertEquals(2, actualPatients.size()); // changed this to 2 because 
		// I added extra patients to different physicians
	}

	@Test
	public void testVerySmallLookupPhysicianPatient() {
		Patient expectedPatient = new Patient("Riley", "Nguyen", new UHealthID("HRHR-7654"));
		ArrayList<CurrentPatient> actualPatients = verySmallFacility.lookupByPhysician(9879876);
		assertEquals(expectedPatient, actualPatients.get(0));
	}

	@Test
	public void testVerySmallAddNewPatient() {
		assertTrue(verySmallFacility
				.addPatient(new CurrentPatient("Jane", "Doe", new UHealthID("BBBB-2222"), 1010101, date1)));
	}

	@Test
	public void testVerySmallUpdatePhysician() {
		verySmallFacility.lookupByUHID(uHID1).updatePhysician(9090909);
		CurrentPatient patient = verySmallFacility.lookupByUHID(uHID1);
		assertEquals(9090909, patient.getPhysician());
	}
	
	@Test
	public void testLookupByUHID_Found() {
		// checks and Returns the correct patient when ID exists
	    Facility facility = new Facility();
	    UHealthID id = new UHealthID("HRHR-7654");
	    CurrentPatient riley = new CurrentPatient("Riley", "Nguyen", id, 9879876, date3);
	    facility.addPatient(riley);
	    CurrentPatient lookup = facility.lookupByUHID(new UHealthID("HRHR-7654"));
	    assertNotNull(lookup);
	    assertEquals(riley, lookup); // relies on Patient.equals using UHID correctly
	}
	
	@Test
	public void testLookupByUHIDNotFoundReturnsNull() {
		// checks and Returns null when ID does not exist
	    Facility facility = new Facility();
	    facility.addPatient(new CurrentPatient("Riley", "Nguyen", new UHealthID("HRHR-7654"), 9879876, date3));
	    CurrentPatient lookedUp = facility.lookupByUHID(new UHealthID("ZZZZ-0000"));
	    assertNull(lookedUp);
	}
	// Small facility tests
	// -------------------------------------------------------------------------

	@Test
	public void testSmallGetRecentPatients() {
		ArrayList<CurrentPatient> actual = smallFacility.getRecentPatients(new GregorianCalendar(2020, 0, 0));
		assertEquals(2, actual.size());
	}

	@Test
	public void testAddPatientDuplicateValidator() {
		Facility facility = new Facility();

		CurrentPatient patient1 = new CurrentPatient("Drew", "Hall", uHID2, 3232323, date2);

		// This First add should work
		assertTrue(facility.addPatient(patient1));

		// This Second add (duplicate) should NOT be added
		assertFalse(facility.addPatient(patient1));
	}

	@Test // Makes sure the exception is thrown
	public void testAddPatientNullThrowsException() {
		Facility myFacility = new Facility();

		assertThrows(IllegalArgumentException.class, () -> myFacility.addPatient(null));
	}

	@Test // Test to make sure the exception message displays clearly
	public void testAddPatientNullThrowsExceptionMessage() {
		Facility myFacility = new Facility();

		IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> myFacility.addPatient(null));

		assertEquals("Please enter a patient to add", e.getMessage());
	}



	// Helper methods ------------------------------------------------------------

	/**
	 * A private helper to generate unique UHealthIDs. Valid for up to 260,000 IDs.
	 * 
	 * @param howMany - IDs to make
	 * @return an array of UHealthIDs
	 */
	private UHealthID[] generateUHIDs(int howMany) {
		UHealthID[] ids = new UHealthID[howMany];
		for (int i = 0; i < howMany; i++) {
			String prefix = "JKL" + (char) ('A' + (i / 10000) % 26);
			ids[i] = new UHealthID(prefix + "-" + String.format("%04d", i % 10000));
		}
		return ids;
	}

	/**
	 * A private helper to generate dates.
	 * 
	 * @param howMany - dates to generate
	 * @return an array of dates
	 */
	private GregorianCalendar[] generateDates(int howMany) {
		GregorianCalendar[] dates = new GregorianCalendar[howMany];
		for (int i = 0; i < howMany; i++)
			dates[i] = new GregorianCalendar(2000 + i % 22, i % 12, i % 28);
		return dates;
	}

	/**
	 * A private helper to generate names.
	 * 
	 * @param howMany - names to generate
	 * @return an array of names
	 */
	private String[] generateNames(int howMany) {
		String[] names = new String[howMany];
		Random rng = new Random();
		for (int i = 0; i < howMany; i++)
			names[i] = "" + (char) ('A' + rng.nextInt(26)) + (char) ('a' + rng.nextInt(26))
					+ (char) ('a' + rng.nextInt(26)) + (char) ('a' + rng.nextInt(26));
		return names;
	}

	/**
	 * Adds the patients specified by the input file to a list.
	 * 
	 * Assumes a strict file format: (each line): FirstName LastName ABCD-0123
	 * u0123456 2023 05 16
	 * 
	 * Also assumes there are no duplicate patients in the file.
	 * 
	 * @param filename - full or relative path to file containing patient data
	 */
	public ArrayList<CurrentPatient> readFromFile(String filename) {
		ArrayList<CurrentPatient> patients = new ArrayList<CurrentPatient>();
		try {
			Scanner fileIn = new Scanner(new File(filename));
			int lineNumber = 0;

			while (fileIn.hasNextLine()) {
				String line = fileIn.nextLine();
				lineNumber++;
				patients.add(parsePatient(line, lineNumber));
			}
			fileIn.close();
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage() + "  Patient file couldn't be opened.");
		} catch (ParseException e) {
			System.err.println(e.getLocalizedMessage() + " formatted incorrectly at line " + e.getErrorOffset()
					+ ". Not all patients added to list.");
		}
		return patients;
	}

	@Test
	public void testAddPatientDuplicateValidatorDifferentObjectSameUHID() {
		// checks for Duplicate patient objects (different object however same uHID)
		Facility facility = new Facility();
		CurrentPatient original = new CurrentPatient("Drew", "Hall", uHID2, 3232323, date2);
		CurrentPatient duplicate = new CurrentPatient("Drew", "Hall", new UHealthID("BCBC-2323"), 3232323, date2);

		assertTrue(facility.addPatient(original));
		assertFalse(facility.addPatient(duplicate)); // this should fail because they have the same UHID
	}

	/**
	 * Helper method for parsing the information about a patient from file.
	 * 
	 * @param line       - string to be parsed
	 * @param lineNumber - line number in file, used for error reporting (see above)
	 * @return the Patient constructed from the information
	 * @throws ParseException if file containing line is not properly formatted (see
	 *                        above)
	 */
	private CurrentPatient parsePatient(String line, int lineNumber) throws ParseException {
		Scanner lineIn = new Scanner(line);
		lineIn.useDelimiter(" ");

		if (!lineIn.hasNext()) {
			lineIn.close();
			throw new ParseException("First name", lineNumber);
		}
		String firstName = lineIn.next();

		if (!lineIn.hasNext()) {
			lineIn.close();
			throw new ParseException("Last name", lineNumber);
		}
		String lastName = lineIn.next();

		if (!lineIn.hasNext()) {
			lineIn.close();
			throw new ParseException("UHealth ID", lineNumber);
		}
		String patientIDString = lineIn.next();

		if (!lineIn.hasNext()) {
			lineIn.close();
			throw new ParseException("physician", lineNumber);
		}
		String physicianString = lineIn.next();
		int physician = Integer.parseInt(physicianString.substring(1, 8));

		if (!lineIn.hasNext()) {
			lineIn.close();
			throw new ParseException("year of last visit", lineNumber);
		}
		String yearString = lineIn.next();
		int year = Integer.parseInt(yearString);

		if (!lineIn.hasNext()) {
			lineIn.close();
			throw new ParseException("month of last visit", lineNumber);
		}
		String monthString = lineIn.next();
		int month = Integer.parseInt(monthString);

		if (!lineIn.hasNext()) {
			lineIn.close();
			throw new ParseException("day of last visit", lineNumber);
		}
		String dayString = lineIn.next();
		int day = Integer.parseInt(dayString);

		GregorianCalendar lastVisit = new GregorianCalendar(year, month, day);

		lineIn.close();

		return new CurrentPatient(firstName, lastName, new UHealthID(patientIDString), physician, lastVisit);
	}
}
