package de.patientenportal.persistence;

import org.hibernate.HibernateException;
import java.util.List;
import org.hibernate.Session;
import de.patientenportal.entities.User;
import de.patientenportal.persistence.HibernateUtil;

public class UserDAO {

	// User hinzuf�gen
	public static void add(User user) throws Exception{
		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();
		session.save(user);
		session.getTransaction().commit();
		
		session.close();
	}

	// User l�schen
	public static void delete(int user_id) throws Exception{
		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();
		User user = (User)session.get(User.class, user_id);
		session.delete(user);
		session.getTransaction().commit();
		
		session.close();
	}
	
	// User �ber ID finden
	public static User getUser(int user_id) throws Exception{
		Session session = HibernateUtil.getSessionFactory().openSession();
		User user = new User();
			
		session.beginTransaction();		
		user = (User)session.get(User.class, user_id);
		session.getTransaction().commit();

		session.close();

		return user;
	}
	

	public static void update(User updateduser) throws Exception{
		int id = updateduser.getUserId();
		if(id!=0){
			
			Session session = HibernateUtil.getSessionFactory().openSession();
			String username = updateduser.getUsername();
			String password = updateduser.getPassword();
			String email = updateduser.getEmail();
			String lastname = updateduser.getLastname();
			String firstname = updateduser.getFirstname();
			
			System.out.println("User mit Id "+id+" wird ge�ndert...Please calm your tits");
			
			session.beginTransaction();
						
			User usertoupdate = session.get(User.class, id);
										
			if(updateduser.equals(usertoupdate) == true){				//funktioniert noch nicht
				System.out.println("Keine Ver�nderung der Daten.");
			} else {
				
				if(username!=null && !(username.equals(usertoupdate.getUsername()))){
					usertoupdate.setUsername(username);
					System.out.println("Username ge�ndert zu: " + username);}
			
				if(email!=null && !(password.equals(usertoupdate.getPassword()))){
					usertoupdate.setEmail(email);
					System.out.println("Email ge�ndert zu:    " + email);}
			
				if(password!=null && !(email.equals(usertoupdate.getEmail()))){
					usertoupdate.setPassword(password);
					System.out.println("Passwort ge�ndert zu: ******");}
				
				if(lastname!=null && !(lastname.equals(usertoupdate.getLastname()))){
					usertoupdate.setLastname(lastname);
					System.out.println("Nachname ge�ndert zu: " + lastname);}
				
				if(firstname!=null && !(firstname.equals(usertoupdate.getFirstname()))){
					usertoupdate.setFirstname(firstname);
					System.out.println("Vorname ge�ndert zu:  " + firstname);}
			
			session.getTransaction().commit();
			session.close();
			System.out.println("... Daten erfolgreich ge�ndert.");
			}
		}
		else {
			System.out.println("Keine ID angegeben.");
		}

	}		
	
	
	public static List<User> getAllUsers(){
		Session session = HibernateUtil.getSessionFactory().openSession();
	    	 
		session.beginTransaction();
	    List<User> users = session.createQuery("FROM User").list(); 
	    session.getTransaction().commit();				
	    session.close();
	
	    return users;
	}
	

}