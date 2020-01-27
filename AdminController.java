package com.app.controller;

import static org.springframework.http.HttpStatus.*;
import java.util.List;

import javax.print.DocFlavor.STRING;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.dao.IAdminDao;
import com.app.pojos.Articles;
import com.app.pojos.DoctorDetails;
import com.app.pojos.Laboratories;
import com.app.pojos.Medical;
import com.app.pojos.User;

@CrossOrigin(allowedHeaders = "*")
@RequestMapping("/admin")
@RestController
public class AdminController 
{
    
	@Autowired
	private IAdminDao adminDao;
	
	
	@GetMapping
	public ResponseEntity<?> getUser()
	{
	   try
	   {
		return new ResponseEntity<List<User>>(adminDao.getUserDetails(),OK);
	   } 
	   catch (Exception e) 
	   {
		// TODO Auto-generated catch block
		e.printStackTrace();
     	return new ResponseEntity<Exception>(e,NOT_FOUND);
	   } 
	}
	
@GetMapping("deleteUser/{uid}")
public ResponseEntity<?> deleteUser(@PathVariable Integer uid)
{
		System.out.println(uid);
    try 
    {
		return new ResponseEntity<String>(adminDao.deleteUserDetails(uid),OK);
	} 
    catch (Exception e)
    {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return new ResponseEntity<Exception>(e,NOT_FOUND);
	}		

}
	
	
public AdminController()
{
	System.out.println("in ctor of admin controller");
}


@GetMapping("/list")
public List<Medical>showMedicalList()
{
	System.out.println("in show medical list");
	return adminDao.listallmedical();
}
@GetMapping("/shopdetails")
public Medical getmedical(@RequestParam Integer shopid )
{
	System.out.println(shopid);
	return adminDao.getMedicalById(shopid);
}
@GetMapping("/labs")
public List<Laboratories>showlabs()
{
	System.out.println("in show labs list");
	return adminDao.listalllaboratories();

}
//delete medicalshop
@GetMapping("/delete/{id}")
public ResponseEntity<?> deletemedicalshop(@PathVariable int id)
{
	System.out.println("in delete medicalshop");
	System.out.println(id);
	adminDao.deletemedicaldetails(id);
	return new ResponseEntity<Integer>(1,OK);
}
@PostMapping("/updatemedical")
public int updatemedical(@RequestBody Medical m)
{
	System.out.println(m);
	adminDao.updateMedicalshop(m);
	return 1;
}
@GetMapping("/labdetails")
public Laboratories getlab(@RequestParam Integer labid)
{
	System.out.println(labid);
	return adminDao.getLabById(labid);
}
@GetMapping("/labdelete/{id}")
public Integer deletelab(@PathVariable int id)
{  
	System.out.println("in delete lab method of admincontroller");
	System.out.println(id);
	String s=adminDao.deletelabdetails(id);
	return 1;	
}
@PostMapping("/updatelab")
public Integer updatelab(@RequestBody Laboratories l)
{
	System.out.println("in updatelab method in controller");
	adminDao.UpdateLab(l);
	return 1;
}
@PostMapping("/addmedical")
public Integer addmedical(@RequestBody Medical m)
{
	System.out.println("in addmedical method in  controller");
	adminDao.addmedicalshop(m);
	return 1;
}
@PostMapping("/addlab")
public Integer addlab(@RequestBody Laboratories l)
{
	System.out.println("in addlab method");
	adminDao.addLab(l);
	return 1;
}
@GetMapping("/listofuserandDoctordetails")
public List<User>getuserdetails(DoctorDetails d)
{
	System.out.println("in doctordetails method in controller");
	//	return dao.getcompletedetails(d);
	return adminDao.getcompletedetails(d); 
}
@GetMapping("/listofDoctordetails")
public List<User>getDoctordetails()
{
	System.out.println("in doctordetails method in controller");
	//	return dao.getcompletedetails(d);
	return adminDao.getdoctordetails(); 
}
@GetMapping("/articles")
public  List<Articles>getarticledetailsaccdocid()
{
	return adminDao.getArticledetailsacctodoctor();
}
@PostMapping("/registerarticle")
public Integer registerarticle(@RequestBody Articles a)
{
	adminDao.registerArticle(a);
   return 1;
}


		
}
