package com.app.pojos;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;


@Entity

public class Articles 
{
	private int aid;
	private String subject;
	private String Description;
	private String image;
	private String name;
	


	public Articles() {
		System.out.println("in articles constructor");
	}
	
	
	public Articles(String subject, String description, String image, String name) {
		super();
		this.subject = subject;
		Description = description;
		this.image = image;
		this.name = name;
	}


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}

	@Lob
	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	@Override
	public String toString() {
		return "Articles [aid=" + aid + ", subject=" + subject + ", Description=" + Description + ", image=" + image
				+ ", name=" + name + "]";
	}

	

}
