package de.patientenportal.persistence;

import org.junit.Test;
import de.patientenportal.entities.*;

public class Test1 {
	
		@Test
		public void main(){
			
			User user1 = new User();
			 user1.setUsername("M��p");
			 user1.setPassword("12345");
			 user1.setFirstname("Test 1");
			 user1.setLastname("Test 23");
			
		RegistrationDAO.createUser(user1);
			 
		}
		
}
