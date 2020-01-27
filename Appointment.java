package com.app.pojos;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;


import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;





@Entity
public class Appointment 
{
      private Integer appId;
    
      //@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd-MM-yyyy")
      //@JsonFormat(pattern = "yyyy-MM-dd")
      @DateTimeFormat( pattern = "yyyy:MM:dd")
      private Date appDate;
     
      //@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "HH-mm-ss")
      //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
      //@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")     
      //@DateTimeFormat( pattern = "hh:mm")
      private String appTime;
      private String appStatus;
  
      private DoctorDetails drId;
   
      private PatientDetails ptId;
     // private User user;
           
    public Appointment()
      {
		System.out.println("Inside Appointment");
	   }
    
    
      
//	public Appointment(Date appDate, String appTime) {
//		super();
//		this.appDate = appDate;
//		this.appTime = appTime;
//	}






	public Appointment(Date appDate, String appTime, String appStatus)
	{
		super();
		this.appDate = appDate;
		this.appTime = appTime;
		this.appStatus = appStatus;
	}
	

public Appointment(Date appDate, String appTime, String appStatus, DoctorDetails drId) {
	super();
	this.appDate = appDate;
	this.appTime = appTime;
	this.appStatus = appStatus;
	this.drId = drId;
//	this.user = user;
}



//	public Appointment(Integer appId, Date appDate, String appTime, String appStatus, DoctorDetails drId,
//			PatientDetails ptId) {
//		super();
//		this.appId = appId;
//		this.appDate = appDate;
//		this.appTime = appTime;
//		this.appStatus = appStatus;
//		
//	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getAppId() {
		return appId;
	}

	public void setAppId(Integer appId) {
		this.appId = appId;
	}

	@Temporal(TemporalType.DATE)
	public Date getAppDate() {
		return appDate;
	}

	public void setAppDate(Date appDate) {
		this.appDate = appDate;
	}
	
   
	public String getAppTime(){
		return appTime;
	}

	public void setAppTime(String appTime) {
		this.appTime = appTime;
	}

	public String getAppStatus() {
		return appStatus;
	}

	public void setAppStatus(String appStatus) {
		this.appStatus = appStatus;
	}

	@Override
	public String toString() {
		return "Appointment [appId=" + appId + ", appDate=" + appDate + ", appTime=" + appTime + ", appStatus="
				+ appStatus + "]";
	}

	@ManyToOne
	@JoinColumn(name = "Did")
	public DoctorDetails getDrId() 
	{
		return drId;
	}

	public void setDrId(DoctorDetails drId) {
		this.drId = drId;
	}

	@ManyToOne
	@JoinColumn(name = "pid")
	public PatientDetails getPtId()
	{
		return ptId;
	}

	public void setPtId (PatientDetails ptId)
	{
		this.ptId = ptId;
	}

	//conv for drDetails
	public void setAppt(DoctorDetails drdt)
	{
		this.drId=drdt;
		drdt.getAppt().add(this);
	}
	
	
	//conv ptDetails
	public void setApptPt(PatientDetails ptdt)
	{
	
		this.ptId=ptdt;
		ptdt.getApp().add(this);
	}

//
//@Transient
//	public User getUser() {
//		return user;
//	}
//
//
//
//	public void setUser(User user) {
//		this.user = user;
//	}
	
	//public void setDrHosp(HospitalDetails hsdt) {
//	this.hosp=hsdt;
//	hsdt.getDrtails().add(this);
//}	

	
}
