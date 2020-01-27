package com.app.dao;

import java.util.List;

import com.app.pojos.Appointment;
import com.app.pojos.PatientDetails;
import com.app.pojos.User;

public interface IAppointmentDao {

	Integer bookAppointment(Appointment app,Integer id)throws Exception;

	String ConfirmAppt(Integer Appid) throws Exception;

	List<Appointment> getAppDetails(Integer did)throws Exception;

	String DeclineAppt(Integer Appid) throws Exception;

	User getPtDetails(Integer pid) throws Exception;

	List<Appointment> getAppdetail(Integer id);

}
