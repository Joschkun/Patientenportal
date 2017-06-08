package de.patientenportal.persistence;

import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import de.patientenportal.entities.Office;
import de.patientenportal.entities.Doctor;

public class OfficeDAO {

	// Office abrufen
	public static Office getOffice(int officeID){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Office office = new Office();
		
		session.beginTransaction();
		office = (Office)session.get(Office.class, officeID);	
		
		if (office != null){
		Hibernate.initialize(office.getDoctors());			// LAZY-HIBERNATE-MAGIC
		}
		session.getTransaction().commit();
				
		session.close();
		return office;		
	}
	
	// Officedaten �ndern
	public static String updateOffice(Office updatedoffice){
		int id = updatedoffice.getOfficeID();
		if(id!=0){
				
			String name = updatedoffice.getName();
			List<Doctor> doctors = updatedoffice.getDoctors();

			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();				
			Office officetoupdate = session.get(Office.class, id);
					
				if(name!=null)		{officetoupdate.setName(name);}
				if(doctors!=null)	{officetoupdate.setDoctors(doctors);}
			
			session.getTransaction().commit();
			session.close();
			return "success";
		}
		else {
			return "noID";
		}
	}
	
	
	// Office hinzuf�gen
	public static String createOffice(Office office){
		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();
		session.save(office);
		session.getTransaction().commit();
				
		session.close();
		return "success";
	}
	
	
	
	
}
