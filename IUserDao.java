package com.app.dao;

import java.util.List;

import com.app.pojos.DoctorDetails;
import com.app.pojos.PatientDetails;
import com.app.pojos.User;
import com.app.pojos.feedback;

public interface IUserDao
{
    public Integer registerUser(User user) throws Exception;
    public User authenticateUser(User user) throws Exception;
	public String drDetails(Integer id ,DoctorDetails user) throws Exception;
	public String ptDetails(Integer id, PatientDetails user) throws Exception;
	public User findByEmail(String email) throws Exception;
	
	void setPass(String pass, String email) throws Exception;
	public void updatePassword(User userPojo) throws Exception;
	User viewProfile(Integer id) throws Exception;
	//feedback
	public List<feedback> displayfeedback(Integer id);
	public String patientfeedback(Integer id,feedback f);
}
