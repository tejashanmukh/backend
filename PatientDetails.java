package com.app.pojos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
public class PatientDetails 
{
    private Integer patientId;
    private Double ptWeight;
    private String bloodgroup;
    private Double height;
    private Double bodyMassIndex;
    @JsonBackReference 
    private User user;
    
    @JsonIgnore
    private List<Appointment> app=new ArrayList<>();
    @JsonIgnore
    private List<feedback>fb=new ArrayList<>();
//    @JsonBackReference
//    private List<Appointment> app=new ArrayList<>();
    public PatientDetails()
    {
		System.out.println("PatientDetails");
	}
    
    

	public PatientDetails(Integer patientId) {
		super();
		this.patientId = patientId;
	}



	public PatientDetails(Double ptWeight, String bloodgroup, Double height, Double bodyMassIndex) {
		super();
		this.ptWeight = ptWeight;
		this.bloodgroup = bloodgroup;
		this.height = height;
		this.bodyMassIndex = bodyMassIndex;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public Double getPtWeight() {
		return ptWeight;
	}

	public void setPtWeight(Double ptWeight) {
		this.ptWeight = ptWeight;
	}

	public String getBloodgroup() {
		return bloodgroup;
	}

	public void setBloodgroup(String bloodgroup) {
		this.bloodgroup = bloodgroup;
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public Double getBodyMassIndex() {
		return bodyMassIndex;
	}

	public void setBodyMassIndex(Double bodyMassIndex) {
		this.bodyMassIndex = bodyMassIndex;
	}


//mapping with user
	@OneToOne
	@JoinColumn(name = "PId")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "patient [patientId=" + patientId + ", ptWeight=" + ptWeight + ", bloodgroup=" + bloodgroup + ", height="
				+ height + ", bodyMassIndex=" + bodyMassIndex + "]";
	}

//mapping with appointment	
	@OneToMany(mappedBy = "ptId",cascade = CascadeType.ALL,orphanRemoval = true)
	public List<Appointment> getApp() {
		return app;
	}

	public void setApp(List<Appointment> app) {
		this.app = app;
	}

//helper method for appointment
	public void apptPt(Appointment appt)
	{
             app.add(appt);
             appt.setPtId(this);
	}
	
	

	public void apptptRem(Appointment appt)
	{
             app.remove(appt);
             appt.setPtId(null);
	}
	
    
	//dealing with feedback
	
	@OneToMany(mappedBy = "ptd",cascade = CascadeType.ALL,orphanRemoval =true)
	public List<feedback> getFb() {
		return fb;
	}


	public void setFb(List<feedback> fb) {
		this.fb = fb;
	}
	//convience method for adding feedback
	public void fbpt(feedback f)
	{
		fb.add(f);
		f.setPtd(this);	
	}
	public void fbptrem(feedback f)
	{
		fb.remove(f);
		f.setPtd(null);
	}
	
	
	
}
