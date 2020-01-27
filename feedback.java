package com.app.pojos;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
public class feedback 
{
private Integer fid;
private String feedback;
private PatientDetails ptd;//patientdetails object
private DoctorDetails dtd;//doctor details object
private Integer did;
public feedback() {
	System.out.println("inside feedback empty constructor");
}
public feedback(String feedback) {
	super();
	this.feedback = feedback;
}


public feedback(String feedback, DoctorDetails dtd)
{
	super();
	this.feedback = feedback;
	this.dtd = dtd;
}
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY )
public Integer getFid() {
	return fid;
}
public void setFid(Integer fid) {
	this.fid = fid;
}
public String getFeedback() {
	return feedback;
}
public void setFeedback(String feedback) {
	this.feedback = feedback;
}
@ManyToOne
@JoinColumn(name = "FPid")//fpid=feedbackpatientid
public PatientDetails getPtd() {
	return ptd;
}
public void setPtd(PatientDetails ptd) {
	this.ptd = ptd;
}
@ManyToOne
@JoinColumn(name = "FDid")//FDID=feedbackdoctorid
public DoctorDetails getDtd() {
	return dtd;
}
@Transient
public Integer getDid() {
	return did;
}
public void setDid(Integer did) {
	this.did = did;
}
public void setDtd(DoctorDetails dtd) {
	this.dtd = dtd;
}
@Override
public String toString() {
	return "feedback [fid=" + fid + ", feedback=" + feedback + "]";
}


}
