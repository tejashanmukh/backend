package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.app.dao.IUserDao;

import com.app.pojos.feedback;
@CrossOrigin(allowedHeaders = "*")
@RestController
@RequestMapping("/feedback")
public class FeedbackController
{
	@Autowired
	
	public IUserDao dao;
	@PostMapping("/feedbackpatients")
	public String feebackpatient(@RequestParam Integer id,@RequestBody feedback f)
	{
		System.out.println(id);
		System.out.println(f.getDid());
		
		return dao.patientfeedback(id, f); 
	}
	@GetMapping("/getfeedbackaccdoctor")
	public List<feedback> getfeedbacklist(@RequestParam Integer id)
	{
		return dao.displayfeedback(id);
	}
	
}
