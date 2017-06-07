package de.patientenportal.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import static javax.persistence.GenerationType.IDENTITY;
//import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name = "Patient", catalog = "patientenportal")
public class Patient {

	private int patientID;
	private String bloodtype;
	private User user;
	//private Insurance insurance;			Verkn�pfung fehlt noch
	//private List <Relative> relatives;	Verkn�pfung fehlt noch
	//private List <Case> cases;			Verkn�pfung fehlt noch
	//private List <MDoc> Mdoc;				Verkn�pfung fehlt noch
	//R�ckverkn�pfung zum User			 	Verkn�pfung fehlt noch
	
	public Patient() {
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	//@GenericGenerator(name = "patient", strategy = "increment")
	//@GeneratedValue(generator = "patient")
	
	@Column(name = "PATIENT_ID", unique = true, nullable = false)
	public int getPatientID() {
		return patientID;
	}

	public void setPatientID(int patientID) {
		this.patientID = patientID;
	}

	@Column(name = "BLOODTYPE", length = 3)
	public String getBloodtype() {
		return bloodtype;
	}

	public void setBloodtype(String bloodtype) {
		this.bloodtype = bloodtype;
	}

	@OneToOne(targetEntity = User.class)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}