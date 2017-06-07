package de.patientenportal.persistence;

import org.hibernate.Session;
import de.patientenportal.entities.*;

public class RegistrationDAO {

	// User hinzuf�gen
	public static void createUser(User user){
		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();
		session.save(user);
		session.getTransaction().commit();
			
		session.close();
	}

}
