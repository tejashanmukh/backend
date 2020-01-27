package com.app.dao;

import com.app.pojos.User;

public interface IUserDoctorDao {

	User getDoctorById(Integer id) throws Exception;

	String updateDoctor(User u) throws Exception;

}
