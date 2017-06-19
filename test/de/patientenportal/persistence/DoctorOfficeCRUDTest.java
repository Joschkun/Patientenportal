package de.patientenportal.persistence;

import org.junit.Test;
import java.util.List;
import org.junit.Assert;
import de.patientenportal.entities.*;

public class DoctorOfficeCRUDTest {
	
	@Test
	public void main(){
		
		// Office anlegen
		Office neuO = new Office();
			neuO.setName("Zahnarztpraxis");
			
		Address addressOffice = new Address();
		addressOffice.setCity("Officecity");
		addressOffice.setNumber("3A");
		addressOffice.setPostalCode("19230");
		addressOffice.setStreet("Officestra�e");
			
			neuO.setAddress(addressOffice);
	
		Contact officeContact = new Contact();
			officeContact.setEmail("officemail.dentist@googlemail.com");
			officeContact.setPhone("0123456789");
			officeContact.setMobile("112");
		
			neuO.setContact(officeContact);
			
		String feedbackCO = OfficeDAO.createOffice(neuO);
			Assert.assertEquals("success", feedbackCO);
		
		// Office abrufen
		Office newoffice = OfficeDAO.getOffice(1);
			Assert.assertEquals("Zahnarztpraxis", newoffice.getName());
			Assert.assertEquals("officemail.dentist@googlemail.com", newoffice.getContact().getEmail());
			Assert.assertEquals("0123456789", newoffice.getContact().getPhone());
			Assert.assertEquals("112", newoffice.getContact().getMobile());
			Assert.assertEquals("Officestra�e", newoffice.getAddress().getStreet());
		
		// Doktoren anlegen Office hinterlegen
		Doctor neuD1 = new Doctor();
			neuD1.setSpecialization("Zahnarzt");
			neuD1.setOffice(newoffice);
		Doctor neuD2 = new Doctor();
			neuD2.setSpecialization("Kieferorthop�de");
			neuD2.setOffice(newoffice);
			
		String feedbackCD1 = RegistrationDAO.createDoctor(neuD1);
		String feedbackCD2 = RegistrationDAO.createDoctor(neuD2);
			Assert.assertEquals("success", feedbackCD1);
			Assert.assertEquals("success", feedbackCD2);
		
		//Doktor und Office �ber Doktor (bidirektional) abrufen
		Doctor D1 = DoctorDAO.getDoctor(1);
			Assert.assertEquals("Zahnarzt", D1.getSpecialization());
			Assert.assertEquals("Zahnarztpraxis", D1.getOffice().getName());
			Assert.assertEquals("0123456789", D1.getOffice().getContact().getPhone());
		
		// Dem Office die Doktoren hinzuf�gen (andere Zugriffsrichtung) // Office-Update
		Doctor D2 = DoctorDAO.getDoctor(2);
		
		Office emptyoffice = OfficeDAO.getOffice(1);
			List<Doctor> doctors = emptyoffice.getDoctors();
				doctors.add(D1);
				doctors.add(D2);
			emptyoffice.setName("Zaaahnarztpraxis");
		String feedbackUO = OfficeDAO.updateOffice(emptyoffice);
			Assert.assertEquals("success", feedbackUO);
					
		//Doktoren und ge�nderte Daten abrufen
		Office fulloffice = OfficeDAO.getOffice(1);
				Assert.assertEquals("Zaaahnarztpraxis", fulloffice.getName());
			List<Doctor> fulldoctors = fulloffice.getDoctors();
				Assert.assertEquals("Zahnarzt", fulldoctors.get(0).getSpecialization());
				Assert.assertEquals("Kieferorthop�de", fulldoctors.get(1).getSpecialization());
		
/*		// Zus�tzlicher Test - �ber Office Arzt �ndern (wie reagiert die DB)	
		fulldoctors.get(1).setSpecialization("Test");
		OfficeDAO.updateOffice(fulloffice);
			Assert.assertEquals("Test", DoctorDAO.getDoctor(2).getSpecialization());*/

		// Nachtr�glicher Userinput-Test (unabh�ngige Doktor-Entity bis zur Registrierung)
		User newuser = new User();
			newuser.setUsername("newuser");
			newuser.setPassword("newpass");
			newuser.setFirstname("New");
			newuser.setLastname("User");
			newuser.setDoctor(D1);
	
			D1.setUser(newuser);
		
		String feedbackCU  = RegistrationDAO.createUser(newuser);
		String feedbackUD = DoctorDAO.updateDoctor(D1);
			Assert.assertEquals("success", feedbackCU);
			Assert.assertEquals("success", feedbackUD);
		
		// Bidirektionaler Zugriff nach nachtr�glicher Verkn�pfung
		Doctor dx = DoctorDAO.getDoctor(1);
		User ux = UserDAO.getUser(1);
			Assert.assertEquals("New", dx.getUser().getFirstname());
			Assert.assertEquals("Zahnarzt", ux.getDoctor().getSpecialization());
			
		//DeleteDoctor-Test
		//Info - R�ckw�rtskaskadierung ist hier nicht eingestellt! Muss auch so sein
		//Deswegen muss die Doktor-User-Verkn�fung entfernt werden, bevor man den Doktor 1 l�schen kann
		
		Doctor doc1 = DoctorDAO.getDoctor(1);
			doc1.setUser(null);
		DoctorDAO.updateDoctor(doc1);
		DoctorDAO.deleteDoktor(1);
				
		//DeleteOffice-Test
		//Info - auch hier keine Kaskadierung vom Office, die Verkn�pfung des anderen Doktors zum Office muss/sollte entfernt werden
		
		Doctor d2 = DoctorDAO.getDoctor(2);
			d2.setOffice(null);
		DoctorDAO.updateDoctor(d2);
			
		String feedbackDO = OfficeDAO.deleteOffice(1);
		Office deletedO = OfficeDAO.getOffice(1);
			Assert.assertEquals("success",feedbackDO);
			Assert.assertEquals(null, deletedO);
			
	}	
}
