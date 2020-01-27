package com.app.controller;
import static org.springframework.http.HttpStatus.*;
import javax.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;
import com.app.dao.IUserDao;
import com.app.pojos.DoctorDetails;
import com.app.pojos.PatientDetails;
import com.app.pojos.User;
import com.app.service.IUserService;

@CrossOrigin(allowedHeaders = "*")
@RestController
@RequestMapping("/user")
public class UserController 
{
    @Autowired
    private IUserDao userDao;
   
    @Autowired
	private JavaMailSender mailSender;   
   
    @Autowired
    private IUserService service;
    
    
    User user;
    int otp;
    
    @PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody User user)
    {
		try 
		{
			System.out.println(user);
			String msg="successfully registered";
			SimpleMailMessage mailmessage=new SimpleMailMessage();
			mailmessage.setTo(user.getEmail());
			mailmessage.setSubject("registeration update");
			mailmessage.setText(msg);
			mailSender.send(mailmessage);
			return new ResponseEntity<Integer>(userDao.registerUser(user),OK);
		} 
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		     return new ResponseEntity<Exception>(e,NOT_FOUND);
		}
	}
    
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody User user) 
	{
		System.out.println(user);	
		try 
		{
			return new ResponseEntity<User>(userDao.authenticateUser(user),OK);
		} 
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<Exception>(e,NOT_FOUND);
		}
	}
	
	@PostMapping("/registerDoc/{id}")
	public void regisDr(@PathVariable Integer id,@RequestBody DoctorDetails user)
	{
		 System.out.println(id);
		 System.out	.println(user);
	     try 
	     {
			userDao.drDetails(id,user);
		 } 
	     catch (Exception e)
	     {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
//	@PostMapping("/registerDoc")
//	public void regisDr(@RequestParam Integer id,@RequestBody DoctorDetails user)
//	{
//		 System.out.println(id);
//		 System.out	.println(user);
//		 System.out	.println(user.getHosp().getHospId());
//	     userDao.drDetails(id,user);
//		
//	}
	
	
	@PostMapping("/registerPt/{id}")
	public void regisPt(@PathVariable Integer id,@RequestBody PatientDetails user)
	{
		 System.out.println(id);
		 System.out.println(user);
		try {
			userDao.ptDetails(id,user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
	}
	
	
	//otp#########################################################################################
	@GetMapping("/forgot")
	public  ResponseEntity<?> processForgotPassword(@RequestParam String em)
	{
		
		System.out.println("email  "+em);
		user= service.findByEmail(em);;
		try
		{
			//user = service.findByEmail(em);
			System.out.println(user);
			if(user !=null)
			{
				 otp =service.generateOtp();
				System.out.println(otp);
				String msg="Your one time password(OTP) is = "+otp;
				SimpleMailMessage mailMessage = new SimpleMailMessage();
				mailMessage.setTo(user.getEmail());
				mailMessage.setSubject("One Time Password");
				mailMessage.setText(msg);
				try
				{
					mailSender.send(mailMessage);
					return new ResponseEntity<String>("success",OK);
				}
				catch (MailException e) 
				{
					System.out.println("inside mail exception");
					e.printStackTrace();
				}
			}
		} catch (NoResultException e) 
		{
			System.out.println("in other exception");
			e.printStackTrace();
		}
		return new ResponseEntity<String>("error",INTERNAL_SERVER_ERROR);

	}

//setpass######################################################################	
	@PostMapping("/setpass")
	public ResponseEntity<?> setpassword(@RequestBody User u)
	{
		System.out.println(u);
		System.out.println("upasssssssssssssssss  "+u.getPassword());
		System.out.println("mail "+user.getEmail()+" otp "+otp);
		try
		{
			if(otp==u.getOtp())
			{
				userDao.setPass(u.getPassword(),user.getEmail());
				return new ResponseEntity<Integer>(1,OK);
			}
			else 
			{
				return new ResponseEntity<Integer>(0,OK);
			}
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<Exception>(e,NOT_FOUND);
		}	
	}
	
	@GetMapping("/viewProf")
	public ResponseEntity<?> getprofile(@RequestParam Integer id)
	{
		try 
		{
			return new ResponseEntity<User>(userDao.viewProfile(id),OK);
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<Exception>(e,NOT_FOUND);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
 
}














