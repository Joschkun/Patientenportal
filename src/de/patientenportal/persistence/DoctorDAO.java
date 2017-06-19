package de.patientenportal.persistence;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import de.patientenportal.entities.Doctor;
import de.patientenportal.entities.Office;
import de.patientenportal.entities.User;
public class DoctorDAO {
	
	// Doktor abrufen
	public static Doctor getDoctor(int doctorID){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Doctor doctor = new Doctor();
				
		session.beginTransaction();		
		doctor = (Doctor)session.get(Doctor.class, doctorID);
		
		if (doctor != null){
			Hibernate.initialize(doctor.getOffice());			// LAZY-HIBERNATE-MAGIC
			}
		session.getTransaction().commit();
	
		session.close();
			
		return doctor;
	}	
	
	// Doktordaten �ndern
	public static String updateDoctor(Doctor updateddoctor){
		int id = updateddoctor.getDoctorID();
		if(id!=0){
					
			String specialization = updateddoctor.getSpecialization();
			User user = updateddoctor.getUser();
			Office office = updateddoctor.getOffice();

			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();				
			Doctor doctortoupdate = session.get(Doctor.class, id);
						
				doctortoupdate.setSpecialization(specialization);
				doctortoupdate.setUser(user);
				doctortoupdate.setOffice(office);

			session.getTransaction().commit();
			session.close();
			return "success";
		}
		else {
			return "noID";
		}
	}
	//Doktordaten l�schen
	//Info - R�ckw�rtskaskadierung ist nicht bei allen Verkn�pfungen eingestellt
	//Bevor der Eintrag gel�scht werden kann, m�ssen diese Verkn�fungen entfernt werden
	
	public static String deleteDoctor(int doctor_id){
		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();
		Doctor doctor = (Doctor)session.get(Doctor.class, doctor_id);
		session.delete(doctor);
		session.getTransaction().commit();
		
		session.close();
		return "success";
	}
	
}
