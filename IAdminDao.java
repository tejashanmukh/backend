package com.app.dao;

import java.util.List;

import com.app.pojos.Articles;
import com.app.pojos.DoctorDetails;
import com.app.pojos.Laboratories;
import com.app.pojos.Medical;
import com.app.pojos.User;

public interface IAdminDao 
{
    public List<User> getUserDetails() throws Exception;
	String deleteUserDetails(Integer UserId) throws Exception; 
	List<Medical>listallmedical();
    String deletemedicaldetails(int shopid);
 	String updateMedicalshop(Medical m);
 	Medical getMedicalById(Integer id);
 	Laboratories getLabById(Integer id);
 	Integer addmedicalshop(Medical m);
 	String deletelabdetails(int labid);
 	List<Laboratories>listalllaboratories();
 	Integer UpdateLab(Laboratories l);
 	String addLab(Laboratories l);
 	List<User>getcompletedetails(DoctorDetails d);
 	List<Articles>getArticledetailsacctodoctor();
	List<User> getdoctordetails();
 	Integer registerArticle(Articles a);
}
