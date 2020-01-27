package com.app.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.pojos.DoctorDetails;
import com.app.pojos.User;
@Service
@Transactional
public class UserDoctorDaoImpl implements IUserDoctorDao 
{

	
	
	@Autowired
	 private SessionFactory sf;
	
	
	@Override
	public User getDoctorById(Integer id) throws Exception
	{
	
		return sf.getCurrentSession().get(User.class,id);
		
	}
	
	@Override
	public String updateDoctor(User u)
	{	
		System.out.println(u.getDrDetails());
		User user= sf.getCurrentSession().get(User.class,u.getUserId());
		System.out.println(user); 
	    user.setName(u.getName());
	    user.setEmail(u.getEmail());
	    user.setPhone(u.getPhone());
	    //user.setUserGender(u.getUserGender());
	    user.setAge(u.getAge());
	    user.setPassword(u.getPassword());
	    user.setDrDeatils(new DoctorDetails(u.getDrDetails().getExpertise(),u.getDrDetails().getExperience(),u.getDrDetails().getDegree(),u.getDrDetails().getHospName(),u.getDrDetails().getHospAddress()));
		
		//sf.getCurrentSession().update(u);
		return "updated";
		
	}
	
//	public String confirmAppt() 
//	{
	
	
	

//	public List<User> getAppointmentList( Integer id)
//	{
//		String jqpl="select a from Appointment a join fetch a.drid";
//		sf.getCurrentSession().createQuery(A)
//		
//		
//		return null;
//		
//	}
	
	
}
