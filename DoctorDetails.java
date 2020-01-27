package com.app.pojos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class DoctorDetails
{
  private Integer doctorId;
  private String expertise;
  private String experience;
  private String degree; 
  private String hospName;	
  private String hospAddress;
  @JsonIgnore
  private  List<Appointment> appt;
  @JsonBackReference 
  private User user;
  private List<PatientDetails>pt;
  @JsonIgnore
private List<feedback>fdb=new ArrayList<>();
  
  //@JsonBackReference
  //private HospitalDetails hosp;
  
  
  public DoctorDetails() 
  {
	  System.out.println("Inside DoctorDetails");
  }
  
  public DoctorDetails(Integer doctorId) {
		super();
		this.doctorId = doctorId;
	}


  
public DoctorDetails(String expertise, String experience, String degree, String hospName, String hospAddress) {
	super();
	this.expertise = expertise;
	this.experience = experience;
	this.degree = degree;
	this.hospName = hospName;
	this.hospAddress = hospAddress;
	
}


public DoctorDetails(String expertise, String hospAddress) {
	super();
	this.expertise = expertise;
	this.hospAddress = hospAddress;
}



public DoctorDetails(Integer doctorId, String expertise, String experience, String degree, String hospName,
		String hospAddress) {
	super();
	this.doctorId = doctorId;
	this.expertise = expertise;
	this.experience = experience;
	this.degree = degree;
	this.hospName = hospName;
	this.hospAddress = hospAddress;

}



//public DoctorDetails(Integer doctorId, String expertise, String experience, String degree)
//{
//	super();
//	this.doctorId = doctorId;
//	this.expertise = expertise;
//	this.experience = experience;
//	this.degree = degree;
//}
//
//public DoctorDetails(String expertise, String experience, String degree) {
//	super();
//	this.expertise = expertise;
//	this.experience = experience;
//	this.degree = degree;
//}
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
public Integer getDoctorId() {
	return doctorId;
}
public void setDoctorId(Integer doctorId) {
	this.doctorId = doctorId;
}
public String getExpertise() {
	return expertise;
}
public void setExpertise(String expertise) {
	this.expertise = expertise;
}
public String getExperience() {
	return experience;
}
public void setExperience(String experience) {
	this.experience = experience;
}
public String getDegree() {
	return degree;
}
public void setDegree(String degree) {
	this.degree = degree;
}

@OneToOne
@JoinColumn(name = "DId")
public User getUser() {
	return user;
}
public void setUser(User user) {
	this.user = user;
}


public String getHospName() {
	return hospName;
}


public void setHospName(String hospName) {
	this.hospName = hospName;
}


public String getHospAddress() {
	return hospAddress;
}


public void setHospAddress(String hospAddress) {
	this.hospAddress = hospAddress;
}



@Override
public String toString() {
	return "DoctorDetails [doctorId=" + doctorId + ", expertise=" + expertise + ", experience=" + experience
			+ ", degree=" + degree + ", hospName=" + hospName + ", hospAddress=" + hospAddress + "]";
}

//dealing with appointment
@OneToMany(mappedBy = "drId",cascade = CascadeType.ALL,orphanRemoval = true)
public List<Appointment> getAppt() {
	return appt;
}



public void setAppt(List<Appointment> appt) {
	this.appt = appt;
}

//dealing with feedback
@OneToMany(mappedBy = "dtd",cascade = CascadeType.ALL,orphanRemoval =true)
public List<feedback> getFdb() {
	return fdb;
}

public void setFdb(List<feedback> fdb) {
	this.fdb = fdb;
}

//convienece method 
public void fbdt(feedback f)
{
	fdb.add(f);
	f.setDtd(this);
	
}
public void fbdtrem(feedback f)
{
	fdb.remove(f);
	f.setDtd(null);
	
}







//@ManyToOne
//@JoinColumn(name = "HId")
//public HospitalDetails getHosp() {
//	return hosp;
//}
//
//public void setHosp(HospitalDetails hosp) {
//	this.hosp = hosp;
//}


//public void setDrHosp(HospitalDetails hsdt) {
//	this.hosp=hsdt;
//	hsdt.getDrtails().add(this);
//}	
//	





}
