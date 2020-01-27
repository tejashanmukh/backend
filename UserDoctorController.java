package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.http.HttpStatus.*;

import com.app.dao.IUserDoctorDao;
import com.app.pojos.User;

@CrossOrigin(allowedHeaders = "*")
@RequestMapping("/doctor")
@RestController
public class UserDoctorController 
{ 

	@Autowired
	private IUserDoctorDao docDao;
	
	
	  @GetMapping("/drDetails")
	  public ResponseEntity<?> getUser(@RequestParam Integer id )
	  {
		try {
			return new  ResponseEntity<User> (docDao.getDoctorById(id),OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<Exception>(e,NOT_FOUND);
		}
		  
	  }
     
	  @PostMapping("/updateDoc")
	  public ResponseEntity<?> updateDoctor(@RequestBody User user)
	  {
		  System.out.println("updateDoctor(@RequestBody User user");
	          System.out.println(user);
	// user.setDrDetails(user.getDrDetails());
		 
		try {
			return new  ResponseEntity<String>(docDao.updateDoctor(user),OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<Exception>(e,NOT_FOUND);
		}
	  }
	  
	
}
