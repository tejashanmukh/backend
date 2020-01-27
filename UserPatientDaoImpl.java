package com.app.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.pojos.DoctorDetails;
import com.app.pojos.PatientDetails;
import com.app.pojos.User;

@Service
@Transactional
public class UserPatientDaoImpl implements IUserPatientDao
{

	@Autowired
	 private SessionFactory sf;
	
	
	@Override
	public User getPatientById(Integer id) throws Exception
	{
		return sf.getCurrentSession().get(User.class,id);
		
	}
	

	@Override
	public String updatePatient(User u)throws Exception
	{	
		System.out.println("hii"+u);
	 System.out.println(u.getPtDetails());
		User user= sf.getCurrentSession().get(User.class,u.getUserId());
	    System.out.println(user);
		user.setName(u.getName());
	    user.setEmail(u.getEmail());
	    user.setPhone(u.getPhone());
	   // user.setUserGender(u.getUserGender());
	    user.setAge(u.getAge());
	    user.setPassword(u.getPassword());
	    user.setPtDeatails(new PatientDetails(u.getPtDetails().getPtWeight(),u.getPtDetails().getBloodgroup(),u.getPtDetails().getHeight(),u.getPtDetails().getBodyMassIndex()));
		//sf.getCurrentSession().update(u);
		return "updated";
		
	}
	
	
	@Override
	public List<User> getDoctorDetails()throws Exception
	{
		String jqpl="select u from User u where u.userType='DOCTOR'";
	   return sf.getCurrentSession().createQuery(jqpl,User.class).getResultList();
		
	}
	
	@Override
	public List<User> getDoctorDetailsBy(String city,String expertise)throws Exception
	{
		String jqpl="select u from User u where u.userType='DOCTOR' and u.drDetails.hospAddress=:ct and u.drDetails.expertise=:ex";
		//String jqpl="select u from User u left join fetch u.drDetails a where u.userType='DOCTOR' and a.hospAddress=:ct and a.expertise=:ex";
		return sf.getCurrentSession().createQuery(jqpl,User.class).setParameter("ct",city).setParameter("ex",expertise).getResultList();
		
	}
	@Override
	public List<User> getDrdetailsById(int did)throws Exception
	{
		System.out.println("hey"+did);
		String jpql="select u from User u where u.drDetails.doctorId=:id";
		
		
		return sf.getCurrentSession().createQuery(jpql,User.class).setParameter("id",did).getResultList();
	}

	
	
	
	
	
	
	
	
}
