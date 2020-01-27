package com.app.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.pojos.Appointment;
import com.app.pojos.DoctorDetails;
import com.app.pojos.PatientDetails;
import com.app.pojos.User;
import com.app.pojos.UserGender;


@Service
@Transactional
public class AppointmentImpl implements IAppointmentDao 
{
  @Autowired		
   private SessionFactory sf;
	
	@Override
	public Integer bookAppointment(Appointment app,Integer id) throws Exception
	{	
		System.out.println(app);
		//System.out.println(app.getDrId());
		System.out.println("id"+id);
		String jpql="select p from PatientDetails p left join fetch p.user u where u.userId=:x";
		PatientDetails u=sf.getCurrentSession().createQuery(jpql,PatientDetails.class).setParameter("x",id).getSingleResult();
		System.out.println(u); 
		u.apptPt(app);
        sf.getCurrentSession().update(u);
		
		//  String jpql="select u from User u left join fetch u.ptDetails where u.userId=:x";
        //	User u=sf.getCurrentSession().createQuery(jpql,User.class).setParameter("x",id).getSingleResult();
		//  Integer patientid=u.getPatientId();
        //  System.out.println("headache "+patientid);    
        //	System.out.println("heyRam"+u);    
        //	System.out.println( u.getPtDetails());
        //	app.setPtId(u.getPtDetails());
		//	app.setApptPt(u.getPtDetails());
		
	    System.out.println(app);
        System.out.println("App Dao"+id);
	    app.setAppStatus("Requested");

        //	app.setPtId(app.getDrId().getDoctorId());
		return (Integer) sf.getCurrentSession().save(app);
	}
	
	
	@Override
	public List<Appointment> getAppDetails(Integer DrUserid)throws Exception
	{
		//To get  doctor id by passing userid
        String jpql1="select d from DoctorDetails d where d.user.userId=:id";
        DoctorDetails dr=sf.getCurrentSession().createQuery(jpql1,DoctorDetails.class).setParameter("id", DrUserid).getSingleResult();
        Integer drid=dr.getDoctorId();
		System.out.println("hey buddy"+dr);
		System.out.println("Doctor id"+drid);
		
		
		String jpql="select a from Appointment a where a.drId.doctorId=:id and a.appStatus='Requested'";
		
		List<Appointment> l=sf.getCurrentSession().createQuery(jpql,Appointment.class).setParameter("id",drid).getResultList();
		
		System.out.println(l);
		
		return l;
		
				
		//System.out.println(did);
		//String jpql="select p from PatientDetails p left join fetch p.Appointment a left join fetch a.drId d where d.doctorId=:id and a.appStatus='Requested'";	
        //String jpql="select p from PatientDetails p left join fetch p.Appointment a where a.drId.doctorId=:id and a.appStatus='Requested'";		
        //String jpql="select p from PatientDetails p left join fetch p.Appointment";
               
        //List<PatientDetails> l= sf.getCurrentSession().createQuery(jpql,PatientDetails.class).getResultList();
        //List<PatientDetails> l= sf.getCurrentSession().createQuery(jpql,PatientDetails.class).setParameter("id",did).getResultList();
	           
        //System.out.println(l);
        //return l;
	
	}
	
// public String setBmi(PatientDetails p,Integer id)
// {
//	Double wt= p.getPtWeight();
//	Double ht= p.getHeight();
//	 Double bodyMassIndex=(100*100*wt)/(ht*ht);
//	 String jpql="select p from PatientDetails p where p.patientId=:id";
//	 PatientDetails pt=sf.getCurrentSession().createQuery(jpql,PatientDetails.class).getSingleResult();	 
//	 return "updated";
//	 
// }
	
	
	
	@Override
	public String ConfirmAppt(Integer Appid) throws Exception
	{
		System.out.println("inside confirmAppt"+Appid);

		String jpql="select a from Appointment a where a.appId=:appid";
		
		Appointment app=sf.getCurrentSession().createQuery(jpql,Appointment.class).setParameter("appid",Appid).getSingleResult();
              
		System.out.println("inside confirmAppt"+app);
		app.setAppStatus("Confirmed");
		return "Status updated";
	}
	
	@Override
	public String DeclineAppt(Integer Appid) throws Exception
	{
		System.out.println("inside confirmAppt"+Appid);
		String jpql="select a from Appointment a where a.appId=:appid";
		Appointment app=sf.getCurrentSession().createQuery(jpql,Appointment.class).setParameter("appid",Appid).getSingleResult();
        System.out.println("inside confirmAppt"+app);
		app.setAppStatus("Rejected");
		return "Status updated";
	}
	
	@Override
	public User getPtDetails(Integer pid) throws Exception
	{
		String jpql="select p from PatientDetails p where p.patientId=:id ";
		PatientDetails p=sf.getCurrentSession().createQuery(jpql,PatientDetails.class).setParameter("id",pid).getSingleResult();
		return new User(p.getUser().getUserId(),p.getUser().getName(),p.getUser().getEmail(),p.getUser().getAge(),p.getUser().getUserGender());
		
	}
	
	//to show his appointment to patient
	@Override
	public List<Appointment> getAppdetail(Integer id)
	{
	        User u= sf.getCurrentSession().get(User.class, id);
	       System.out.println(u);
	       //pid patient id
	       Integer pid=u.getPtDetails().getPatientId();
	       String jpql="select a from Appointment a where a.ptId.patientId=:apid";
	       return sf.getCurrentSession().createQuery(jpql,Appointment.class).setParameter("apid",pid).getResultList();
	       
	       
	       
	  	
	}
	
	
	
	
	
	
	
	
	
	
}
