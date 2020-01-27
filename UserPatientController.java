package com.app.controller;

import java.util.List;
import static org.springframework.http.HttpStatus.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.dao.IUserPatientDao;
import com.app.pojos.DoctorDetails;
import com.app.pojos.User;

@CrossOrigin(allowedHeaders = "*")
@RestController
@RequestMapping("/patient")
public class UserPatientController
{
  @Autowired
  IUserPatientDao patientDao;
	
  @GetMapping("/pdetails")
  public ResponseEntity<?> getUser(@RequestParam Integer id )
  {
	try {
		return new ResponseEntity<User>(patientDao.getPatientById(id),OK);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return new ResponseEntity<Exception>(e,NOT_FOUND);
	}
	  
  }
  
  @PostMapping("/patientDetails")
  public ResponseEntity<?> updatePatient(@RequestBody User u)
  {
	  System.out.println(u);
	  System.out.println(u.getPtDetails());
	try 
	{
		return new ResponseEntity<String>(patientDao.updatePatient(u),OK);
	}
	catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return new ResponseEntity<Exception>(e,NOT_FOUND);
	}
	  
  }
  
  @GetMapping("/drdetails")
  public ResponseEntity<?> getDoctordetails()
  {
	  try 
	  {
		return new ResponseEntity<List<User>>(patientDao.getDoctorDetails(),OK);
	  } 
	  catch (Exception e)
	  {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return new ResponseEntity<Exception>(e,NOT_FOUND);
	}
	  
  }
  //for fetching data as per patient requirement(i.e city and expertise)
  @PostMapping("/drdetailsby")
  public ResponseEntity<?> getDoctordetailsby(@RequestBody DoctorDetails u)
  {
	 String city= u.getHospAddress();
	  String expertise=u.getExpertise();
	  
	  System.out.println(city+" "+expertise);
	  try
	  {
		return new ResponseEntity<List<User>>(patientDao.getDoctorDetailsBy(city, expertise),OK);
      } 
	  catch (Exception e) 
	  {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return new ResponseEntity<Exception>(e,NOT_FOUND);
	}
	  
  }
  
  @GetMapping("/drdetailsbyId/{id}")
  public ResponseEntity<?> getDoctordetailsbyId(@PathVariable int id  )
  {
	  System.out.println(id);
	  try
	  {
		return new ResponseEntity<List<User>>(patientDao.getDrdetailsById(id),OK);
	  } 
	  catch (Exception e) 
	  {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return new ResponseEntity<Exception>(e,NOT_FOUND);
	  }
  }
	
}
