package com.app.dao;

import java.security.MessageDigest;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.pojos.DoctorDetails;
import com.app.pojos.PatientDetails;
import com.app.pojos.User;
import com.app.pojos.feedback;

@Service
@Transactional
public class UserDaoImpl implements IUserDao 
{

	@Autowired
	public SessionFactory sf;
	
	//User Registeration
	//User Registeration
		@Override
		public Integer registerUser(User user) throws Exception
		{	
			
			  
		      try {
				//Creating the MessageDigest object  
				  MessageDigest md = MessageDigest.getInstance("SHA-256");

				  //Passing data to the created MessageDigest Object
				  md.update(user.getPassword().getBytes());
				  
				  //Compute the message digest
				  byte[] digest = md.digest();      
				  System.out.println(digest);  
				 
				  //Converting the byte array in to HexString format
				  StringBuffer hexString = new StringBuffer();
				  
				  for (int i = 0;i<digest.length;i++) {
				     hexString.append(Integer.toHexString(0xFF & digest[i]));
				  }
				System.out.println("encry pass"+hexString.toString());
				user.setPassword(hexString.toString());
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
		  	return (Integer) sf.getCurrentSession().save(user);
		}

		//User Login
	  	@Override
		public User authenticateUser(User user) throws Exception
	  	{
	  		StringBuffer hexString=null;
	  		MessageDigest md;
		      try {
				//Creating the MessageDigest object  
				  md = MessageDigest.getInstance("SHA-256");

				  //Passing data to the created MessageDigest Object
				  md.update(user.getPassword().getBytes());
				  
				  //Compute the message digest
				  byte[] digest = md.digest();      
				  System.out.println(digest);  
				 
				  //Converting the byte array in to HexString format
				  hexString = new StringBuffer();
				  
				  for (int i = 0;i<digest.length;i++) {
				     hexString.append(Integer.toHexString(0xFF & digest[i]));
				  }
				System.out.println("encry pass"+hexString.toString());
			
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
	  		
	  		
			String jpql="select u from User u where u.email=:em and u.password=:pass";
			User u=sf.getCurrentSession().createQuery(jpql,User.class).setParameter("em",user.getEmail()).setParameter("pass",hexString.toString()).getSingleResult();
		    System.out.println(u);
			return u;
		}

  	//Add Doctor Details
	@Override
	public String drDetails(Integer id, DoctorDetails user) throws Exception
	{
		 System.out.println("inside dao");
		 System.out.println(id);
		 
		String jpql="select u from User u where u.userId=:nm ";
		User uv=sf.getCurrentSession().createQuery(jpql,User.class).setParameter("nm",id).getSingleResult();
		 
		  System.out.println(uv);
		  System.out.println(user);
		
		  uv.setDrDeatils(user);	 
		
		  return "added";
           
	}
	
	
	
	
	
//	@Override
//	public String drDetails(Integer id, DoctorDetails user)
//	{
//		 System.out.println("inside dao");
//		  System.out.println(user.getHosp());
//		System.out.println(id);
//		String jpql="select u from User u where u.userId=:nm ";
//		  User uv=sf.getCurrentSession().createQuery(jpql,User.class).setParameter("nm",id).getSingleResult();
//		  System.out.println(uv);
//		  System.out.println(user);
//		  System.out.println("inside dao");
//		  System.out.println(user.getHosp());
//		  uv.setDrDeatils(user);	 
//		  uv.getDrDetails().setDrHosp(uv.getDrDetails().getHosp());
//		return "added";
//           
//	}
	
   //Add Patient Details
	@Override
	public String ptDetails(Integer id, PatientDetails user)
	{
		System.out.println(id);
		String jpql="select u from User u where u.userId=:nm ";
		  User uv=sf.getCurrentSession().createQuery(jpql,User.class).setParameter("nm",id).getSingleResult();
		  System.out.println(uv);
		  System.out.println(user);
		  uv.setPtDeatails(user);	 
		return "added";
           
	}
	
   
	@Override
	public User findByEmail(String email)
	{
		String str = "select u from User u where u.email=:em";
		return sf.getCurrentSession().createQuery(str,User.class).setParameter("em",email).
				getSingleResult();
	}

    //Set Password
	@Override
	public void setPass(String pass, String email)
	{
		System.out.println("passs   "+pass);
		System.out.println("email   "+email);
  		StringBuffer hexString=null;
  		MessageDigest md;
	      try {
			//Creating the MessageDigest object  
			  md = MessageDigest.getInstance("SHA-256");

			  //Passing data to the created MessageDigest Object
			  md.update(pass.getBytes());
			  
			  //Compute the message digest
			  byte[] digest = md.digest();      
			  System.out.println(digest);  
			 
			  //Converting the byte array in to HexString format
			  hexString = new StringBuffer();
			  
			  for (int i = 0;i<digest.length;i++) {
			     hexString.append(Integer.toHexString(0xFF & digest[i]));
			  }
			System.out.println("encry pass"+hexString.toString());
		
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
  		
  		
	    
	    System.out.println("eeeeeeeeeeeeeee   "+email);
		String str = "select u from User u where u.email=:em";
		User u= sf.getCurrentSession().createQuery(str,User.class).setParameter("em",email).
					getSingleResult();
		
		System.out.println("OOOOOOOOO   "+u);
		System.out.println("encry pass"+hexString.toString());
			u.setPassword(hexString.toString());
//		u.setPassword(pass);
	
	}

   
	@Override
	public void updatePassword(User userPojo) 
	{
		sf.getCurrentSession().update(userPojo);
		
	}
	

	

	@Override
	public User viewProfile(Integer id) throws Exception
	{
		
		return sf.getCurrentSession().get(User.class,id);
	}
	
	
	
	//feedback
	@Override
	public String patientfeedback(Integer id, feedback fb)
	{
		
		System.out.println(fb);
		System.out.println(fb.getDid());
		System.out.println("inside patientfeedback");
		String jpql="select u from User u where u.userId=:id";
		User u=sf.getCurrentSession().createQuery(jpql,User.class).setParameter("id",id).getSingleResult();
		System.out.println(u);
		System.out.println(fb);
		  u.getPtDetails().fbpt(fb);
	         
		    String jpql1="select d from DoctorDetails d where d.doctorId=:did";
		    DoctorDetails d=sf.getCurrentSession().createQuery(jpql1,DoctorDetails.class).setParameter("did", fb.getDid()).getSingleResult();
		    d.fbdt(fb);
		    sf.getCurrentSession().update(u);
		    sf.getCurrentSession().save(fb);
		    return "added";
		    
		    
		//Integer Did=fb.getDtd().getDoctorId();
	//	System.out.println(Did);
		
//		System.out.println(did);
		
	
		//Integer dtid=u.getDrDetails().getDoctorId();
		//System.out.println(dtid);
	    //Integer Ptid=u.getPtDetails().getPatientId();
	  
//System.out.println(fb.getFid());
	    
	    
//	    DoctorDetails d=sf.getCurrentSession().get(DoctorDetails.class,fb.getDid());
//	    d.fbdt(fb);
	  
	   // sf.getCurrentSession().update(d);  
		
	}

	@Override
	public List<feedback> displayfeedback(Integer id) 
	{
//		String jpql="select f from feedback f join fetch f.dtd p join fetch p.user u where u.userId:id ";
//		List<feedback> l=sf.getCurrentSession().createQuery(jpql,feedback.class).setParameter("id",id).getResultList();
//		System.out.println(l);
//		return l;		
		
	    System.out.println("in display feedback dao impl");	
	    System.out.println(id);
	    String jpql="select u from User u where userId=:id";
	   User u= sf.getCurrentSession().createQuery(jpql,User.class).setParameter("id", id).getSingleResult();
	   Integer did=u.getDrDetails().getDoctorId();
	   System.out.println(u);
	   System.out.println(did);
	   String jpql2="select f from feedback f where f.dtd.doctorId=:did";
	   List<feedback> f=sf.getCurrentSession().createQuery(jpql2,feedback.class).setParameter("did", did).getResultList();
	    System.out.println(f);
	   return f;
	}
	
	
	
	
	
}
