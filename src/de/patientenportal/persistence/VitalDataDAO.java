package de.patientenportal.persistence;

import org.hibernate.Session;

import de.patientenportal.entities.VitalData;


public class VitalDataDAO {
	
	// VitalDaten hinzuf�gen // Da das �ber den Case l�uft ist die Methode wsl. unn�tig
	public static void add(VitalData vitalData) {
		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();
		session.save(vitalData);
		session.getTransaction().commit();
		
		session.close();
	}
}

