package de.patientenportal.persistence;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/*
 * Funktioniert gerade nicht, alle Tests m�ssen vollst�ndig aufr�umen, damit das klappt
 */


@RunWith(Suite.class)
@SuiteClasses({
		UserCRUDTest.class,
		CaseCRUDTest.class,
		PatientRelativeCRUDTest.class,
		DoctorOfficeCRUDTest.class,
		RightsTest.class,
		AccessTest.class})

public class AllTests {

}
