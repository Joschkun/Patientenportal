package de.patientenportal.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "Relative", catalog = "patientenportal")
public class Relative {
	
	private int relativeID;
	//Verkn�pfung zu private User user
	//Verkn�pfung zu private Patient patient
	
	
	public Relative(){
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "RELATIVE_ID", unique = true, nullable = false)
	public int getRelativeID() {
		return relativeID;
	}

	public void setRelativeID(int relativeID) {
		this.relativeID = relativeID;
	}








}
