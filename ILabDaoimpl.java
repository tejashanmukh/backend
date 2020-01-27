package com.app.dao;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.app.pojos.Laboratories;


@Service
@Transactional
public class ILabDaoimpl implements ILabDao
{   @Autowired
    private SessionFactory sf;
	
	@Override
	public String deletelab(int labid) {
		System.out.println(labid);
		String message="delete labaratory failed";
		Session hs=sf.getCurrentSession();
		Laboratories l=hs.get(Laboratories.class,labid);
		if(l!=null)
		{
		 hs.delete(l);
		 message="Laboratory  with id:"+labid+"deletion succed";
		 return message;
		}
		return "notdeleted";
	}

}
