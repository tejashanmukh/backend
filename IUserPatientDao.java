package com.app.dao;

import java.util.List;

import com.app.pojos.User;

public interface IUserPatientDao {

	
	String updatePatient(User u) throws Exception;

	User getPatientById(Integer id) throws Exception;

	List<User> getDoctorDetails() throws Exception;

	List<User> getDoctorDetailsBy(String city, String expertise) throws Exception;

	List<User> getDrdetailsById(int did) throws Exception;



}
