package de.patientenportal.persistence;

//import org.hibernate.HibernateException;
//import javax.transaction.Transactional;
import java.util.List;
import org.hibernate.Session;
import de.patientenportal.entities.*;

//@Transactional
public class UserDAO {
	
	// User abrufen
	public static User getUser(int user_id){
		Session session = HibernateUtil.getSessionFactory().openSession();
		User user = new User();
			
		session.beginTransaction();		
		user = (User)session.get(User.class, user_id);
		session.getTransaction().commit();

		session.close();
		
		return user;
	}
	
	// Userdaten �ndern
	public static String updateUser(User updateduser){
		int id = updateduser.getUserId();
		if(id!=0){
			
			String username = updateduser.getUsername();
			String password = updateduser.getPassword();
			String email = updateduser.getEmail();
			String lastname = updateduser.getLastname();
			String firstname = updateduser.getFirstname();
			String birthdate = updateduser.getBirthdate();
			String gender = updateduser.getGender();

			System.out.println("Updating User /w ID "+ id +" ... Please calm your tits ...");
			
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();				
			User usertoupdate = session.get(User.class, id);
				
				if(username!=null)	{usertoupdate.setUsername(username);}
				if(password!=null)	{usertoupdate.setPassword(password);}
				if(email!=null)		{usertoupdate.setEmail(email);}
				if(lastname!=null)	{usertoupdate.setLastname(lastname);}
				if(firstname!=null)	{usertoupdate.setFirstname(firstname);}				
				if(birthdate!=null)	{usertoupdate.setBirthdate(birthdate);}				
				if(gender!=null)	{usertoupdate.setGender(gender);}
							
			session.getTransaction().commit();
			session.close();
			return "success";
		}
		else {
			return "noID";
		}
	}		
	
	// User l�schen
	public static String deleteUser(int user_id){
		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();
		User user = (User)session.get(User.class, user_id);
		session.delete(user);
		session.getTransaction().commit();
		
		session.close();
		return "success";
	}
	
	// Alle User ausgeben -- nur testweise hier drin, kann in ein AdminDAO
	@SuppressWarnings("unchecked")
	public static List<User> getAllUsers(){
		Session session = HibernateUtil.getSessionFactory().openSession();
	    	 
		session.beginTransaction();
	    List<User> users = session.createQuery("FROM User").list(); 
	    session.getTransaction().commit();				
	    session.close();
	
	    return users;
	}
	
}