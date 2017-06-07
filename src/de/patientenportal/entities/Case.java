package de.patientenportal.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
//import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.CascadeType;
import static javax.persistence.GenerationType.IDENTITY;
import java.util.List;


//import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "Case", catalog = "patientenportal")
public class Case {

	private int caseID;
	private String title;
	private String description;
	private boolean status;
	//Verkn�pfung Rechte
	private List<VitalData> vitaldata;
	//Verkn�pfung Medication
	//Verkn�pfung InstructionMaterial
	//Verkn�pfung MDoc
	//Verkn�pfung Doctor
	//Verkn�pfung Patient
	
	public Case(){	
	}
	
	public Case(String title) {
		this.title = title;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	//@GenericGenerator(name = "user", strategy = "increment")
	//@GeneratedValue(generator = "user")
	@Column(name = "CASE_ID", unique = true, nullable = false)
	public int getCaseID() {
		return caseID;
	}
	public void setCaseID(int caseID) {
		this.caseID = caseID;
	}

	@Column(name = "TITLE", length = 45, nullable = false)
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "DESCRIPTION", length = 200)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "STATUS"/*, nullable = false*/)
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}

	@OneToMany (fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="vitaldata_fk")
	public List<VitalData> getVitaldata() {
		return vitaldata;
	}
	public void setVitaldata(List<VitalData> vitaldata) {
		this.vitaldata = vitaldata;
	}
		
}
