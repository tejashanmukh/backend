package com.app.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.pojos.Articles;
import com.app.pojos.DoctorDetails;
import com.app.pojos.Laboratories;
import com.app.pojos.Medical;
import com.app.pojos.User;

@Service
@Transactional
public class AdminDaoImpl implements IAdminDao
{
	@Autowired
	public SessionFactory sf;
	
	@Override
	public List<User> getUserDetails() throws Exception
	{
		String jpql="select u from User u";
		
		return sf.getCurrentSession().createQuery(jpql,User.class).getResultList();
	}

	
	
	@Override
	public String deleteUserDetails(Integer UserId) throws Exception
	{
		// TODO Auto-generated method stub
     Session hs= sf.getCurrentSession();
     User u=hs.get(User.class,UserId);
   hs.delete(u);
           return "Successfully deleted";
	}
	
	@Override
	public List<Medical> listallmedical() 
	{
		String jpql="select m from Medical m";
		return sf.getCurrentSession().createQuery(jpql,Medical.class).getResultList();
	}

	@Override
	public String deletemedicaldetails(int shopid) 
	{
		System.out.println(shopid);
	String message="delete medicalshop failed";
	Session hs=sf.getCurrentSession();
	Medical m=hs.get(Medical.class,shopid);
	if(m!=null)
	{
	 hs.delete(m);
	 message="medical with id:"+shopid+"deletion succed";
	}
	return message;
	}

	@Override
	public String updateMedicalshop(Medical m) {
		System.out.println("hiii"+m);
		Medical med= sf.getCurrentSession().get(Medical.class,m.getShopid());
		System.out.println(med);
		
		med.setCity(m.getCity());
		med.setMedicalshopname(m.getMedicalshopname());
		med.setOwnername(m.getOwnername());
		med.setPhno(m.getPhno());
		med.setAddress(m.getAddress());
		System.out.println(m);
		return "updated";
	}

	@Override
	public Medical getMedicalById(Integer shopid) {
		return sf.getCurrentSession().get(Medical.class,shopid);

	}

	@Override
	public Integer addmedicalshop(Medical m) 
	{	
		System.out.println(m);
		System.out.println(m);
		sf.getCurrentSession().save(m);
		return 1;
	}
	@Override
	public List<Laboratories> listalllaboratories() 
	{    
		String jpql="select l from Laboratories l";
		return sf.getCurrentSession().createQuery(jpql,Laboratories.class).getResultList();
	}
	@Override
	public String deletelabdetails(int labid) {
       System.out.println(labid);
       String message ="delete laboratory is failed";
    Session hs=sf.getCurrentSession();
      Laboratories l=hs.get(Laboratories.class,labid);
       if(l!=null)
       {
    	  hs.delete(l);
    	  message="laboratory with id:"+labid+"deletion succed";
       }
		return message;
	}

	@Override
	public Laboratories getLabById(Integer id) {
		return sf.getCurrentSession().get(Laboratories.class, id);
		
	}

	@Override
	public Integer UpdateLab(Laboratories l) {
		System.out.println("hiii"+l);
		Laboratories lab=sf.getCurrentSession().get(Laboratories.class,l.getLabid());
		System.out.println(lab);
		lab.setAddress(l.getAddress());
		lab.setCity(l.getCity());
		lab.setEmail(l.getCity());
		lab.setName(l.getName());
		lab.setPhonenum(l.getPhonenum());
		return 1;
	}

	@Override
	public String addLab(Laboratories l) {
      sf.getCurrentSession().save(l);
		return "added successfully";
	}

	//@Override
	//public List<User> listallusers()
	//{
	  //   String jpql="select u from User u ";
	    //  return sf.getCurrentSession().createQuery(jpql,User.class).getResultList();
		
	//}
	@Override
	public List<User> getcompletedetails(DoctorDetails dr) {
//		String jpql="select d from DoctorDetails d left join fetch d.articles";
		String jpql="select u from User u" ;
		
		List<User> l= sf.getCurrentSession().createQuery(jpql,User.class).getResultList();
	              
		return l;
	}
	@Override
	public List<User> getdoctordetails() {
//		String jpql="select d from DoctorDetails d left join fetch d.articles";
		String jpql="select u from User u where u.userType='DOCTOR'" ;
		
		List<User> l= sf.getCurrentSession().createQuery(jpql,User.class).getResultList();
	              
		return l;
	}
	@Override
	public List<Articles>getArticledetailsacctodoctor()
	{
		String jpql="select a from Articles a ";
		return sf.getCurrentSession().createQuery(jpql,Articles.class).getResultList();
	}

	@Override
	public Integer registerArticle(Articles a) 
	{
		 return (Integer)sf.getCurrentSession().save(a);
	     
	    			
		
	}
	
	
	
	
	
	
	
	
	
//	@Override
//	public String deleteUserDetails(Integer UserId) throws Exception
//	{
//		// TODO Auto-generated method stub
//     Session hs= sf.getCurrentSession();
//     User u=hs.get(User.class,UserId);
//      	
//    hs.delete(u);
//     return "Successfully deleted";
//	}
    

//Admin is not supposed to view details of all employee
//	@Override
//	public User getCompleteDetails(@RequestParam int uid) {
//		String jpql = "select u from User u left outer join fetch u.drDetails or u.ptDetails where u.id=:id";
//		return sf.getCurrentSession().createQuery(jpql, User.class).setParameter("id", uid).getSingleResult();
//	}
//		

	
	
}
