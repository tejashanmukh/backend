package com.app.controller;

import java.text.SimpleDateFormat;
import java.util.List;
import static org.springframework.http.HttpStatus.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.dao.IAppointmentDao;
import com.app.pojos.Appointment;
import com.app.pojos.PatientDetails;
import com.app.pojos.User;
import static org.springframework.http.HttpStatus.*;
@CrossOrigin(allowedHeaders = "*")
@RestController
@RequestMapping("/appointment")
public class AppointmentController 
{
	@Autowired
     private IAppointmentDao appDao;
  
	@PostMapping("/app")
     public ResponseEntity<?> bookApp(@RequestBody Appointment app,@RequestParam int id)
     {
		System.out.println(id);
		
		System.out.println("hey"+app.getDrId());
		System.out.println(app.getAppDate());
	    int hrs=app.getAppDate().getHours();
	    int min =app.getAppDate().getMinutes();
        
	    
	    System.out.println(hrs+":"+min);
	    
	    
//	    if(hrs==0)
//	    {
//	    	System.out.println("hello");
//	    }
	    
	    
	    
	    
	    
	    
	  //  int mins=app.getAppDate().getMinutes();
//        	String time= hrs+":"+mins;
//	    System.out.println("hey"+app.getAppDate().getTime());	 
//        System.out.println(app); System.out.println(app.getAppTime());
//        		  SimpleDateFormat sdf = new SimpleDateFormat("HH:mm"); 
//        		  String time1 =0 to 5:29
//        		  sdf.format(app.getAppTime()); 
	    
	    if(hrs >=0 & hrs<5)
	    {
	    
	    	 System.out.println("bhai");
	    	String time= hrs+18+":"+(min+30);
	      System.out.println(time);    
	    }
	    else
	    {
	    if(hrs < 12)
	    {
	    if(min<30)
	    {
	    	System.out.println("dingdong");
	    	String time= hrs-6+":"+(min+30);
	    	 app.setAppTime(time);
	    }
	    else
	    {
	   
//	    	String time= hrs-6+":"+(min+30);
//	    	 app.setAppTime(time);
	    	System.out.println("hey bro");
	    	 String time= hrs-5+":"+(min-30);
	    	 app.setAppTime(time);
	    }
	    }
	    else
	    {
	    	System.out.println("hello");
		    if(min<30)
		    {
		    	String time= hrs-6+":"+(min+30);
		    	 app.setAppTime(time);
		    }
		    else
		    {
//		    	String time= hrs-6+":"+(min+30);
//		    	 app.setAppTime(time);
		    	System.out.println("hey bro");
		    	 String time= hrs-5+":"+(min-30);
		    	 app.setAppTime(time);
		    }
	    }
	   
		
     }
//	    app.getDrId().setDoctorId(id);
	    try 
	    {
			return new ResponseEntity<Integer>(appDao.bookAppointment(app,id),OK);
		} 
	    catch (Exception e)
	    {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<Exception>(e,NOT_FOUND);
		}
     }     
     
	@GetMapping("/getApptlist")
	public  ResponseEntity<?> apptList(@RequestParam Integer did)
	{
		System.out.println("controller"+did);
		try {
			return new ResponseEntity<List<Appointment>> (appDao.getAppDetails(did),OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<Exception>(e,NOT_FOUND);
		}
	}
	
	
	@GetMapping("/confirmAppt")
	public ResponseEntity<?> confirmAppt(@RequestParam Integer id)
	{
		System.out.println("confirm"+id);
		try 
		{
			return new ResponseEntity<String>(appDao.ConfirmAppt(id),OK);
		}
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<Exception>(e,NOT_FOUND);
		}
	}
	
	@GetMapping("/declineAppt")
	public ResponseEntity<?> declineAppt(@RequestParam Integer id)
	{
		System.out.println("confirm"+id);
		try {
			return new ResponseEntity<String>(appDao.DeclineAppt(id),OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<Exception>(e,NOT_FOUND);
		}
	}
	
	@GetMapping("/getPtdetails")
	public ResponseEntity<?> getPtDetails(@RequestParam Integer id)
	{
		System.out.println("hey Buddy"+id);
		try {
			return new ResponseEntity<User>(appDao.getPtDetails(id),OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<Exception>(e,NOT_FOUND);
		}
		
	}
	
	@GetMapping("/getApptdetailsofPatient")
	public List<Appointment> getApptDetailsOfPatient(@RequestParam Integer id)
	{
		return appDao.getAppdetail(id);
	}	

	
	
	
	
}
